package com.josty.genshin.dictionary.domain

import android.util.Log
import com.josty.genshin.core.database.GenshinDatabase
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.dictionary.data.DictionaryRequests
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
            retrofit.getDictionary().enqueue(object : Callback<List<DictionaryEntity>> {
                override fun onFailure(call: Call<List<DictionaryEntity>>, t: Throwable) {
                    Log.e("[Dictionary]", t.toString())
                    // TODO add error handler
                }

                override fun onResponse(
                    call: Call<List<DictionaryEntity>>,
                    response: Response<List<DictionaryEntity>>
                ) {
                    continuation.resume(response.body()!!)
                }
            })
        }
    }
    suspend fun getWord(id: Long): DictionaryEntity? = db.dictionaryDao.find(id)
}
