package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class InspectionThreeModelList {


    String id;
    String order_id;
    String ot_plant_in_female;
    String details;
    String disease_plant_in_f;
    String date_of_roughing;
    String polln_start_date;
    String disease_plant_in_male;
    String pld_acre;
    String reason_of_pld;
    String rejected_acre;
    String reason_of_rejected;
    String exi_fruit;
    String exp_fruit;
    String total_fruit;
    String avg_wt_of_seed_fruit;
    String expected_date_of_dispatch;
    String flag_name;
    String ibreeder_remarkd;


    public InspectionThreeModelList(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.order_id = jsonObject.getString("order_id");
            this.ot_plant_in_female = jsonObject.getString("ot_plant_in_female");
            this.details = jsonObject.getString("details");
            this.disease_plant_in_f = jsonObject.getString("disease_plant_in_f");
            this.date_of_roughing = jsonObject.getString("date_of_roughing");
            this.polln_start_date = jsonObject.getString("polln_start_date");
            this.disease_plant_in_male = jsonObject.getString("disease_plant_in_male");
            this.pld_acre = jsonObject.getString("pld_acre");
            this.reason_of_pld = jsonObject.getString("reason_of_pld");
            this.rejected_acre = jsonObject.getString("rejected_acre");
            this.reason_of_rejected = jsonObject.getString("reason_of_rejected");
            this.exi_fruit = jsonObject.getString("exi_fruit");
            this.exp_fruit = jsonObject.getString("exp_fruit");
            this.total_fruit = jsonObject.getString("total_fruit");
            this.avg_wt_of_seed_fruit = jsonObject.getString("avg_wt_of_seed_fruit");
            this.expected_date_of_dispatch = jsonObject.getString("expected_date_of_dispatch");
            this.flag_name = jsonObject.getString("flag_name");
            this.ibreeder_remarkd = jsonObject.getString("ibreeder_remarkd");

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

    public String getOt_plant_in_female() {
        return ot_plant_in_female;
    }

    public void setOt_plant_in_female(String ot_plant_in_female) {
        this.ot_plant_in_female = ot_plant_in_female;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDisease_plant_in_f() {
        return disease_plant_in_f;
    }

    public void setDisease_plant_in_f(String disease_plant_in_f) {
        this.disease_plant_in_f = disease_plant_in_f;
    }

    public String getDate_of_roughing() {
        return date_of_roughing;
    }

    public void setDate_of_roughing(String date_of_roughing) {
        this.date_of_roughing = date_of_roughing;
    }

    public String getPolln_start_date() {
        return polln_start_date;
    }

    public void setPolln_start_date(String polln_start_date) {
        this.polln_start_date = polln_start_date;
    }

    public String getDisease_plant_in_male() {
        return disease_plant_in_male;
    }

    public void setDisease_plant_in_male(String disease_plant_in_male) {
        this.disease_plant_in_male = disease_plant_in_male;
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

    public String getExi_fruit() {
        return exi_fruit;
    }

    public void setExi_fruit(String exi_fruit) {
        this.exi_fruit = exi_fruit;
    }

    public String getExp_fruit() {
        return exp_fruit;
    }

    public void setExp_fruit(String exp_fruit) {
        this.exp_fruit = exp_fruit;
    }

    public String getTotal_fruit() {
        return total_fruit;
    }

    public void setTotal_fruit(String total_fruit) {
        this.total_fruit = total_fruit;
    }

    public String getAvg_wt_of_seed_fruit() {
        return avg_wt_of_seed_fruit;
    }

    public void setAvg_wt_of_seed_fruit(String avg_wt_of_seed_fruit) {
        this.avg_wt_of_seed_fruit = avg_wt_of_seed_fruit;
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

    public String getIbreeder_remarkd() {
        return ibreeder_remarkd;
    }

    public void setIbreeder_remarkd(String ibreeder_remarkd) {
        this.ibreeder_remarkd = ibreeder_remarkd;
    }
}




