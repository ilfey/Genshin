package com.josty.genshin.wishes

import com.josty.genshin.wishes.data.WishesRequests
import com.josty.genshin.wishes.domain.WishesRepository
import com.josty.genshin.wishes.ui.WishesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val wishesModule
    get() = module {
        factory {
            WishesRepository(get(), get<Retrofit>().create(WishesRequests::class.java))
        }

        viewModel {
            WishesViewModel(get())
        }
    }