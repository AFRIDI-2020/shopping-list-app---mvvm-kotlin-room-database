package com.example.shopping_list_app.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping_list_app.database.item.Item
import com.example.shopping_list_app.repositories.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(val itemRepository: ItemRepository) : ViewModel() {

    fun upsertItem(item: Item) = viewModelScope.launch {
        itemRepository.upsertItem(item)
    }

    fun deleteItem(item: Item) = viewModelScope.launch {
        itemRepository.deleteItem(item)
    }

    fun getAllItem(listId : Int) = itemRepository.getAllItem(listId)
}