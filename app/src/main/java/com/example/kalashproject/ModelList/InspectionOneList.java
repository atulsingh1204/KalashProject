package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class InspectionOneList
{
    String id;
    String order_id;
    String male_sowing_date;
    String female_sowing_date;
    String male_row;
    String female_row;
    String male_plant_in_row;
    String female_plant_in_row;
    String total_male;
    String total_female;
    String is_isolation;
    String pld_acre;
    String reason_of_pld;
    String rejected_acre;
    String reason_of_rejected;
    String expected_date_of_dispatch;
    String flag_name;
    String breeder_remark;


    public InspectionOneList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.order_id = jsonObject.getString("order_id");
            this.male_sowing_date = jsonObject.getString("male_sowing_date");
            this.female_sowing_date = jsonObject.getString("female_sowing_date");
            this.male_row = jsonObject.getString("male_row");
            this.female_row = jsonObject.getString("female_row");
            this.male_plant_in_row = jsonObject.getString("male_plant_in_row");
            this.female_plant_in_row = jsonObject.getString("female_plant_in_row");
            this.total_male = jsonObject.getString("total_male");
            this.total_female = jsonObject.getString("total_female");
            this.is_isolation = jsonObject.getString("is_isolation");
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

    public String getMale_sowing_date() {
        return male_sowing_date;
    }

    public void setMale_sowing_date(String male_sowing_date) {
        this.male_sowing_date = male_sowing_date;
    }

    public String getFemale_sowing_date() {
        return female_sowing_date;
    }

    public void setFemale_sowing_date(String female_sowing_date) {
        this.female_sowing_date = female_sowing_date;
    }

    public String getMale_row() {
        return male_row;
    }

    public void setMale_row(String male_row) {
        this.male_row = male_row;
    }

    public String getFemale_row() {
        return female_row;
    }

    public void setFemale_row(String female_row) {
        this.female_row = female_row;
    }

    public String getMale_plant_in_row() {
        return male_plant_in_row;
    }

    public void setMale_plant_in_row(String male_plant_in_row) {
        this.male_plant_in_row = male_plant_in_row;
    }

    public String getFemale_plant_in_row() {
        return female_plant_in_row;
    }

    public void setFemale_plant_in_row(String female_plant_in_row) {
        this.female_plant_in_row = female_plant_in_row;
    }

    public String getTotal_male() {
        return total_male;
    }

    public void setTotal_male(String total_male) {
        this.total_male = total_male;
    }

    public String getTotal_female() {
        return total_female;
    }

    public void setTotal_female(String total_female) {
        this.total_female = total_female;
    }

    public String getIs_isolation() {
        return is_isolation;
    }

    public void setIs_isolation(String is_isolation) {
        this.is_isolation = is_isolation;
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
