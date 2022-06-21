package com.josty.genshin.wishes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "wishes",
    primaryKeys = ["wish_id"]
)
class WishesEntity(
//    TODO fix backend
    @ColumnInfo(name = "wish_id", index = true) val wishId: Long,
    @SerializedName("title") @ColumnInfo(name = "title", index = true) val title: String?,
    @SerializedName("poster") @ColumnInfo(name = "icon", index = true) val icon: String?
)