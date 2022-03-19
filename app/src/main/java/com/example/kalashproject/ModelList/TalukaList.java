package com.example.kalashproject.ModelList;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TalukaList {

    String id;
    String state_id;
    String district_id;
    String taluka_name;

    public TalukaList(JSONObject jsonObject) {
        try {
            this.state_id = jsonObject.getString("state_id");
            this.district_id = jsonObject.getString("district_id");
            this.taluka_name = jsonObject.getString("taluka_name");
            this.id = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String toString()
    {
        return this.taluka_name;            // What to display in the Spinner list.
    }
    public TalukaList(String taluka_name)
    {
        this.taluka_name = taluka_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTaluka_name() {
        return taluka_name;
    }

    public void setTaluka_name(String taluka_name) {
        this.taluka_name = taluka_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
