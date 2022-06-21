package com.josty.genshin.wishes.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josty.genshin.list.ui.ListViewModel.ListViewModel
import com.josty.genshin.wishes.data.WishesEntity
import com.josty.genshin.wishes.domain.WishesRepository
import kotlinx.coroutines.launch

class WishesViewModel(
    private val repository: WishesRepository
) : ListViewModel() {
    var list = MutableLiveData<List<WishesEntity>>()

    fun getWishes() {
        viewModelScope.launch {
            list.value = repository.getAllWishes()
        }
    }

    override fun onRefresh() = getWishes()
}