package com.example.data.remotely.characters;

public class CharactersRequests {
    public class GetCharacters {
        public int from;
        public int to;

        public GetCharacters(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public class GetCharacter {
        public int id;

        public GetCharacter(int id) {
            this.id = id;
        }
    }

    public class PostCharacter {
        public int id;
        public String name;
        public int rarity;
        public String name_en;
        public String full_name;
        public String card;
        public String weapon;
        public String eye;
        public String sex;
        public String birthday;
        public String region;
        public String affilaition;
        public String portrait;
        public String description;

        public PostCharacter(int id, String name, int rarity, String name_en, String full_name, String card, String weapon, String eye, String sex, String birthday, String region, String affilaition, String portrait, String description) {
            this.id = id;
            this.name = name;
            this.rarity = rarity;
            this.name_en = name_en;
            this.full_name = full_name;
            this.card = card;
            this.weapon = weapon;
            this.eye = eye;
            this.sex = sex;
            this.birthday = birthday;
            this.region = region;
            this.affilaition = affilaition;
            this.portrait = portrait;
            this.description = description;
        }
    }

    public class DeleteCharacter extends GetCharacter {
        public DeleteCharacter(int id) {
            super(id);
        }
    }

    public class PatchCharacter extends PostCharacter {
        public PatchCharacter(int id, String name, int rarity, String name_en, String full_name, String card, String weapon, String eye, String sex, String birthday, String region, String affilaition, String portrait, String description) {
            super(id, name, rarity, name_en, full_name, card, weapon, eye, sex, birthday, region, affilaition, portrait, description);
        }
    }
}
