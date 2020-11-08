package com.example.shopping_list_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopping_list_app.database.shoppingList.ShoppingList
import com.example.shopping_list_app.database.shoppingList.ShoppingListDao

@Database(
    entities = [ShoppingList::class],
    version = 1
)
abstract class ShoppingListDatabase : RoomDatabase(){
    abstract fun shoppingListDao() : ShoppingListDao

    companion object{

        @Volatile
        private var instance : ShoppingListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also {
                instance = it
            }
        }

        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingListDatabase::class.java,
                "shoppingListDatabase.db"
            ).build()
    }
}