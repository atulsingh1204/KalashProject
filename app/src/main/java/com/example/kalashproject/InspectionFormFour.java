package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.FQ_flag_list;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.StartActivities.MainActivity;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InspectionFormFour extends AppCompatActivity {

    EditText four_total_female,four_ot_fruit_f,four_pld_acre,four_reason_of_pld,four_rejected_acre, four_reason_rejected_acre,four_exi_fruit,four_exp_fruit, four_total_fruit,four_avg_wt_seed_fruit, four_breeder_remark;
    TextView  four_date_of_roughing, four_polln_end_date, expected_date_of_harvesting_four, expected_date_of_dispatch_four;
    ImageView four_iv_image_1,four_iv_image_2, four_iv_image_3;
    TextView inspection_four_tv_next;

    String str_four_total_female, str_four_ot_fruit_f, str_four_pld_acre, str_four_reason_of_pld, str_four_rejected_acre, str_four_reason_rejected_acre, str_four_exi_fruit, str_four_exp_fruit, str_four_total_fruit, str_four_avg_wt_seed_fruit, str_four_breeder_remark;
    String str_four_date_of_roughing, str_four_polln_end_date, str_expected_date_of_harvesting_four, str_expected_date_of_dispatch_four;

    Spinner spn_fa_flag_four;

    ArrayList<FQ_flag_list> fq_flag_lists_four = new ArrayList<FQ_flag_list>();
    String str_Fa_flag_id_four;


    DatePickerDialog.OnDateSetListener SetListenerDateOfRoughing;
    DatePickerDialog.OnDateSetListener SetListenerPollnEndDate;
    DatePickerDialog.OnDateSetListener SetListenerExpetDateHarvesting;
    DatePickerDialog.OnDateSetListener SetListenerExpetDateDispatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_inspection_form_four);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection Form Four");

        //EditText
        four_total_female = findViewById(R.id.four_total_female);
        four_ot_fruit_f = findViewById(R.id.four_ot_fruit_f);
        four_pld_acre = findViewById(R.id.four_pld_acre);
        four_reason_of_pld = findViewById(R.id.four_reason_of_pld);
        four_rejected_acre = findViewById(R.id.four_rejected_acre);
        four_reason_rejected_acre = findViewById(R.id.four_reason_rejected_acre);
        four_exi_fruit = findViewById(R.id.four_exi_fruit);
        four_exp_fruit = findViewById(R.id.four_exp_fruit);
        four_total_fruit = findViewById(R.id.four_total_fruit);
        four_avg_wt_seed_fruit = findViewById(R.id.four_avg_wt_seed_fruit);
        four_breeder_remark = findViewById(R.id.four_breeder_remark);

        //TextView
        four_date_of_roughing = findViewById(R.id.four_date_of_roughing);
        four_polln_end_date = findViewById(R.id.four_polln_end_date);
        expected_date_of_harvesting_four = findViewById(R.id.expected_date_of_harvesting_four);
        expected_date_of_dispatch_four = findViewById(R.id.expected_date_of_dispatch_four);

        //ImageView
        four_iv_image_1 = findViewById(R.id.four_iv_image_1);
        four_iv_image_2 = findViewById(R.id.four_iv_image_2);
        four_iv_image_3 = findViewById(R.id.four_iv_image_3);

        spn_fa_flag_four = findViewById(R.id.spn_fa_flag_four);
        inspection_four_tv_next = findViewById(R.id.inspection_four_tv_next);


        getFaFlag();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        four_date_of_roughing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormFour.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetListenerDateOfRoughing, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetListenerDateOfRoughing = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month +"/" + year;
                four_date_of_roughing.setText(date);

            }
        };



        four_polln_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormFour.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetListenerPollnEndDate, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetListenerPollnEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month +"/" + year;
                four_polln_end_date.setText(date);

            }
        };



        expected_date_of_harvesting_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormFour.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetListenerExpetDateHarvesting, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetListenerExpetDateHarvesting = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month +"/" + year;
                expected_date_of_harvesting_four.setText(date);

            }
        };



        expected_date_of_dispatch_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormFour.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetListenerExpetDateDispatch, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetListenerExpetDateDispatch = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month +"/" + year;
                expected_date_of_dispatch_four.setText(date);

            }
        };


        inspection_four_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(InspectionFormFour.this, "Clicked", Toast.LENGTH_SHORT).show();
                sendData();

            }
        });








    }

    private void sendData()
    {

       str_four_total_female= four_total_female.getText().toString().trim();
        str_four_ot_fruit_f= four_ot_fruit_f.getText().toString().trim();
        str_four_pld_acre= four_pld_acre.getText().toString().trim();
        str_four_reason_of_pld= four_reason_of_pld.getText().toString().trim();
        str_four_rejected_acre= four_rejected_acre.getText().toString().trim();
        str_four_reason_rejected_acre= four_reason_rejected_acre.getText().toString().trim();
        str_four_exi_fruit= four_exi_fruit.getText().toString().trim();
        str_four_exp_fruit= four_exp_fruit.getText().toString().trim();
        str_four_total_fruit= four_total_fruit.getText().toString().trim();
        str_four_avg_wt_seed_fruit= four_avg_wt_seed_fruit.getText().toString().trim();
        str_four_breeder_remark= four_breeder_remark.getText().toString().trim();

        str_four_date_of_roughing= four_date_of_roughing.getText().toString().trim();
        str_four_polln_end_date= four_polln_end_date.getText().toString().trim();
        str_expected_date_of_harvesting_four= expected_date_of_harvesting_four.getText().toString().trim();
        str_expected_date_of_dispatch_four= expected_date_of_dispatch_four.getText().toString().trim();


        String fdo_id = Shared_Preferences.getPrefs(InspectionFormFour.this,"Reg_id");

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.fourth_inspection_add("1", fdo_id,str_four_ot_fruit_f,str_four_date_of_roughing,str_four_polln_end_date,str_four_pld_acre,str_four_reason_of_pld,str_four_rejected_acre,str_four_reason_rejected_acre,str_four_exi_fruit, str_four_exp_fruit,str_four_total_fruit, str_four_avg_wt_seed_fruit,str_Fa_flag_id_four,str_expected_date_of_harvesting_four, str_expected_date_of_dispatch_four,str_four_breeder_remark);

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();
                    Log.e("OutPut","Success" +output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                       // Toast.makeText(InspectionFormFour.this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();

                        Toast.makeText(InspectionFormFour.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(InspectionFormFour.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(InspectionFormFour.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                        Log.e("OutPut","zero" +jsonObject.getString("ResponseMessage"));
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("OutPut","failure" +t);
            }
        });




    }


    private void getFaFlag()
    {
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.fq_flag_list();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                // loadingDialog.dismissDialog();
                Log.e("getCropList", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        fq_flag_lists_four.add(new FQ_flag_list("--- Select FA Flag ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            fq_flag_lists_four.add(new FQ_flag_list(object));
                        }
                        ArrayAdapter<FQ_flag_list> cropAdapter = new ArrayAdapter<FQ_flag_list>(InspectionFormFour.this, android.R.layout.simple_spinner_item, fq_flag_lists_four);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spn_fa_flag_four.setAdapter(cropAdapter);

                        spn_fa_flag_four.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                Log.e("FA_Flag_Item", " " + item);
                                str_Fa_flag_id_four = fq_flag_lists_four.get(i).getId();
                                Log.e("FA_Id"," " +str_Fa_flag_id_four);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }



}