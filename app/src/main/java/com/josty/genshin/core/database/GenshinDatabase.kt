package com.josty.genshin.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josty.genshin.characters.data.CharactersDao
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.dictionary.data.DictionaryDao
import com.josty.genshin.dictionary.data.DictionaryEntity

@Database(
    entities = [CharactersEntity::class, DictionaryEntity::class], // TODO add more Entities and Data Access Objects
    version = 1
)
abstract class GenshinDatabase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
    abstract val dictionaryDao: DictionaryDao
}

fun buildDatabase(context: Context): GenshinDatabase =
    Room.databaseBuilder(context, GenshinDatabase::class.java, "genshin-db").build()