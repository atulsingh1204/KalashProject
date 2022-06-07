package com.example.kalashproject.ModelList;

import org.json.JSONException;
import org.json.JSONObject;

public class AllInspectionDataModelList {
    String order_id;
    String users_name;
    String invoice_no;
    String address;
    String invoice_date;
    String phone;
    String gstin;
    String first_inspection_date;
    String second_inspection_date;
    String third_inspection_date;
    String fourth_inspection_date;
    String grpo_date;

    public AllInspectionDataModelList(JSONObject jsonObject) {

        try {
            this.order_id = jsonObject.getString("order_id");
            this.users_name = jsonObject.getString("users_name");
            this.invoice_no = jsonObject.getString("invoice_no");
            this.address = jsonObject.getString("address");
            this.invoice_date = jsonObject.getString("invoice_date");
            this.phone = jsonObject.getString("phone");
            this.gstin = jsonObject.getString("gstin");
            this.first_inspection_date = jsonObject.getString("first_inspection_date");
            this.second_inspection_date = jsonObject.getString("second_inspection_date");
            this.third_inspection_date = jsonObject.getString("third_inspection_date");
            this.fourth_inspection_date = jsonObject.getString("fourth_inspection_date");
            this.grpo_date = jsonObject.getString("grpo_date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getFirst_inspection_date() {
        return first_inspection_date;
    }

    public void setFirst_inspection_date(String first_inspection_date) {
        this.first_inspection_date = first_inspection_date;
    }

    public String getSecond_inspection_date() {
        return second_inspection_date;
    }

    public void setSecond_inspection_date(String second_inspection_date) {
        this.second_inspection_date = second_inspection_date;
    }

    public String getThird_inspection_date() {
        return third_inspection_date;
    }

    public void setThird_inspection_date(String third_inspection_date) {
        this.third_inspection_date = third_inspection_date;
    }

    public String getFourth_inspection_date() {
        return fourth_inspection_date;
    }

    public void setFourth_inspection_date(String fourth_inspection_date) {
        this.fourth_inspection_date = fourth_inspection_date;
    }

    public String getGrpo_date() {
        return grpo_date;
    }

    public void setGrpo_date(String grpo_date) {
        this.grpo_date = grpo_date;
    }
}
