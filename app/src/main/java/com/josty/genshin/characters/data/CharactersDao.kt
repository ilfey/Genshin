package com.josty.genshin.characters.data

import androidx.room.*

@Dao
abstract class CharactersDao {
    @Query("SELECT * FROM characters")
    abstract suspend fun findAll(): List<CharactersEntity>

    @Query("SELECT * FROM characters WHERE character_id = :id")
    abstract suspend fun find(id: Long): CharactersEntity?

    @Query("DELETE FROM characters WHERE character_id = :id")
    abstract suspend fun delete(id: Long)
}