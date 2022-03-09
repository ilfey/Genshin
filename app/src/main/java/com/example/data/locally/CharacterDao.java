package com.example.data.locally;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CharacterDao {
    @Delete
    void deleteCharacter(Character character);

    @Query("SELECT * FROM CHARACTERS")
    List<Character> getCharacters();

    @Query("SELECT * FROM CHARACTERS WHERE id IN (:IDs)")
    List<Character> getCharactersByIDs(int[] IDs);

    @Query("SELECT * FROM CHARACTERS WHERE id LIKE :id LIMIT 1")
    Character getCharacterByID(int id);

    @Insert
    void setCharacters(Character... character);

}
