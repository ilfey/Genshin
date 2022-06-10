package com.josty.genshin

import android.app.Application
import com.josty.genshin.characters.charactersModule
import com.josty.genshin.dictionary.dictionaryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GenshinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@GenshinApp)
            modules(
                charactersModule,
                dictionaryModule
            )
        }
    }
}