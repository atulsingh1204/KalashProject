package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class DistrictList {

    private String id;
    private String state_id;
    private String district_name;

    public DistrictList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.state_id = jsonObject.getString("state_id");
            this.district_name = jsonObject.getString("district_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String toString()
    {
        return this.district_name;            // What to display in the Spinner list.
    }
    public DistrictList(String district_name)
    {
        this.district_name = district_name;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
}
