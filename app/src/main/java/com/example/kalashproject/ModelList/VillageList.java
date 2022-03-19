package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class VillageList
{

   private String id;
   private String state_id;
   private String district_id;
   private String taluka_id;
   private String village_name;

    public VillageList(JSONObject jsonObject)
    {
        try {
            this.id = jsonObject.getString("id");
            this.state_id = jsonObject.getString("state_id");
            this.district_id = jsonObject.getString("district_id");
            this.taluka_id = jsonObject.getString("taluka_id");
            this.village_name =jsonObject.getString("village_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String toString()
    {
        return this.village_name;            // What to display in the Spinner list.
    }
    public VillageList(String village_name)
    {
        this.village_name = village_name;
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

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTaluka_id() {
        return taluka_id;
    }

    public void setTaluka_id(String taluka_id) {
        this.taluka_id = taluka_id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }
}
