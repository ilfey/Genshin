package com.josty.genshin.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule
    get() = module {
//        TODO choice okhttp or retrofit
        single {
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()
        }
        single {
            Retrofit.Builder().apply {
                client(get())
                baseUrl("http://api.josty.ml/")
                addConverterFactory(GsonConverterFactory.create())
            }.build()
        }
    }