package com.example.shopping_list_app.database.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class Item(
    @ColumnInfo(name = "list_id") val listId: Int,
    @ColumnInfo(name = "item_name") val itemName: String,
    @ColumnInfo(name = "quantity") val quantity: String,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "cost_per_unit") val costPerUnit: String,
    @ColumnInfo(name = "cost") val cost: String,
    @ColumnInfo(name = "isChecked") val isChecked : Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}