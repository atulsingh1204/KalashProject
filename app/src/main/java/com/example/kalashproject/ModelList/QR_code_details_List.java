package com.example.kalashproject.ModelList;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class QR_code_details_List
{
    String qr_for;
    String id;
    String crop_name;
    String variety_name;
    String batch_number;
    String number_of_packing;
    String weight_in_packets;
    String germination_rate;
    String genetic_purity;


    public QR_code_details_List() {
    }

    public QR_code_details_List(JSONObject jsonObject) {
        try {
            this.qr_for = jsonObject.getString("qr_for");
            this.id = jsonObject.getString("id");
            this.crop_name = jsonObject.getString("crop_name");
            this.variety_name = jsonObject.getString("variety_name");
            this.batch_number = jsonObject.getString("batch_number");
            this.number_of_packing = jsonObject.getString("number_of_packing");
            this.weight_in_packets = jsonObject.getString("weight_in_packets");
            this.germination_rate = jsonObject.getString("germination_rate");
            this.genetic_purity = jsonObject.getString("genetic_purity");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getQr_for() {
        return qr_for;
    }

    public void setQr_for(String qr_for) {
        this.qr_for = qr_for;
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

    public String getVariety_name() {
        return variety_name;
    }

    public void setVariety_name(String variety_name) {
        this.variety_name = variety_name;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getNumber_of_packing() {
        return number_of_packing;
    }

    public void setNumber_of_packing(String number_of_packing) {
        this.number_of_packing = number_of_packing;
    }

    public String getWeight_in_packets() {
        return weight_in_packets;
    }

    public void setWeight_in_packets(String weight_in_packets) {
        this.weight_in_packets = weight_in_packets;
    }

    public String getGermination_rate() {
        return germination_rate;
    }

    public void setGermination_rate(String germination_rate) {
        this.germination_rate = germination_rate;
    }

    public String getGenetic_purity() {
        return genetic_purity;
    }

    public void setGenetic_purity(String genetic_purity) {
        this.genetic_purity = genetic_purity;
    }
}
