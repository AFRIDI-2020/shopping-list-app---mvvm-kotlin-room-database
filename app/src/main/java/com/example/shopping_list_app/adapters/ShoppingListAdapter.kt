package com.example.shopping_list_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.R
import com.example.shopping_list_app.database.shoppingList.ShoppingList
import com.example.shopping_list_app.ui.CurrentListActivity
import com.example.shopping_list_app.viewModels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.dialog_layout_create_list.view.*
import kotlinx.android.synthetic.main.dialog_layout_delete_list_name.view.*
import kotlinx.android.synthetic.main.shopping_list_name_preview.view.*

class ShoppingListAdapter(
    var shoppingLists : List<ShoppingList>, val shoppingListViewModel: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_name_preview,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingList = shoppingLists[position]

        holder.itemView.apply {
            tv_list_number.text = (position+1).toString()
            tv_list_name.text = shoppingList.shoppingListName
            iv_delete.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                val view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_delete_list_name,null)
                builder.setView(view)
                val dialog : AlertDialog = builder.create()
                view.tv_delete_list_name.text = shoppingList.shoppingListName
                view.tv_delete.setOnClickListener {
                    shoppingListViewModel.delete(shoppingList)
                    notifyDataSetChanged()
                    dialog.dismiss()
                }
                view.tv_cancel.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()

            }

            iv_edit.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                val view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_create_list,null)
                builder.setView(view)
                val dialog : AlertDialog = builder.create()
                view.et_shopping_list_name.setText(shoppingList.shoppingListName)

                view.tv_create_list_name.setOnClickListener {
                    val shoppingListName: String = view.et_shopping_list_name.text.toString()
                    if(shoppingListName.isEmpty()){
                        return@setOnClickListener
                    }
                    val newShoppingList = ShoppingList(shoppingListName)
                    newShoppingList.id = shoppingList.id
                    shoppingListViewModel.upsert(newShoppingList)
                    dialog.dismiss()
                }

                dialog.show()
            }

            setOnClickListener {
                val intent = Intent(context,CurrentListActivity::class.java)
                intent.putExtra("listId",shoppingList.id)
                context.startActivity(intent)
            }
        }
    }


}