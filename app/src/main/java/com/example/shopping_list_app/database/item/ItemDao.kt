package com.example.shopping_list_app.database.item

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_table WHERE list_id LIKE :listId ORDER BY id ASC")
    fun getAllItem(listId : Int) : LiveData<List<Item>>
}