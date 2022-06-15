package com.josty.genshin.characters

import com.josty.genshin.characters.data.CharactersRequests
import com.josty.genshin.characters.domain.CharactersRepository
import com.josty.genshin.characters.ui.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val charactersModule = module {
    factory {
        CharactersRepository(get(), get<Retrofit>().create(CharactersRequests::class.java))
    }
    viewModel() {
        CharactersViewModel(get())
    }
}