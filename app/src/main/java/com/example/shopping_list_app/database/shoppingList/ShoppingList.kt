package com.example.shopping_list_app.database.shoppingList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list")
class ShoppingList(
    @ColumnInfo(name = "shopping_list_name") val shoppingListName : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}