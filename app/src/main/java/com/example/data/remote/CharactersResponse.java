package com.example.data.remote;

import java.util.List;

public class CharactersResponse {
    String total;

    class fields{
        class name extends Common.Field{}
        class nameeng extends Common.Field{}
        class rarity extends Common.Field{}
        class ico extends Common.Field{}
        class weapon extends Common.Field{}
        class eye extends Common.Field{}
        class fullname extends Common.Field{}
        class gender extends Common.Field{}
        class birthday extends Common.Field{}
        class region extends Common.Field{}
        class affiliation extends Common.Field{}
        class portrait extends Common.Field{}
        class desk extends Common.Field{}
    }
    class entries{
        List<CharacterEntry> entries;
        entries(List<CharacterEntry> entries){
            this.entries = entries;
        }
    }
}

class CharacterEntry{
    String name;
    String nameeng;
    String href;
    String rarity;
    class ico{
        String path;
    }

    String weapon;
    String eye;
    String fullname;
    String gender;
    String birthday;
    String region;
    String affiliation;
    class portrait{
        String path;
    }

    String _mby;
    String _by;
    int _modified;
    int _created;
    String _id;
    String desk;
}
