package com.josty.genshin.characters.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "characters",
    primaryKeys = ["character_id"]
)
class CharactersEntity(
    @ColumnInfo(name = "character_id", index = true) val characterId: Long,
    @SerializedName("name") @ColumnInfo(name = "name", index = true) val name: String?,
    @SerializedName("name_en") @ColumnInfo(name = "name_en", index = true) val nameEn: String?,
    @SerializedName("full_name") @ColumnInfo(name = "full_name", index = true) val fullName: String?,
    @SerializedName("rarity") @ColumnInfo(name = "rarity", index = true) val rarity: Int?,
    @SerializedName("card") @ColumnInfo(name = "card", index = true) val card: String?,
    @SerializedName("weapon") @ColumnInfo(name = "weapon", index = true) val weapon: String?,
    @SerializedName("eye") @ColumnInfo(name = "eye", index = true) val eye: String?,
    @SerializedName("sex") @ColumnInfo(name = "sex", index = true) val sex: String?,
    @SerializedName("birthday") @ColumnInfo(name = "birthday", index = true) val birthday: String?,
    @SerializedName("region") @ColumnInfo(name = "region", index = true) val region: String?,
    @SerializedName("affiliation") @ColumnInfo(name = "affiliation", index = true) val affiliation: String?,
    @SerializedName("portrait") @ColumnInfo(name = "portrait", index = true) val portrait: String?,
    @SerializedName("description") @ColumnInfo(name = "description", index = true) val description: String?,
)