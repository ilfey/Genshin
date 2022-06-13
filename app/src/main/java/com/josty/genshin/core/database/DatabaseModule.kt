package com.josty.genshin.core.database

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule
    get() = module {
        single {
            buildDatabase(androidContext())
        }
    }