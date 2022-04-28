package com.example.kalashproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognitionService;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.MyLibrary.FileUtils;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.StartActivities.MainActivity;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionFormTwo extends AppCompatActivity {

    EditText total_female, ot_plant_in_f, ot_plant_in_m, details, disease_plant_in_m, pld_acre, reason_of_pld, rejected_acre, reason_rejected_acre, breeder_remark;
    TextView date_of_roughing, date_of_roughing_two, expected_date_of_dispatch_two;
    ImageView iv_image_1, iv_image_2, iv_image_3;
    ImageView iv_photo_1, iv_photo_2, iv_photo_3;

    ArrayList<Uri> iv_photo_list = new ArrayList<Uri>();

    String str_total_female, str_ot_plant_in_f, str_ot_plant_in_m, str_details, str_disease_plant_in_m, str_pld_acre, str_reason_of_pld, str_rejected_acre, str_reason_rejected_acre, str_breeder_remark,
            str_date_of_roughing, str_date_of_roughing_two, str_expected_date_of_dispatch_two;

    DatePickerDialog.OnDateSetListener SetListnerDateOfRoughing;
    DatePickerDialog.OnDateSetListener Setdate_of_roughing_two;
    DatePickerDialog.OnDateSetListener SetExpected_date_of_dispatch_two;

    private CharSequence[] options = {"camera","Gallery","Cancel"};

    TextView inspection_two_tv_next;
    String path;

    String Date = new SimpleDateFormat("yyyymmdd", Locale.getDefault()).format(new Date());
    String Time = new SimpleDateFormat("HHmmss",Locale.getDefault()).format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form_two);

        //EditText
        total_female = findViewById(R.id.total_female);
        ot_plant_in_f = findViewById(R.id.ot_plant_in_f);
        ot_plant_in_m = findViewById(R.id.ot_plant_in_m);
        details = findViewById(R.id.details);
        disease_plant_in_m = findViewById(R.id.disease_plant_in_m);
        pld_acre = findViewById(R.id.pld_acre);
        reason_of_pld = findViewById(R.id.reason_of_pld);
        rejected_acre = findViewById(R.id.rejected_acre);
        reason_rejected_acre = findViewById(R.id.reason_rejected_acre);
        breeder_remark = findViewById(R.id.breeder_remark);

        iv_image_1 = findViewById(R.id.iv_image_1);
        iv_image_2 = findViewById(R.id.iv_image_2);
        iv_image_3 = findViewById(R.id.iv_image_3);

        iv_photo_1 = findViewById(R.id.iv_photo_1);
        iv_photo_2 = findViewById(R.id.iv_photo_2);
        iv_photo_3 = findViewById(R.id.iv_photo_3);


        inspection_two_tv_next = findViewById(R.id.inspection_two_tv_next);


        //TextView
        date_of_roughing = findViewById(R.id.date_of_roughing);
        date_of_roughing_two = findViewById(R.id.date_of_roughing_two);
        expected_date_of_dispatch_two = findViewById(R.id.expected_date_of_dispatch_two);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection Form Two");


        date_of_roughing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormTwo.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetListnerDateOfRoughing, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetListnerDateOfRoughing = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                date_of_roughing.setText(date);

            }
        };

        date_of_roughing_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormTwo.this, android.R.style.Theme_Holo_Dialog_MinWidth, Setdate_of_roughing_two, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        Setdate_of_roughing_two = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                date_of_roughing_two.setText(date);

            }
        };


        expected_date_of_dispatch_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InspectionFormTwo.this, android.R.style.Theme_Holo_Dialog_MinWidth, SetExpected_date_of_dispatch_two, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        SetExpected_date_of_dispatch_two = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                expected_date_of_dispatch_two.setText(date);
            }
        };

        GetImages();

        inspection_two_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendData();
            }
        });


    }

    private void GetImages()
    {
        iv_image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InspectionFormTwo.this);
                builder.setTitle("Select Image");
                builder.setItems(options, new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int i)
                    {
                        if(options[i].equals("camera"))
                        {
                            Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takepic,0);
                        }
                        else if(options[i].equals("Gallery"))
                        {
                            Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery,1);
                        }
                        else
                        {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });



        iv_photo_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        iv_photo_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        iv_photo_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });

    }

    private void sendData() {
        str_total_female = total_female.getText().toString().trim();
        str_ot_plant_in_f = ot_plant_in_f.getText().toString().trim();
        str_ot_plant_in_m = ot_plant_in_m.getText().toString().trim();
        str_details = details.getText().toString().trim();
        str_disease_plant_in_m = disease_plant_in_m.getText().toString().trim();
        str_pld_acre = pld_acre.getText().toString().trim();
        str_reason_of_pld = reason_of_pld.getText().toString().trim();
        str_rejected_acre = rejected_acre.getText().toString().trim();
        str_reason_rejected_acre = reason_rejected_acre.getText().toString().trim();
        str_breeder_remark = breeder_remark.getText().toString().trim();

        str_date_of_roughing = date_of_roughing.getText().toString().trim();
        str_date_of_roughing_two = date_of_roughing_two.getText().toString().trim();
        str_expected_date_of_dispatch_two = expected_date_of_dispatch_two.getText().toString().trim();


        Log.e("sendInspectionTwo", "str_total_female: " + str_total_female);
        Log.e("sendInspectionTwo", "str_ot_plant_in_f: " + str_ot_plant_in_f);
        Log.e("sendInspectionTwo", "str_ot_plant_in_m: " + str_ot_plant_in_m);
        Log.e("sendInspectionTwo", "str_details: " + str_details);
        Log.e("sendInspectionTwo", "str_disease_plant_in_m: " + str_disease_plant_in_m);
        Log.e("sendInspectionTwo", "str_pld_acre: " + str_pld_acre);
        Log.e("sendInspectionTwo", "str_reason_of_pld: " + str_reason_of_pld);
        Log.e("sendInspectionTwo", "str_rejected_acre: " + str_rejected_acre);
        Log.e("sendInspectionTwo", "str_reason_rejected_acre: " + str_reason_rejected_acre);
        Log.e("sendInspectionTwo", "str_breeder_remark: " + str_breeder_remark);
        Log.e("sendInspectionTwo", "str_date_of_roughing: " + str_date_of_roughing);
        Log.e("sendInspectionTwo", "str_date_of_roughing_two: " + str_date_of_roughing_two);
        Log.e("sendInspectionTwo", "str_expected_date_of_dispatch_two: " + str_expected_date_of_dispatch_two);
        Log.e("sendInspectionTwo", "order_id: " + "1");
        Log.e("sendInspectionTwo", "fdo_id: " + Shared_Preferences.getPrefs(InspectionFormTwo.this,"Reg_id"));



        String fdo_id = Shared_Preferences.getPrefs(InspectionFormTwo.this,"Reg_id");
        Log.e("fdo_id","" +fdo_id);

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.second_inspection_add("1",fdo_id,"abc",str_ot_plant_in_f,str_date_of_roughing,str_ot_plant_in_m,str_date_of_roughing_two,str_disease_plant_in_m ,str_details,str_pld_acre, str_reason_of_pld,str_rejected_acre,str_reason_rejected_acre,"1", str_expected_date_of_dispatch_two,str_breeder_remark);

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();
                    Log.e("sendDataResponse","" +output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(InspectionFormTwo.this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
                        Log.e("error","1" +jsonObject.getString("ResponseMessage"));
                        Intent ii = new Intent(InspectionFormTwo.this, MainActivity.class);
                        startActivity(ii);
                        finish();

                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){

                        Toast.makeText(InspectionFormTwo.this,"" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        Log.e("error","0" +jsonObject.getString("ResponseMessage"));
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(InspectionFormTwo.this, "" +t, Toast.LENGTH_SHORT).show();

                Log.e("error","Failure" +t);

            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



            switch (requestCode){
                case 0:


//
//                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                        iv_photo_1.setImageBitmap(bitmap);

                        Uri uri1 = data.getData();
                        Log.e("check","Uri1 " +uri1);

//                      path = FileUtils.getPath(getApplicationContext(),getImageUri(getApplicationContext(),photo1));
//                      Log.e("path","" +path);
                       // iv_photo_1.setImageBitmap(photo1);
                   // iv_photo_list.add(uri1);
                    iv_photo_1.setImageURI(uri1);

                    break;


//                case 1:
//
//                    Uri uri2 = data.getData();
//                    iv_photo_list.add(uri2);
//                    iv_photo_2.setImageURI(uri2);
//                    break;

            }



    }

    public Uri getImageUri(Context context, Bitmap bitmap)
    {
        String profile = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,"ProfileImg"+Time+Date,"");

        return Uri.parse(profile);
    }
}