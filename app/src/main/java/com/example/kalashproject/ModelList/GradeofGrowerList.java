package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class GradeofGrowerList
{
    String id;
    String grade_of_grower_name;

    public GradeofGrowerList()
    {

    }

    public GradeofGrowerList(JSONObject jsonObject)
    {
        try {
        this.id = jsonObject.getString("id");
        this.grade_of_grower_name = jsonObject.getString("grade_of_grower_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String toString()
    {
        return this.grade_of_grower_name;            // What to display in the Spinner list.
    }
    public GradeofGrowerList(String grade_of_grower_name)
    {
        this.grade_of_grower_name = grade_of_grower_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade_of_grower_name() {
        return grade_of_grower_name;
    }

    public void setGrade_of_grower_name(String grade_of_grower_name) {
        this.grade_of_grower_name = grade_of_grower_name;
    }
}
