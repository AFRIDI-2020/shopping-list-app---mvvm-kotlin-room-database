package com.example.shopping_list_app.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopping_list_app.repositories.ItemRepository
import com.example.shopping_list_app.viewModels.ItemViewModel

@Suppress("UNCHECKED_CAST")
class ItemViewModelFactory(val itemRepository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemViewModel(itemRepository) as T
    }
}