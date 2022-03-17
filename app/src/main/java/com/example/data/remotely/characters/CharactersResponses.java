package com.example.data.remotely.characters;

public class CharactersResponses {
    public class Character {
        private int id;
        private int modified_at;
        private int created_at;
        private String name;
        private int rarity;
        private String name_en;
        private String full_name;
        private String card;
        private String weapon;
        private String eye;
        private String sex;
        private String birthday;
        private String region;
        private String affiliation;
        private String portrait;
        private String description;

        public int getId() {
            return id;
        }

        public int getModified_at() {
            return modified_at;
        }

        public int getCreated_at() {
            return created_at;
        }

        public String getName() {
            return name;
        }

        public int getRarity() {
            return rarity;
        }

        public String getName_en() {
            return name_en;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getCard() {
            return card;
        }

        public String getWeapon() {
            return weapon;
        }

        public String getEye() {
            return eye;
        }

        public String getSex() {
            return sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getRegion() {
            return region;
        }

        public String getAffiliation() {
            return affiliation;
        }

        public String getPortrait() {
            return portrait;
        }

        public String getDescription() {
            return description;
        }
    }
}
