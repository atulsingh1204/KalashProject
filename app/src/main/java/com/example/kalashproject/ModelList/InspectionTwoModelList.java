package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class InspectionTwoModelList
{
    String id;
    String order_id;
    String gps_location;
    String ot_plant_in_female;
    String ot_plant_in_male;
    String female_date_of_roughing;
    String male_date_of_roughing;
    String disease_plant_in_male;
    String details;
    String pld_acre;
    String reason_of_pld;
    String rejected_acre;
    String reason_of_rejected;
    String expected_date_of_dispatch;
    String flag_name;
    String breeder_remark;

    public InspectionTwoModelList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.order_id = jsonObject.getString("order_id");
            this.gps_location = jsonObject.getString("gps_location");
            this.ot_plant_in_female = jsonObject.getString("ot_plant_in_female");
            this.ot_plant_in_male = jsonObject.getString("ot_plant_in_male");
            this.female_date_of_roughing = jsonObject.getString("female_date_of_roughing");
            this.male_date_of_roughing = jsonObject.getString("male_date_of_roughing");
            this.disease_plant_in_male = jsonObject.getString("disease_plant_in_male");
            this.details = jsonObject.getString("details");
            this.pld_acre = jsonObject.getString("pld_acre");
            this.reason_of_pld = jsonObject.getString("reason_of_pld");
            this.rejected_acre = jsonObject.getString("rejected_acre");
            this.reason_of_rejected = jsonObject.getString("reason_of_rejected");
            this.expected_date_of_dispatch = jsonObject.getString("expected_date_of_dispatch");
            this.flag_name = jsonObject.getString("flag_name");
            this.breeder_remark = jsonObject.getString("breeder_remark");

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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getGps_location() {
        return gps_location;
    }

    public void setGps_location(String gps_location) {
        this.gps_location = gps_location;
    }

    public String getOt_plant_in_female() {
        return ot_plant_in_female;
    }

    public void setOt_plant_in_female(String ot_plant_in_female) {
        this.ot_plant_in_female = ot_plant_in_female;
    }

    public String getOt_plant_in_male() {
        return ot_plant_in_male;
    }

    public void setOt_plant_in_male(String ot_plant_in_male) {
        this.ot_plant_in_male = ot_plant_in_male;
    }

    public String getFemale_date_of_roughing() {
        return female_date_of_roughing;
    }

    public void setFemale_date_of_roughing(String female_date_of_roughing) {
        this.female_date_of_roughing = female_date_of_roughing;
    }

    public String getMale_date_of_roughing() {
        return male_date_of_roughing;
    }

    public void setMale_date_of_roughing(String male_date_of_roughing) {
        this.male_date_of_roughing = male_date_of_roughing;
    }

    public String getDisease_plant_in_male() {
        return disease_plant_in_male;
    }

    public void setDisease_plant_in_male(String disease_plant_in_male) {
        this.disease_plant_in_male = disease_plant_in_male;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPld_acre() {
        return pld_acre;
    }

    public void setPld_acre(String pld_acre) {
        this.pld_acre = pld_acre;
    }

    public String getReason_of_pld() {
        return reason_of_pld;
    }

    public void setReason_of_pld(String reason_of_pld) {
        this.reason_of_pld = reason_of_pld;
    }

    public String getRejected_acre() {
        return rejected_acre;
    }

    public void setRejected_acre(String rejected_acre) {
        this.rejected_acre = rejected_acre;
    }

    public String getReason_of_rejected() {
        return reason_of_rejected;
    }

    public void setReason_of_rejected(String reason_of_rejected) {
        this.reason_of_rejected = reason_of_rejected;
    }

    public String getExpected_date_of_dispatch() {
        return expected_date_of_dispatch;
    }

    public void setExpected_date_of_dispatch(String expected_date_of_dispatch) {
        this.expected_date_of_dispatch = expected_date_of_dispatch;
    }

    public String getFlag_name() {
        return flag_name;
    }

    public void setFlag_name(String flag_name) {
        this.flag_name = flag_name;
    }

    public String getBreeder_remark() {
        return breeder_remark;
    }

    public void setBreeder_remark(String breeder_remark) {
        this.breeder_remark = breeder_remark;
    }
}
