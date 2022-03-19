package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class GrowerorVendorList
{
    String id;
    String full_name;

    public GrowerorVendorList()
    {

    }

    public GrowerorVendorList(JSONObject jsonObject)
    {
        try {
        this.id =jsonObject.getString("id");
        this.full_name = jsonObject.getString("full_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return this.full_name;            // What to display in the Spinner list.
    }
    public GrowerorVendorList(String full_name)
    {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
