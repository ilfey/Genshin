package com.josty.genshin.characters.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.characters.domain.CharactersRepository
import com.josty.genshin.list.ui.ListViewModel.ListViewModel
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharactersRepository
) : ListViewModel() {
    var list = MutableLiveData<List<CharactersEntity>>()

    fun getCharacters() {
        viewModelScope.launch {
            list.value = repository.getAllCharacters()
        }
    }

    override fun onRefresh() = getCharacters()
}