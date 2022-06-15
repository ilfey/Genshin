package com.josty.genshin.characters.data

import retrofit2.Call
import retrofit2.http.GET

interface CharactersRequests {
    @GET("./api/characters")
    fun getCharacters(): Call<List<CharactersEntity>>
}