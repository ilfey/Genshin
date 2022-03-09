package com.example.data.locally;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Character.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CharacterDao getCharacterDao();
}
