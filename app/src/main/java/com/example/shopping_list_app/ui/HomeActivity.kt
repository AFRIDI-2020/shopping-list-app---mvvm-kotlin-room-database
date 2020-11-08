package com.example.shopping_list_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list_app.R
import com.example.shopping_list_app.adapters.ShoppingListAdapter
import com.example.shopping_list_app.database.ShoppingListDatabase
import com.example.shopping_list_app.database.shoppingList.ShoppingList
import com.example.shopping_list_app.repositories.ShoppingListRepository
import com.example.shopping_list_app.viewModelFactories.ShoppingListViewModelFactory
import com.example.shopping_list_app.viewModels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_layout_create_list.view.*

class HomeActivity : AppCompatActivity() {

    lateinit var shoppingListViewModel: ShoppingListViewModel
    lateinit var shoppingListAdapter: ShoppingListAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.home_activity_toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val shoppingListRepository = ShoppingListRepository(ShoppingListDatabase(this))
        val shoppingListViewModelFactory = ShoppingListViewModelFactory(shoppingListRepository)
        shoppingListViewModel = ViewModelProvider(
            this,
            shoppingListViewModelFactory
        ).get(ShoppingListViewModel::class.java)

        shoppingListAdapter = ShoppingListAdapter(listOf(), shoppingListViewModel)
        rv_shopping_list_name.apply {
            adapter = shoppingListAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        shoppingListViewModel.getAllShoppingListName().observe(this, Observer {
            shoppingListAdapter.shoppingLists = it
            shoppingListAdapter.notifyDataSetChanged()
        })

        tv_create_list.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_layout_create_list, null)
            builder.setView(view)
            dialog = builder.create()
            view.tv_create_list_name.setOnClickListener {
                val shoppingListName: String = view.et_shopping_list_name.text.toString()
                if(shoppingListName.isEmpty()){
                    return@setOnClickListener
                }
                shoppingListViewModel.upsert(ShoppingList(shoppingListName))
                dialog.dismiss()
            }
            dialog.show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.home_activity_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}