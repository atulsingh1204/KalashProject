package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InspectionOneFullData extends AppCompatActivity {

    TextView view_I1_id, view_I1_OrderId, view_t1_maleSowingDate, view_I1_femaleSowingDate, view_I1_maleRow, view_I1_femaleRow, view_I1_MalePlantInRow,view_I1_FemalePlantInRow, view_I1_TotalMale, view_I1_TotalFemale, view_I1_IsIsolation, view_I1_PldAcre, view_I1_ReasonsOfPld, view_I1_RejectedAcre, view_I1_ReasonsOfRejectedAcre, view_I1_ExpectedDateOfDispatch, view_I1_FlagName, view_I1_BreederRemark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_one_full_data);

        view_I1_id = findViewById(R.id.view_I1_id);
        view_I1_OrderId = findViewById(R.id.view_I1_OrderId);
        view_t1_maleSowingDate = findViewById(R.id.view_t1_maleSowingDate);
        view_I1_femaleSowingDate = findViewById(R.id.view_I1_femaleSowingDate);
        view_I1_maleRow = findViewById(R.id.view_I1_maleRow);
        view_I1_femaleRow = findViewById(R.id.view_I1_femaleRow);
        view_I1_MalePlantInRow = findViewById(R.id.view_I1_MalePlantInRow);
        view_I1_FemalePlantInRow = findViewById(R.id.view_I1_FemalePlantInRow);
        view_I1_TotalMale = findViewById(R.id.view_I1_TotalMale);
        view_I1_TotalFemale = findViewById(R.id.view_I1_TotalFemale);
        view_I1_IsIsolation = findViewById(R.id.view_I1_IsIsolation);
        view_I1_PldAcre = findViewById(R.id.view_I1_PldAcre);
        view_I1_ReasonsOfPld = findViewById(R.id.view_I1_ReasonsOfPld);
        view_I1_RejectedAcre = findViewById(R.id.view_I1_RejectedAcre);
        view_I1_ReasonsOfRejectedAcre = findViewById(R.id.view_I1_ReasonsOfRejectedAcre);
        view_I1_ExpectedDateOfDispatch = findViewById(R.id.view_I1_ExpectedDateOfDispatch);
        view_I1_FlagName = findViewById(R.id.view_I1_FlagName);
        view_I1_BreederRemark = findViewById(R.id.view_I1_BreederRemark);


        Intent ii = getIntent();

       String id = ii.getStringExtra("id");
       String order_id = ii.getStringExtra("order_id");
       String male_sowing_date = ii.getStringExtra("male_sowing_date");
       String female_sowing_date = ii.getStringExtra("female_sowing_date");
       String male_row = ii.getStringExtra("male_row");
       String female_row = ii.getStringExtra("female_row");
       String male_plant_in_row = ii.getStringExtra("male_plant_in_row");
       String female_plant_in_row = ii.getStringExtra("female_plant_in_row");
       String total_male = ii.getStringExtra("total_male");
       String total_female = ii.getStringExtra("total_female");
       String is_isolation = ii.getStringExtra("is_isolation");
       String pld_acre = ii.getStringExtra("pld_acre");
       String reason_of_pld = ii.getStringExtra("reason_of_pld");
       String rejected_acre = ii.getStringExtra("rejected_acre");
       String reason_of_rejected = ii.getStringExtra("reason_of_rejected");
       String expected_date_of_dispatch = ii.getStringExtra("expected_date_of_dispatch");
       String flag_name = ii.getStringExtra("flag_name");
       String breeder_remark = ii.getStringExtra("breeder_remark");


        view_I1_id.setText(id);
        view_I1_OrderId.setText(order_id);
        view_t1_maleSowingDate.setText(male_sowing_date);
        view_I1_femaleSowingDate.setText(female_sowing_date);
        view_I1_maleRow.setText(male_row);
        view_I1_femaleRow.setText(female_row);
        view_I1_MalePlantInRow.setText(male_plant_in_row);
        view_I1_FemalePlantInRow.setText(female_plant_in_row);
        view_I1_TotalMale.setText(total_male);
        view_I1_TotalFemale.setText(total_female);
        view_I1_IsIsolation.setText(is_isolation);
        view_I1_PldAcre.setText(pld_acre);
        view_I1_ReasonsOfPld.setText(reason_of_pld);
        view_I1_RejectedAcre.setText(rejected_acre);
        view_I1_ReasonsOfRejectedAcre.setText(reason_of_rejected);
        view_I1_ExpectedDateOfDispatch.setText(expected_date_of_dispatch);
        view_I1_FlagName.setText(flag_name);
        view_I1_BreederRemark.setText(breeder_remark);



    }
}