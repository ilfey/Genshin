package com.example.data.locally;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "CHARACTERS")
public class Character {
    @PrimaryKey(autoGenerate = true)
    public int id;

//    @ColumnInfo(name = "")
}
