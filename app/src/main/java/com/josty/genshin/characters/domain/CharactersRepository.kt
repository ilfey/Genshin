package com.josty.genshin.characters.domain

import android.util.Log
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.characters.data.CharactersRequests
import com.josty.genshin.core.database.GenshinDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CharactersRepository(
    private val db: GenshinDatabase,
    private val retrofit: CharactersRequests
) {
    suspend fun getAllCharacters(): List<CharactersEntity>? {
//        TODO add choice between local and remote database
        return suspendCoroutine { continuation ->
            retrofit.getCharacters().enqueue(object : Callback<List<CharactersEntity>> {
                override fun onFailure(call: Call<List<CharactersEntity>>, t: Throwable) {
                    Log.e("[Dictionary]", t.toString())
                    // TODO add error handler
                    continuation.resume(listOf())
                }

                override fun onResponse(
                    call: Call<List<CharactersEntity>>,
                    response: Response<List<CharactersEntity>>
                ) {
                    if (response.body() == null)
                        continuation.resume(listOf())
                    else continuation.resume(response.body())
                }
            })
        }
    }

    suspend fun getWord(id: Long): CharactersEntity? = db.charactersDao.find(id)
}