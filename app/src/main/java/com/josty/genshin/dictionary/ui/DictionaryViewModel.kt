package com.josty.genshin.dictionary.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.dictionary.domain.DictionaryRepository
import com.josty.genshin.list.ui.ListViewModel.ListViewModel
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val repository: DictionaryRepository
) : ListViewModel() {
    var list = MutableLiveData<List<DictionaryEntity>>()

    fun getDictionary() {
        viewModelScope.launch {
            list.value = repository.getAllWords()
        }
    }

    override fun onRefresh() = getDictionary()
}