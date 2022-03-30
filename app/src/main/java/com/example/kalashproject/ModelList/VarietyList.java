package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class VarietyList
{
    String id;
    String variety_name;

    public VarietyList(JSONObject jsonObject)
    {
        try {
            this.id  = jsonObject.getString("id");
            this.variety_name = jsonObject.getString("variety_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String toString()
    {
        return this.variety_name;            // What to display in the Spinner list.
    }
    public VarietyList(String variety_name)
    {
        this.variety_name = variety_name;
    }

    public VarietyList(String id, String variety_name) {
        this.id = id;
        this.variety_name = variety_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVariety_name() {
        return variety_name;
    }

    public void setVariety_name(String variety_name) {
        this.variety_name = variety_name;
    }
}
