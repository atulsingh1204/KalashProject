package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionFormThree extends AppCompatActivity {


    EditText three_total_female, three_ot_plant_in_f, three_details, disease_plant_in_f, three_pld_acre, three_reason_of_pld, three_rejected_acre, three_reason_rejected_acre, three_exi_fruit, three_exp_fruit, three_total_fruit, three_avg_wt_seed_fruit, three_breeder_remark;
    Spinner spn_fa_flag_three;
    TextView three_date_of_roughing, three_polln_start_date, expected_date_of_dispatch_two;
    ImageView three_iv_image_1, three_iv_image_2, three_iv_image_3;
    ImageView three_iv_photo_1, three_iv_photo_2, three_iv_photo_3;

    TextView inspection_three_tv_next;

    String str_three_total_female, str_three_ot_plant_in_f, str_three_details, str_disease_plant_in_f, str_three_pld_acre, str_three_reason_of_pld, str_three_rejected_acre, str_three_reason_rejected_acre, str_three_exi_fruit, str_three_exp_fruit, str_three_total_fruit, str_three_avg_wt_seed_fruit, str_three_breeder_remark;
    String str_spn_fa_flag_three;
    String str_three_date_of_roughing, str_three_polln_start_date, str_expected_date_of_dispatch_two;

    DatePickerDialog.OnDateSetListener three_SetListnerDateOfRoughing;
    DatePickerDialog.OnDateSetListener three_Setdate_of_roughing_two;
    DatePickerDialog.OnDateSetListener three_SetExpected_date_of_dispatch_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form_three);

        //EditText
        three_total_female = findViewById(R.id.three_total_female);
        three_ot_plant_in_f = findViewById(R.id.three_ot_plant_in_f);
        three_details = findViewById(R.id.three_details);
        disease_plant_in_f = findViewById(R.id.disease_plant_in_f);
        three_pld_acre = findViewById(R.id.three_pld_acre);
        three_reason_of_pld = findViewById(R.id.three_reason_of_pld);
        three_rejected_acre = findViewById(R.id.three_rejected_acre);
        three_reason_rejected_acre = findViewById(R.id.three_reason_rejected_acre);
        three_exi_fruit = findViewById(R.id.three_exi_fruit);
        three_exp_fruit = findViewById(R.id.three_exp_fruit);
        three_total_fruit = findViewById(R.id.three_total_fruit);
        three_avg_wt_seed_fruit = findViewById(R.id.three_avg_wt_seed_fruit);
        three_breeder_remark = findViewById(R.id.three_breeder_remark);

        //Spinner
        spn_fa_flag_three = findViewById(R.id.spn_fa_flag_three);

        //TextView

        three_date_of_roughing = findViewById(R.id.three_date_of_roughing);
        three_polln_start_date = findViewById(R.id.three_polln_start_date);
        expected_date_of_dispatch_two = findViewById(R.id.expected_date_of_dispatch_two);

        inspection_three_tv_next = findViewById(R.id.inspection_three_tv_next);

        //ImageView

        three_iv_image_1 = findViewById(R.id.three_iv_image_1);
        three_iv_image_2 = findViewById(R.id.three_iv_image_2);
        three_iv_image_3 = findViewById(R.id.three_iv_image_3);

        three_iv_photo_1 = findViewById(R.id.three_iv_photo_1);
        three_iv_photo_2 = findViewById(R.id.three_iv_photo_2);
        three_iv_photo_3 = findViewById(R.id.three_iv_photo_3);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection Form Three");


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        three_date_of_roughing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormThree.this, android.R.style.Theme_Holo_Dialog_MinWidth, three_SetListnerDateOfRoughing, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        three_SetListnerDateOfRoughing = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                three_date_of_roughing.setText(date);
            }
        };


        three_polln_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormThree.this, android.R.style.Theme_Holo_Dialog_MinWidth, three_Setdate_of_roughing_two, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        three_Setdate_of_roughing_two = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                three_polln_start_date.setText(date);
            }
        };


        expected_date_of_dispatch_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormThree.this, android.R.style.Theme_Holo_Dialog_MinWidth, three_SetExpected_date_of_dispatch_two, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        three_SetExpected_date_of_dispatch_two = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                expected_date_of_dispatch_two.setText(date);

            }
        };


        inspection_three_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }

    private void sendData()
    {

     // EditText
     str_three_total_female =  three_total_female.getText().toString();
     str_three_ot_plant_in_f = three_ot_plant_in_f.getText().toString();
     str_three_details = three_details.getText().toString();
     str_disease_plant_in_f = disease_plant_in_f.getText().toString();
     str_three_pld_acre = three_pld_acre.getText().toString();
     str_three_reason_of_pld = three_reason_of_pld.getText().toString();
     str_three_rejected_acre = three_rejected_acre.getText().toString();
     str_three_reason_rejected_acre = three_reason_rejected_acre.getText().toString();
     str_three_exi_fruit = three_exi_fruit.getText().toString();
     str_three_exp_fruit = three_exp_fruit.getText().toString();
     str_three_total_fruit = three_total_fruit.getText().toString();
     str_three_avg_wt_seed_fruit = three_avg_wt_seed_fruit.getText().toString();
     str_three_breeder_remark = three_breeder_remark.getText().toString();

     // TextViewv (Date)


        str_three_date_of_roughing = three_date_of_roughing.getText().toString();
        str_three_polln_start_date = three_polln_start_date.getText().toString();
        str_expected_date_of_dispatch_two = expected_date_of_dispatch_two.getText().toString();


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.third_inspection_add("1","1",str_three_ot_plant_in_f,str_three_details,str_disease_plant_in_f, str_three_date_of_roughing, str_three_polln_start_date,str_three_pld_acre, str_three_reason_of_pld, str_three_rejected_acre, str_three_reason_rejected_acre, str_three_exi_fruit, str_three_exp_fruit, str_three_total_fruit, str_three_avg_wt_seed_fruit, "1", str_expected_date_of_dispatch_two,str_three_breeder_remark);

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                try {

                    String output = response.body().string();

                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(InspectionFormThree.this, "Data Sent Successfully!", Toast.LENGTH_SHORT).show();
                        Log.e("Check_Response","" +jsonObject.getString("ResponseMessage"));
                    }

                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(InspectionFormThree.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }

                }catch (IOException |JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
















    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}