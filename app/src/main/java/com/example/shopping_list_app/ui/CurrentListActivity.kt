package com.example.shopping_list_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list_app.R
import com.example.shopping_list_app.adapters.ItemAdapter
import com.example.shopping_list_app.database.ShoppingListDatabase
import com.example.shopping_list_app.database.item.Item
import com.example.shopping_list_app.database.shoppingList.ShoppingList
import com.example.shopping_list_app.repositories.ItemRepository
import com.example.shopping_list_app.repositories.ShoppingListRepository
import com.example.shopping_list_app.viewModelFactories.ItemViewModelFactory
import com.example.shopping_list_app.viewModelFactories.ShoppingListViewModelFactory
import com.example.shopping_list_app.viewModels.ItemViewModel
import com.example.shopping_list_app.viewModels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_current_list.*

class CurrentListActivity : AppCompatActivity() {

    lateinit var shoppingListViewModel: ShoppingListViewModel
    lateinit var itemViewModel: ItemViewModel
    lateinit var itemAdapter: ItemAdapter
    var unit : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_list)

        val shoppingListRepository = ShoppingListRepository(ShoppingListDatabase(this))
        val shoppingListViewModelFactory = ShoppingListViewModelFactory(shoppingListRepository)
        shoppingListViewModel = ViewModelProvider(
            this,
            shoppingListViewModelFactory
        ).get(ShoppingListViewModel::class.java)

        val listId: Int = intent.getIntExtra("listId", 0)
        shoppingListViewModel.getListNameDate(listId).observe(this, Observer {
            tv_list_name.text = it.shoppingListName
            tv_date.text = it.date
        })

        val itemRepository = ItemRepository(ShoppingListDatabase(this))
        val itemViewModelFactory = ItemViewModelFactory(itemRepository)
        itemViewModel = ViewModelProvider(this, itemViewModelFactory).get(ItemViewModel::class.java)

        itemAdapter = ItemAdapter(listOf(),itemViewModel)
        rv_only_item.adapter = itemAdapter
        rv_only_item.layoutManager = LinearLayoutManager(this)

        itemViewModel.getAllItem(listId).observe(this, Observer {
            itemAdapter.itemlist = it
            itemAdapter.notifyDataSetChanged()
            rv_only_item.smoothScrollToPosition(itemAdapter.itemCount)
        })

        kg.setOnClickListener {
            unit = getString(R.string.kg)
            tv_unit.text = unit
        }
        gram.setOnClickListener {
            unit = getString(R.string.gram)
            tv_unit.text = unit
        }
        quarter.setOnClickListener {
            unit = getString(R.string.quarter)
            tv_unit.text = unit
        }
        liter.setOnClickListener {
            unit = getString(R.string.liter)
            tv_unit.text = unit
        }
        ml.setOnClickListener {
            unit = getString(R.string.ml)
            tv_unit.text = unit
        }
        piece.setOnClickListener {
            unit = getString(R.string.piece)
            tv_unit.text = unit
        }
        dozen.setOnClickListener {
            unit = getString(R.string.dozen)
            tv_unit.text = unit
        }
        pair.setOnClickListener {
            unit = getString(R.string.pair)
            tv_unit.text = unit
        }
        packet.setOnClickListener {
            unit = getString(R.string.packet)
            tv_unit.text = unit
        }
        bag.setOnClickListener {
            unit = getString(R.string.bag)
            tv_unit.text = unit
        }
        the_unit.setOnClickListener {
            unit = getString(R.string.unit)
            tv_unit.text = unit
        }

        addBtn.setOnClickListener {
            val itemName = et_item_name.text.toString()
            val quantity = et_quantity.text.toString()
            if(itemName.isEmpty() || quantity.isEmpty()){
                return@setOnClickListener
            }
            itemViewModel.upsertItem(Item(listId,itemName,quantity,unit,"0","0",false))
            et_item_name.text = null
            et_quantity.text = null
        }

    }


}