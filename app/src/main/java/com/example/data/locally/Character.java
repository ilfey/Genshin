package com.example.data.locally;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "CHARACTERS")
public class Character {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "rarity")
    public int rarity;

    @ColumnInfo(name = "name_en")
    public String name_en;

    @ColumnInfo(name = "full_name")
    public String full_name;

    @ColumnInfo(name = "card")
    public String card;

    @ColumnInfo(name = "weapon")
    public String weapon;

    @ColumnInfo(name = "eye")
    public String eye;

    @ColumnInfo(name = "sex")
    public String sex;

    @ColumnInfo(name = "birthday")
    public String birthday;

    @ColumnInfo(name = "region")
    public String region;

    @ColumnInfo(name = "affiliation")
    public String affiliation;

    @ColumnInfo(name = "protrait")
    public String protrait;

    @ColumnInfo(name = "description")
    public String description;
}
