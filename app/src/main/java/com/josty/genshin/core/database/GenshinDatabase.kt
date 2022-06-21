package com.josty.genshin.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josty.genshin.characters.data.CharactersDao
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.dictionary.data.DictionaryDao
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.wishes.data.WishesDao
import com.josty.genshin.wishes.data.WishesEntity

@Database(
    entities = [CharactersEntity::class, DictionaryEntity::class, WishesEntity::class],
    version = 1
)
abstract class GenshinDatabase : RoomDatabase() {
    abstract val charactersDao: CharactersDao
    abstract val dictionaryDao: DictionaryDao
    abstract val wishesDao: WishesDao
}

fun buildDatabase(context: Context): GenshinDatabase =
    Room.databaseBuilder(context, GenshinDatabase::class.java, "genshin-db").build()