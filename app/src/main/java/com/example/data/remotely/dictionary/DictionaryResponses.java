package com.example.data.remotely.dictionary;

public class DictionaryResponses {
    public class Word{
        private int id;
        private int modified;
        private int created_at;
        private String word;
        private String translate;
        private String subinf;
        private String original;

        public int getId() {
            return id;
        }

        public int getModified() {
            return modified;
        }

        public int getCreated_at() {
            return created_at;
        }

        public String getWord() {
            return word;
        }

        public String getTranslate() {
            return translate;
        }

        public String getSubinf() {
            return subinf;
        }

        public String getOriginal() {
            return original;
        }
    }
}