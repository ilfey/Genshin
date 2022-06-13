package com.josty.genshin.dictionary.data

import androidx.room.*

@Dao
abstract class DictionaryDao {
    @Query("SELECT * FROM dictionary")
    abstract suspend fun findAll(): List<DictionaryEntity>

    @Query("SELECT * FROM dictionary WHERE word_id = :id")
    abstract suspend fun find(id: Long): DictionaryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(favourite: DictionaryEntity)

    @Update
    abstract suspend fun update(favourite: DictionaryEntity): Int

    @Query("DELETE FROM dictionary WHERE word_id = :id")
    abstract suspend fun delete(id: Long)
}