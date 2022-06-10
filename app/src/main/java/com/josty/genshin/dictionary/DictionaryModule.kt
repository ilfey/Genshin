package com.josty.genshin.dictionary

import com.josty.genshin.dictionary.ui.DictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dictionaryModule = module {
    viewModel<DictionaryViewModel> {
        DictionaryViewModel()
    }
}