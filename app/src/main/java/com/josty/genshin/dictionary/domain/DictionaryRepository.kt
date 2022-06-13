package com.josty.genshin.dictionary.domain

import com.josty.genshin.core.database.GenshinDatabase
import com.josty.genshin.dictionary.data.DictionaryEntity
import okhttp3.*

class DictionaryRepository(
    private val db: GenshinDatabase,
    private val okHttp: OkHttpClient
) {
    suspend fun getAllWords(): List<DictionaryEntity> {
//        TODO add json converter for remotely database and choice

        /*val request = Request.Builder()
            .get()
            .header("Content-Type", "application/json")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36")
            .url("https://sushicat.pp.ua/api/genshin/api/collections/get/dict?token=a4191046104f8f3674f788e804c2d0")
        val call = okHttp.newCall(request.build()).enqueue(
            object : Callback{
                override fun onFailure(call: Call, e: IOException) {}

                override fun onResponse(call: Call, response: Response) {
                    entries = Gson().fromJson(response.body.toString(), mResponse::class.java).entries
                }
            }
        )*/
        return db.dictionaryDao.findAll()
    }

    suspend fun getWord(id: Long): DictionaryEntity? = db.dictionaryDao.find(id)
}
