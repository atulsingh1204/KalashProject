package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class FamilyCodeList {

    String id;
    String family_code;

    public FamilyCodeList(){

    }

    public FamilyCodeList(JSONObject jsonObject) {

        try {
            this.id = jsonObject.getString("id");
            this.family_code = jsonObject.getString("family_code");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return this.family_code;            // What to display in the Spinner list.

    }

    public FamilyCodeList(String family_code)
    {
        this.family_code = family_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamily_code() {
        return family_code;
    }

    public void setFamily_code(String family_code) {
        this.family_code = family_code;
    }
}
