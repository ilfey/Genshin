package com.example.data.remotely.wishes;

public class WishesResponses {
    public class Wish{
        private int id;
        private int modified_at;
        private int created_at;
        private String name;
        private String version;
        private String poster;
        private int rate_5;
        private int rate_4;

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

        public String getVersion() {
            return version;
        }

        public String getPoster() {
            return poster;
        }

        public int getRate_5() {
            return rate_5;
        }

        public int getRate_4() {
            return rate_4;
        }
    }
}
