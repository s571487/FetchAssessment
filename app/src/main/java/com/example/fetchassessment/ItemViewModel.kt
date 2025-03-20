package com.example.fetchassessment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<Map<Int, List<Item>>?>()
    val items: MutableLiveData<Map<Int, List<Item>>?> get() = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getItems()
                if (response.isSuccessful) {
                    val filteredItems = response.body()?.filter { !it.name.isNullOrBlank() }
                    val groupedItems = filteredItems?.groupBy { it.listId }?.mapValues { (_, items) ->
                        items.sortedBy { it.name }
                    }
                    val sortedGroupedItems = groupedItems?.toSortedMap()
                    _items.value = sortedGroupedItems
                }
            } catch (e: Exception) {

            }
        }
    }
}
