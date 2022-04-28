package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class FQ_flag_list
{
    String id;
    String flag_name;

    public FQ_flag_list(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.flag_name = jsonObject.getString("flag_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return this.flag_name;            // What to display in the Spinner list.
    }
    public FQ_flag_list(String flag_name)
    {
        this.flag_name = flag_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag_name() {
        return flag_name;
    }

    public void setFlag_name(String flag_name) {
        this.flag_name = flag_name;
    }
}
