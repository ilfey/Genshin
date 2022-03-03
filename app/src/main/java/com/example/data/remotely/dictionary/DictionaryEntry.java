package com.example.data.remotely.dictionary;

public class DictionaryEntry {

    private String word;
    private String translate;
    private String subinf;
    private String original;
    private String _mby;
    private String _by;
    private int _modified;
    private int _created;
    private String _id;

    public DictionaryEntry(String _id, String word, String translate, String subinf) {
        this._id = _id;
        this.word = word;
        this.translate = translate;
        this.subinf = subinf;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getSubinf() {
        return subinf;
    }

    public void setSubinf(String subinf) {
        this.subinf = subinf;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String get_mby() {
        return _mby;
    }

    public void set_mby(String _mby) {
        this._mby = _mby;
    }

    public String get_by() {
        return _by;
    }

    public void set_by(String _by) {
        this._by = _by;
    }

    public int get_modified() {
        return _modified;
    }

    public void set_modified(int _modified) {
        this._modified = _modified;
    }

    public int get_created() {
        return _created;
    }

    public void set_created(int _created) {
        this._created = _created;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
