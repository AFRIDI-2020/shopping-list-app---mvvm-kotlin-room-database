package com.example.shopping_list_app.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopping_list_app.repositories.ShoppingListRepository
import com.example.shopping_list_app.viewModels.ShoppingListViewModel

@Suppress("UNCHECKED_CAST")
class ShoppingListViewModelFactory(val shoppingListRepository: ShoppingListRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(shoppingListRepository) as T
    }
}