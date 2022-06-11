package com.josty.genshin.wishes

import com.josty.genshin.wishes.ui.WishesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishesModule = module{
    viewModel(){
        WishesViewModel()
    }
}