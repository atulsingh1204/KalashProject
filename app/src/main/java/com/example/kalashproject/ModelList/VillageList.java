package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class VillageList {

    String id;
    String village_name;

    public VillageList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.village_name = jsonObject.getString("village_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return this.village_name;            // What to display in the Spinner list.
    }

    public VillageList(String village_name) {
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
