package com.josty.genshin.dictionary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "dictionary",
    primaryKeys = ["word_id"]
)
class DictionaryEntity(
    @ColumnInfo(name = "word_id", index = true) val wordId: Long,
    @SerializedName("word") @ColumnInfo(name = "title", index = true) val title: String?,
    @SerializedName("translate") @ColumnInfo(name = "content", index = true) val content: String?,
    @SerializedName("subinf") @ColumnInfo(name = "description", index = true) val description: String?,
)