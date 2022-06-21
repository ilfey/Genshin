package com.josty.genshin.wishes.data

import retrofit2.Call
import retrofit2.http.GET

interface WishesRequests {
    @GET("./api/wishes")
    fun getWishes(): Call<List<WishesEntity>>
}