package com.josty.genshin.dictionary.data

import retrofit2.Call
import retrofit2.http.*


interface DictionaryRequests {
    @GET("./api/dictionary")
    fun getDictionary(): Call<List<DictionaryEntity>>
}