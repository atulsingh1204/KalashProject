package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class View_Details_QR_Checker_Activity extends AppCompatActivity {

  TextView view_qr_checker_details_crop_name,view_qr_checker_variety_name,view_qr_checker_batch_number,view_qr_checker_number_of_packing,view_qr_checker_weight_in_packets,view_qr_checker_germination_rate,view_qr_checker_genetic_purity;
  String qr_for,id,crop_name,variety_name,batch_number,number_of_packing,weight_in_packets,germination_rate,genetic_purity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_qr_checker);

        view_qr_checker_details_crop_name = findViewById(R.id.view_qr_checker_details_crop_name);
        view_qr_checker_variety_name = findViewById(R.id.view_qr_checker_variety_name);
        view_qr_checker_batch_number = findViewById(R.id.view_qr_checker_batch_number);
        view_qr_checker_number_of_packing = findViewById(R.id.view_qr_checker_number_of_packing);
        view_qr_checker_weight_in_packets = findViewById(R.id.view_qr_checker_weight_in_packets);
        view_qr_checker_germination_rate = findViewById(R.id.view_qr_checker_germination_rate);
        view_qr_checker_genetic_purity = findViewById(R.id.view_qr_checker_genetic_purity);


        Intent ii = getIntent();
        qr_for = ii.getStringExtra("qr_for");
        id = ii.getStringExtra("id");
        crop_name = ii.getStringExtra("crop_name");
        variety_name = ii.getStringExtra("variety_name");
        batch_number = ii.getStringExtra("batch_number");
        number_of_packing = ii.getStringExtra("number_of_packing");
        weight_in_packets =  ii.getStringExtra("weight_in_packets");
        germination_rate = ii.getStringExtra("germination_rate");
        genetic_purity = ii.getStringExtra("genetic_purity");

        Log.e("qr_for","" +qr_for);
        Log.e("qr_for","" +id);

        view_qr_checker_details_crop_name.setText(crop_name);
        view_qr_checker_variety_name.setText(variety_name);
        view_qr_checker_batch_number.setText(batch_number);
        view_qr_checker_number_of_packing.setText(number_of_packing);
        view_qr_checker_weight_in_packets.setText(weight_in_packets);
        view_qr_checker_germination_rate.setText(germination_rate);
        view_qr_checker_genetic_purity.setText(genetic_purity);




    }
}