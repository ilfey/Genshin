package com.josty.genshin.characters

import com.josty.genshin.characters.ui.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel<CharactersViewModel>() {
        CharactersViewModel()
    }
}