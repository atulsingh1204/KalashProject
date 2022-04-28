package com.example.kalashproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

import com.example.kalashproject.ModelList.CropList;
import com.example.kalashproject.ModelList.FQ_flag_list;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.github.dhaval2404.imagepicker.ImagePicker;

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

public class InspectionFormOne extends AppCompatActivity {

    TextView male_sowing_date, female_sowing_date, expected_date_of_dispatch;

    EditText male_row, female_row, male_plant_in_row, female_plant_in_row, pld_acre, reason_of_pld, rejected_acre, reason_of_rejected_pld;

    String MaleRow, FemaleRow, MalePlantInRow, FemalePlantInRow, PLDAcre, ReasonOfPLD, RejectedAcre, ReasonOfRejectedPLD;
    String MaleSowingDate,FemaleSowingDate, ExpectedDateOfDispatch;

    ImageView iv_form_one_1, iv_form_one_2, iv_form_one_3;

    int Code = 2404;

    ArrayList<Uri> imagesList = new ArrayList<Uri>();

    ArrayList<FQ_flag_list> fq_flag_lists = new ArrayList<FQ_flag_list>();




    String str_isolation, str_Fa_flag_id = "";

    Spinner spn_isolation, spn_fa_flag;
        DatePickerDialog.OnDateSetListener setListenerMale;

    DatePickerDialog.OnDateSetListener setListenerFemale;


    DatePickerDialog.OnDateSetListener Listener_expected_date_of_dispatch;

