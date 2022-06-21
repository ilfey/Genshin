package com.josty.genshin.wishes.domain

import android.util.Log
import com.josty.genshin.core.database.GenshinDatabase
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.wishes.data.WishesEntity
import com.josty.genshin.wishes.data.WishesRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WishesRepository(
    private val db: GenshinDatabase,
    private val retrofit: WishesRequests
) {
    suspend fun getAllWishes(): List<WishesEntity>? {
//        TODO add choice between local and remote database
        return suspendCoroutine { continuation ->
            retrofit.getWishes().enqueue(object : Callback<List<WishesEntity>> {
                override fun onFailure(call: Call<List<WishesEntity>>, t: Throwable) {
                    Log.e("[Dictionary]", t.toString())
                    // TODO add error handler
                    continuation.resume(listOf())
                }

                override fun onResponse(
                    call: Call<List<WishesEntity>>,
                    response: Response<List<WishesEntity>>
                ) {
                    if (response.body() == null)
                        continuation.resume(listOf())
                    else continuation.resume(response.body())
                }
            })
        }
    }
    suspend fun getWish(id: Long): WishesEntity? = db.wishesDao.find(id)
}