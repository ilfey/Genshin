package com.josty.genshin.dictionary.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "dictionary",
    primaryKeys = ["word_id"]
)
class DictionaryEntity(
    @ColumnInfo(name = "word_id", index = true) val wordId: Long,
    @ColumnInfo(name = "title", index = true) val title: String?,
    @ColumnInfo(name = "content", index = true) val content: String?,
    @ColumnInfo(name = "description", index = true) val description: String?,
)