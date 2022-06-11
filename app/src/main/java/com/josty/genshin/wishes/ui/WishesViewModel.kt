package com.josty.genshin.wishes.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josty.genshin.wishes.adapter.WishesListItem

class WishesViewModel : ViewModel() {
    var list = MutableLiveData<List<WishesListItem>>()

    fun getWishes(): Unit = TODO("create getWishesItems") // list.value = ...
}