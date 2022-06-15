package com.josty.genshin.characters.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josty.genshin.characters.adapter.CharactersListItem
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.characters.domain.CharactersRepository
import com.josty.genshin.dictionary.data.DictionaryEntity
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharactersRepository
): ViewModel() {
    var list = MutableLiveData<List<CharactersEntity>>()

    fun getCharacters() {
        viewModelScope.launch {
            list.value = repository.getAllCharacters()
        }
    }
}