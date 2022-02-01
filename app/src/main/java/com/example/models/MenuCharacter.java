package com.example.models;

public class MenuCharacter {
    int id;
    int icon;
    String name;
    String star;

    public MenuCharacter(int id, int icon, String name, String star) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
