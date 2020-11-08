package com.example.shopping_list_app.database.shoppingList

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopping_list_app.database.shoppingList.ShoppingList

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingList: ShoppingList)

    @Delete
    suspend fun delete(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list ORDER BY id DESC")
    fun getAllShoppingListName() : LiveData<List<ShoppingList>>
}