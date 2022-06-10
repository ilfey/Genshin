package com.josty.genshin.dictionary.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josty.genshin.dictionary.adapter.DictionaryListItem

class DictionaryViewModel : ViewModel() {
    var list = MutableLiveData<List<DictionaryListItem>>()

    fun getDictionary(): Unit = TODO("create getDictionaryItems") // list.value = ...
}