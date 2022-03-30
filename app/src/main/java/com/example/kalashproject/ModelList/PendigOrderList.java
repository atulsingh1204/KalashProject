package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class PendigOrderList {

    String growervendorid;
    String full_name;
    String grower_or_vendor;
    String distance;
    String crop_name;
    String variety_name;
    String land_holding;
    String source_of_irrigation_name;
    String grade_of_grower_name;
    String crop_details;
    String previous_company;
    String last_crop_taken;


    public PendigOrderList(JSONObject jsonObject) {
        try {
            this.growervendorid = jsonObject.getString("growervendorid");
            this.full_name = jsonObject.getString("full_name");
            this.grower_or_vendor = jsonObject.getString("grower_or_vendor");
            this.distance = jsonObject.getString("distance");
            this.crop_name = jsonObject.getString("crop_name");
            this.variety_name = jsonObject.getString("variety_name");
            this.land_holding = jsonObject.getString("land_holding");
            this.source_of_irrigation_name = jsonObject.getString("source_of_irrigation_name");
            this.grade_of_grower_name = jsonObject.getString("grade_of_grower_name");
            this.crop_details = jsonObject.getString("crop_details");
            this.previous_company = jsonObject.getString("previous_company");
            this.last_crop_taken = jsonObject.getString("last_crop_taken");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getGrowervendorid() {
        return growervendorid;
    }

    public void setGrowervendorid(String growervendorid) {
        this.growervendorid = growervendorid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGrower_or_vendor() {
        return grower_or_vendor;
    }

    public void setGrower_or_vendor(String grower_or_vendor) {
        this.grower_or_vendor = grower_or_vendor;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getVariety_name() {
        return variety_name;
    }

    public void setVariety_name(String variety_name) {
        this.variety_name = variety_name;
    }

    public String getLand_holding() {
        return land_holding;
    }

    public void setLand_holding(String land_holding) {
        this.land_holding = land_holding;
    }

    public String getSource_of_irrigation_name() {
        return source_of_irrigation_name;
    }

    public void setSource_of_irrigation_name(String source_of_irrigation_name) {
        this.source_of_irrigation_name = source_of_irrigation_name;
    }

    public String getGrade_of_grower_name() {
        return grade_of_grower_name;
    }

    public void setGrade_of_grower_name(String grade_of_grower_name) {
        this.grade_of_grower_name = grade_of_grower_name;
    }

    public String getCrop_details() {
        return crop_details;
    }

    public void setCrop_details(String crop_details) {
        this.crop_details = crop_details;
    }

    public String getPrevious_company() {
        return previous_company;
    }

    public void setPrevious_company(String previous_company) {
        this.previous_company = previous_company;
    }

    public String getLast_crop_taken() {
        return last_crop_taken;
    }

    public void setLast_crop_taken(String last_crop_taken) {
        this.last_crop_taken = last_crop_taken;
    }

}