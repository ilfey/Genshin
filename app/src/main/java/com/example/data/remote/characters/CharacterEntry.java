package com.example.data.remote.characters;

public class CharacterEntry {
    private String name;
    private String nameeng;
    private String href;
    private String rarity;
    private ObjectWithStringPath ico;
    private String weapon;
    private String eye;
    private String fullname;
    private String gender;
    private String birthday;
    private String region;
    private String affiliation;
    private ObjectWithStringPath portrait;
    private String _mby;
    private String _by;
    private int _modified;
    private int _created;
    private String _id;
    private String desk;

    public CharacterEntry(String _id, String name, String rarity, ObjectWithStringPath ico) {
        this._id = _id;
        this.name = name;
        this.rarity = rarity;
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameeng() {
        return nameeng;
    }

    public void setNameeng(String nameeng) {
        this.nameeng = nameeng;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getIco() {
        return ico.path;
    }

    public void setIco(ObjectWithStringPath ico) {
        this.ico = ico;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public ObjectWithStringPath getPortrait() {
        return portrait;
    }

    public void setPortrait(ObjectWithStringPath portrait) {
        this.portrait = portrait;
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

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}

class ObjectWithStringPath{
    String path;
}