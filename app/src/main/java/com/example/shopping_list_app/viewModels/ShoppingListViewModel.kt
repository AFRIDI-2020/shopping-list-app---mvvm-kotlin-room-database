package com.example.shopping_list_app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping_list_app.database.shoppingList.ShoppingList
import com.example.shopping_list_app.repositories.ShoppingListRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel(val shoppingListRepository: ShoppingListRepository) : ViewModel() {

    fun upsert(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.upsert(shoppingList)
    }

    fun delete(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.delete(shoppingList)
    }

    fun getAllShoppingListName() = shoppingListRepository.getAllShoppingListName()
}