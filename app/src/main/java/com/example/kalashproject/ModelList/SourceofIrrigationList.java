package com.example.kalashproject.ModelList;

import org.json.JSONObject;

public class SourceofIrrigationList
{
    String id;
    String source_of_irrigation_name;

    public SourceofIrrigationList()
    {

    }

    public SourceofIrrigationList(JSONObject jsonObject)
    {
        try {
            this.id = jsonObject.getString("id");
            this.source_of_irrigation_name = jsonObject.getString("source_of_irrigation_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return this.source_of_irrigation_name;            // What to display in the Spinner list.
    }
    public SourceofIrrigationList(String source_of_irrigation_name)
    {
        this.source_of_irrigation_name = source_of_irrigation_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource_of_irrigation_name() {
        return source_of_irrigation_name;
    }

    public void setSource_of_irrigation_name(String source_of_irrigation_name) {
        this.source_of_irrigation_name = source_of_irrigation_name;
    }
}
