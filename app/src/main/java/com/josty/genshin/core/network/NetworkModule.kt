package com.josty.genshin.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule
    get() = module {
        single {
            val client = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()

            Retrofit.Builder().apply {
                client(client)
                baseUrl("http://api.josty.ml/")
                addConverterFactory(GsonConverterFactory.create())
            }.build()
        }
    }