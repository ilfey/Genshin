package com.example.data.remotely.gacha;

import com.example.data.remotely.Common;
import com.google.gson.annotations.SerializedName;

public class GachaEntry {
    private String  ver;
    private String name;
    private Common.ObjectWithStringPath img;
    @SerializedName("5star")
    private String star;
    @SerializedName("4star")
    private String star1;
    @SerializedName("4star2")
    private String star2;
    @SerializedName("4star3")
    private String star3;
    private String _mby;
    private String _by;
    private int _modified;
    private int _created;
    private String _id;
    private String ch5star;
    private String ch4star1;
    private String ch4star2;
    private String ch4star3;

    public GachaEntry(String _id, String img, String name, String star, String star1, String star2, String star3){
        this._id = _id;
        this.img = null;
        this.name = name;
        this.star = star;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img.path;
    }

    public void setImg(Common.ObjectWithStringPath img) {
        this.img = img;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStar1() {
        return star1;
    }

    public void setStar1(String star1) {
        this.star1 = star1;
    }

    public String getStar2() {
        return star2;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public String getStar3() {
        return star3;
    }

    public void setStar3(String star3) {
        this.star3 = star3;
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

    public String getCh5star() {
        return ch5star;
    }

    public void setCh5star(String ch5star) {
        this.ch5star = ch5star;
    }

    public String getCh4star1() {
        return ch4star1;
    }

    public void setCh4star1(String ch4star1) {
        this.ch4star1 = ch4star1;
    }

    public String getCh4star2() {
        return ch4star2;
    }

    public void setCh4star2(String ch4star2) {
        this.ch4star2 = ch4star2;
    }

    public String getCh4star3() {
        return ch4star3;
    }

    public void setCh4star3(String ch4star3) {
        this.ch4star3 = ch4star3;
    }
}
