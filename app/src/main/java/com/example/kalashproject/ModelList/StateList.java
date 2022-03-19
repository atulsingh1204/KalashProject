package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class StateList {

    String id, state_name;

    public StateList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.state_name = jsonObject.getString("state_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String toString()
    {
        return this.state_name;            // What to display in the Spinner list.
    }
    public StateList(String state_name)
    {
        this.state_name = state_name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
