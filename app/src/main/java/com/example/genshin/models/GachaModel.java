package com.example.genshin.models;

public class GachaModel {
    int id;
    int icon;
    String title;
    String skills;

    public GachaModel(int id, int icon, String title, String skills) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.skills = skills;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
