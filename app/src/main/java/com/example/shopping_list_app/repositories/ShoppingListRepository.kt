package com.example.shopping_list_app.repositories

import com.example.shopping_list_app.database.ShoppingListDatabase
import com.example.shopping_list_app.database.shoppingList.ShoppingList

class ShoppingListRepository(val shoppingListDatabase: ShoppingListDatabase) {

    suspend fun upsert(shoppingList: ShoppingList) = shoppingListDatabase.shoppingListDao().upsert(shoppingList)
    suspend fun delete(shoppingList: ShoppingList) = shoppingListDatabase.shoppingListDao().delete(shoppingList)
    fun getAllShoppingListName() = shoppingListDatabase.shoppingListDao().getAllShoppingListName()
}