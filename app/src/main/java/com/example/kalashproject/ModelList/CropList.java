package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class CropList {
    String id;
    String crop_name;

    public CropList(JSONObject jsonObject)
    {
        try {
            this.id  = jsonObject.getString("id");
            this.crop_name = jsonObject.getString("crop_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }
}