    TextView inspection_one_tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form_one);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection Form One");


        male_sowing_date = findViewById(R.id.male_sowing_date);
        female_sowing_date = findViewById(R.id.female_sowing_date);

        male_row = findViewById(R.id.male_row);
        female_row = findViewById(R.id.female_row);
        male_plant_in_row = findViewById(R.id.male_plant_in_row);
        female_plant_in_row = findViewById(R.id.female_plant_in_row);
        pld_acre = findViewById(R.id.pld_acre);
        reason_of_pld = findViewById(R.id.reason_of_pld);
        rejected_acre = findViewById(R.id.rejected_acre);
        expected_date_of_dispatch = findViewById(R.id.expected_date_of_dispatch);
        reason_of_rejected_pld = findViewById(R.id.reason_of_rejected_pld);

        inspection_one_tv_next = findViewById(R.id.inspection_one_tv_next);



        //Spinner

        spn_isolation = findViewById(R.id.spn_isolation);
        spn_fa_flag = findViewById(R.id.spn_fa_flag);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        male_sowing_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormOne.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListenerMale, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListenerMale = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                male_sowing_date.setText(date);
                Log.e("Date", "MaleDate" + date);
            }
        };

        female_sowing_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialogFemale = new DatePickerDialog(InspectionFormOne.this, android.R.style.Theme_Holo_Dialog_MinWidth, setListenerFemale, year, month, day);
                datePickerDialogFemale.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialogFemale.show();

            }
        });

        setListenerFemale = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String dateTwo = day + "/" + month + "/" + year;
                Log.e("Date", "FemaleDate" + dateTwo);
                female_sowing_date.setText(dateTwo);
            }
        };


        expected_date_of_dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialogFemale = new DatePickerDialog(InspectionFormOne.this, android.R.style.Theme_Holo_Dialog_MinWidth, Listener_expected_date_of_dispatch, year, month, day);
                datePickerDialogFemale.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialogFemale.show();
            }
        });


        Listener_expected_date_of_dispatch = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String dateTwo = day + "/" + month + "/" + year;
                Log.e("Date", "FemaleDate" + dateTwo);
                expected_date_of_dispatch.setText(dateTwo);
            }
        };




        getIsolation();
        getFaFlag();

        getImages();



        inspection_one_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendInspectionFormOne();
                Toast.makeText(InspectionFormOne.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void getImages()
    {
        iv_form_one_1 = findViewById(R.id.iv_form_one_1);
        iv_form_one_2 = findViewById(R.id.iv_form_one_2);
        iv_form_one_3 = findViewById(R.id.iv_form_one_3);


        iv_form_one_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(InspectionFormOne.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        iv_form_one_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(InspectionFormOne.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


        iv_form_one_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImagePicker.with(InspectionFormOne.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
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

                        fq_flag_lists.add(new FQ_flag_list("--- Select FA Flag ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            fq_flag_lists.add(new FQ_flag_list(object));
                        }
                        ArrayAdapter<FQ_flag_list> cropAdapter = new ArrayAdapter<FQ_flag_list>(InspectionFormOne.this, android.R.layout.simple_spinner_item, fq_flag_lists);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spn_fa_flag.setAdapter(cropAdapter);

                        spn_fa_flag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                Log.e("FA_Flag_Item", " " + item);
                                 str_Fa_flag_id = fq_flag_lists.get(i).getId();
                                Log.e("FA_Id"," " +str_Fa_flag_id);

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

    private void getIsolation()
    {

        spn_isolation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_isolation = spn_isolation.getSelectedItem().toString();
                Log.e("spinner","str_isolation" +str_isolation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void sendInspectionFormOne() {



        MaleSowingDate = male_sowing_date.getText().toString();
        FemaleSowingDate = female_sowing_date.getText().toString();
        MaleRow = male_row.getText().toString().trim();
        FemaleRow = female_row.getText().toString().trim();
        MalePlantInRow = male_plant_in_row.getText().toString().trim();
        FemalePlantInRow = female_plant_in_row.getText().toString().trim();
        PLDAcre = pld_acre.getText().toString().trim();
        ReasonOfPLD = reason_of_pld.getText().toString().trim();
        RejectedAcre = rejected_acre.getText().toString().trim();
        ReasonOfRejectedPLD = reason_of_rejected_pld.getText().toString().trim();
        ExpectedDateOfDispatch = expected_date_of_dispatch.getText().toString();

        Log.e("ReasonOfPLD","" +ReasonOfPLD);
        Log.e("RejectedAcre","" +RejectedAcre);


        Log.e("sendFormOne","MaleSowingDate " +MaleSowingDate);
        Log.e("sendFormOne","FemaleSowingDate " +FemaleSowingDate);
        Log.e("sendFormOne","MaleRow " +MaleRow);
        Log.e("sendFormOne","FemaleRow " +FemaleRow);
        Log.e("sendFormOne","MalePlantInRow " +MalePlantInRow);
        Log.e("sendFormOne","FemalePlantInRow " +FemalePlantInRow);
        Log.e("sendFormOne","str_isolation " +str_isolation);
        Log.e("sendFormOne","PLDAcre " +PLDAcre);
        Log.e("sendFormOne","ReasonOfPLD " +ReasonOfPLD);
        Log.e("sendFormOne","RejectedAcre " +RejectedAcre);
        Log.e("sendFormOne","ReasonOfRejectedPLD " +ReasonOfRejectedPLD);
        Log.e("sendFormOne","ExpectedDateOfDispatch " +ExpectedDateOfDispatch);
        Log.e("sendFormOne","fq_flag_id " +"1");
        Log.e("sendFormOne","breeder_remark " +"Abc");

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.first_inspection_add(MaleSowingDate,
                                                                                            FemaleSowingDate,
                                                                                            MaleRow,
                                                                                            FemaleRow,
                                                                                            MalePlantInRow,
                                                                                            FemalePlantInRow,
                                                                                            str_isolation,
                                                                                            PLDAcre,
                                                                                            ReasonOfPLD,
                                                                                            RejectedAcre,
                                                                                            ReasonOfRejectedPLD,
                                                                                            ExpectedDateOfDispatch,
                                                                                            "1",
                                                                                            "Abc");

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String output;
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    Log.e("response","" +output);
                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(InspectionFormOne.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){

                        Toast.makeText(InspectionFormOne.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        Log.e("inspectionOne", "RequestCode: " +requestCode);
        Log.e("inspectionOne", "ResultCode: " +resultCode);
        Log.e("uri","uri: " +uri);

        if (Code==2404){
            iv_form_one_1.setImageURI(uri);
            Code = Code+1;
            imagesList.add(uri);
            Log.e("inspectionOne","iv_form_one_1"+Code);
        }
        else if (Code == 2405){
            iv_form_one_2.setImageURI(uri);
            Code = Code+1;
            
            Log.e("inspectionOne","iv_form_one_2"+Code);
        }

        else if (Code == 2406){
            iv_form_one_3.setImageURI(uri);
            Code = Code+1;
            Log.e("inspectionOne","iv_form_one_2"+Code);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}