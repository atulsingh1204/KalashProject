package com.example.kalashproject.ModelList;

public class NewVillageList {

    String id;
    String village_name;

    public NewVillageList() {
    }

    public NewVillageList(String id, String village_name) {
        this.id = id;
        this.village_name = village_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }
}
