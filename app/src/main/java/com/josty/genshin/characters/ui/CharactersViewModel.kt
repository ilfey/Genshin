package com.josty.genshin.characters.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josty.genshin.characters.adapter.CharactersListItem

class CharactersViewModel: ViewModel() {
    var list = MutableLiveData<List<CharactersListItem>>()

    fun getCharacters(): Unit = TODO("create getCharactersItems") // list.value = ...
}