package com.example.data.remote.characters;

import java.util.List;

public class CharactersResponse {
    public String total;
    public Fields fields;
    public List<CharacterEntry> entries;

}
class Fields{
    public Common.Field name;
    public Common.Field nameeng;
    public Common.Field rarity;
    public Common.Field ico;
    public Common.Field weapon;
    public Common.Field eye;
    public Common.Field fullname;
    public Common.Field gender;
    public Common.Field birthday;
    public Common.Field region;
    public Common.Field affiliation;
    public Common.Field portrait;
    public Common.Field desk;
}
