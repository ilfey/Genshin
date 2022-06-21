package com.josty.genshin.wishes.data

import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class WishesDao {
    @Query("SELECT * FROM wishes")
    abstract suspend fun findAll(): List<WishesEntity>

    @Query("SELECT * FROM wishes WHERE wish_id = :id")
    abstract suspend fun find(id: Long): WishesEntity?

    @Query("DELETE FROM wishes WHERE wish_id = :id")
    abstract suspend fun delete(id: Long)
}