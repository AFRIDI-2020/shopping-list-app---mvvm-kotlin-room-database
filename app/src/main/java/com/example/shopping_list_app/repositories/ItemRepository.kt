package com.example.shopping_list_app.repositories

import com.example.shopping_list_app.database.ShoppingListDatabase
import com.example.shopping_list_app.database.item.Item

class ItemRepository(val shoppingListDatabase: ShoppingListDatabase) {
    suspend fun upsertItem(item: Item) = shoppingListDatabase.itemDao().upsertItem(item)
    suspend fun deleteItem(item: Item) = shoppingListDatabase.itemDao().deleteItem(item)
    fun getAllItem(listId : Int) = shoppingListDatabase.itemDao().getAllItem(listId)
}