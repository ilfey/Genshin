package com.josty.genshin.dictionary.domain

import android.util.Log
import com.josty.genshin.core.database.GenshinDatabase
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.dictionary.data.DictionaryRequests
import com.josty.genshin.dictionary.data.DictionaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DictionaryRepository(
    private val db: GenshinDatabase,
    private val retrofit: DictionaryRequests
) {
    suspend fun getAllWords(): List<DictionaryEntity> {
//        TODO add choice between local and remote database
        return suspendCoroutine { continuation ->
            retrofit.getDictionary().enqueue(object : Callback<DictionaryResponse> {
                override fun onFailure(call: Call<DictionaryResponse>, t: Throwable) {
                    Log.e("[Dictionary]", t.toString())
                }

                override fun onResponse(
                    call: Call<DictionaryResponse>,
                    response: Response<DictionaryResponse>
                ) {
                    continuation.resume(response.body()!!.entries)
                }
            })
        }
    }

    suspend fun getWord(id: Long): DictionaryEntity? = db.dictionaryDao.find(id)
}
