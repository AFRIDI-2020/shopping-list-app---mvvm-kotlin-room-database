package com.example.shopping_list_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.R
import com.example.shopping_list_app.database.item.Item
import com.example.shopping_list_app.viewModels.ItemViewModel
import kotlinx.android.synthetic.main.only_item_preview.view.*

class ItemAdapter(
    var itemlist: List<Item>, val itemViewModel: ItemViewModel
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.only_item_preview,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemlist[position]

        holder.itemView.apply {
            tv_serial.text = (position+1).toString()
            tv_item_name.text = item.itemName
            val quantity = item.quantity
            val unit = item.unit
            val finalQuantity= "$quantity $unit"
            tv_item_quantity.text = finalQuantity
        }
    }


}