package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.FQ_flag_list;
import com.example.kalashproject.ModelList.VillageList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.StartActivities.MainActivity;
import com.example.kalashproject.Utils.FileUtil;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InspectionFormFour extends AppCompatActivity {

    EditText four_total_female,four_ot_fruit_f,four_pld_acre,four_reason_of_pld,four_rejected_acre, four_reason_rejected_acre,four_exi_fruit,four_exp_fruit, four_total_fruit,four_avg_wt_seed_fruit, four_breeder_remark;
    TextView  four_date_of_roughing, four_polln_end_date, expected_date_of_harvesting_four, expected_date_of_dispatch_four;
    ImageView four_iv_image_1,four_iv_image_2, four_iv_image_3;
    TextView inspection_four_tv_next;

    String str_fa_flag_four= "";

    String str_four_total_female, str_four_ot_fruit_f, str_four_pld_acre, str_four_reason_of_pld, str_four_rejected_acre, str_four_reason_rejected_acre, str_four_exi_fruit, str_four_exp_fruit, str_four_total_fruit, str_four_avg_wt_seed_fruit, str_four_breeder_remark;
    String str_four_date_of_roughing, str_four_polln_end_date, str_expected_date_of_harvesting_four, str_expected_date_of_dispatch_four;


    // Multiple Images via gallery

    ///Multiple Images
    ImageView selectedImage;
    List<Uri> files = new ArrayList<>();

    private LinearLayout parentLinearLayout;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

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

        //Images initialize

        parentLinearLayout= findViewById(R.id.parent_linear_layout);

        ImageView addImage = findViewById(R.id.iv_add_image);





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




        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addImage();
            }
        });





        inspection_four_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(InspectionFormFour.this, "Clicked", Toast.LENGTH_SHORT).show();

                Log.e("checkingValidations", "Date of Roughing:" + four_date_of_roughing.getText().toString().trim());

                if (validations()){
                    sendData();
                }


            }
        });









    }

    private void sendData()
    {


        List<MultipartBody.Part> list = new ArrayList<>();

        for (Uri uri:files){

            Log.i("uris",uri.getPath());

            Log.e("response","Uris: " +uri.getPath());
            list.add(prepareFilePart("camera_document_name  []", uri));

            Log.e("response", "listSizeUpload: " +list);
        }


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
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.fourth_inspection_add(

                RequestBody.create(MediaType.parse("text/plain"),"1"),
                RequestBody.create(MediaType.parse("text/plain"),fdo_id),
                RequestBody.create(MediaType.parse("text/plain"),str_four_ot_fruit_f),
                RequestBody.create(MediaType.parse("text/plain"),str_four_date_of_roughing),
                        RequestBody.create(MediaType.parse("text/plain"),str_four_polln_end_date),
                                RequestBody.create(MediaType.parse("text/plain"),str_four_pld_acre),
                                        RequestBody.create(MediaType.parse("text/plain"),str_four_reason_of_pld),
                                                RequestBody.create(MediaType.parse("text/plain"),str_four_rejected_acre),
                                                        RequestBody.create(MediaType.parse("text/plain"),str_four_reason_rejected_acre),
                                                                RequestBody.create(MediaType.parse("text/plain"),str_four_exi_fruit),
                                                                        RequestBody.create(MediaType.parse("text/plain"),str_four_exp_fruit),
                                                                                RequestBody.create(MediaType.parse("text/plain"),str_four_total_fruit),
                                                                                        RequestBody.create(MediaType.parse("text/plain"),str_four_avg_wt_seed_fruit),
                                                                                                RequestBody.create(MediaType.parse("text/plain"),str_Fa_flag_id_four),
                                                                                                        RequestBody.create(MediaType.parse("text/plain"),str_expected_date_of_harvesting_four),
                                                                                                                RequestBody.create(MediaType.parse("text/plain"),str_expected_date_of_dispatch_four),
                                                                                                                        RequestBody.create(MediaType.parse("text/plain"),str_four_breeder_remark),
                                                                                                                            list);

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
                                str_fa_flag_four = spn_fa_flag_four.getSelectedItem().toString();
                                Log.e("spn_fa_flag_four", "str_fa_flag_four " +str_fa_flag_four);
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


    private void addImage()
    {

        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        parentLinearLayout.isFocusable();

        selectedImage = rowView.findViewById(R.id.number_edit_text);
        selectImage(InspectionFormFour.this);

    }

    private void selectImage(Context context)
    {
        //requestPermission();

        Dexter.withContext(InspectionFormFour.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                if (multiplePermissionsReport.areAllPermissionsGranted()){

                    final CharSequence[] options = {"Take Photo", "Cancel"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setTitle("Choose a Media");

                    builder.setItems(options, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int item) {

                            if (options[item].equals("Take Photo")) {
                                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(takePicture, 0);

                            } else if (options[item].equals("Choose from Gallery")) {
//                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                            } else if (options[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();

                } else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()){

                    Toast.makeText(context, "Permission is Denied", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(InspectionFormFour.this, MainActivity.class);
                    startActivity(ii);
                    finish();
                }


            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImage.setImageBitmap(img);
                        // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormFour.this,getImageUri(InspectionFormFour.this,img));

                        files.add(Uri.parse(imgPath));
                        Log.e("image", imgPath);
                    }

                    break;
//                case 1:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Uri img = data.getData();
//                        Picasso.get().load(img).into(selectedImage);
//
//                        String imgPath = FileUtil.getPath(InspectionFormTwo.this,img);
//
//                        files.add(Uri.parse(imgPath));
//                        /////Testing start
//                        Log.e("newresponse" , "Uri1: " +imgPath);
//                        Uri temp = Uri.parse(imgPath);
//                        Log.e("newresponse" , "addedInList: " +Uri.parse(imgPath));
//                        Log.e("newresponse" , "addedInList: " +temp);
//                        Log.e("image", imgPath);
//
//
//                        ///// Testing End
//
//                    }
//                    break;

            }
        }

    }

    //===== bitmap to Uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "intuenty", null);
        Log.d("image uri",path);
        return Uri.parse(path);
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {

        File file = new File(fileUri.getPath());
        Log.i("here is error",file.getAbsolutePath());
        // create RequestBody instance from file

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);


    }


    private  boolean validations(){
        boolean VALID = true;

        if (TextUtils.isEmpty(four_total_female.getText().toString())){
            VALID = false;
            four_total_female.setError("Please enter total female");
        }

        else if (TextUtils.isEmpty(four_ot_fruit_f.getText().toString())){
            VALID = false;
            four_ot_fruit_f.setError("Please enter OT Fruit Female");
        }

        else   if ((four_date_of_roughing.getText().toString().trim()).equals("Select date")){
            VALID = false;
            // four_date_of_roughing.setError("Please select date of roughing");
            Toast.makeText(this, "Please select date of roughing", Toast.LENGTH_SHORT).show();
        }

        else if (four_polln_end_date.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Polln end date", Toast.LENGTH_SHORT).show();
        }


        else if (TextUtils.isEmpty(four_pld_acre.getText().toString())){
            VALID = false;
            four_pld_acre.setError("Please enter PLD Acre");
        }



        else if (TextUtils.isEmpty(four_reason_of_pld.getText().toString())){
            VALID = false;
            four_reason_of_pld.setError("Please enter reasons of PLD");
        }
        else if (TextUtils.isEmpty(four_rejected_acre.getText().toString())){
            VALID = false;
            four_rejected_acre.setError("Please enter rejected acre");
        }
        else if (TextUtils.isEmpty(four_reason_rejected_acre.getText().toString())){
            VALID = false;
            four_reason_rejected_acre.setError("Please enter reasons of rejected acre");
        }
        else if (TextUtils.isEmpty(four_exi_fruit.getText().toString())){
            VALID = false;
            four_exi_fruit.setError("Please select Exi Fruit");
        }

        else if (TextUtils.isEmpty(four_exp_fruit.getText().toString())){
            VALID = false;
            four_exp_fruit.setError("Please select Exp Fruit");
        }

        else if (TextUtils.isEmpty(four_total_fruit.getText().toString())){
            VALID = false;
            four_total_fruit.setError("Please select total fruit");
        }

        else if (TextUtils.isEmpty(four_avg_wt_seed_fruit.getText().toString())){
            VALID = false;
            four_avg_wt_seed_fruit.setError("Please enter Avg Wt of seed fruit");
        }

        else if (str_fa_flag_four.equals("--- Choose Options ---") || str_fa_flag_four.equals("--- Select FA Flag ---")){
            VALID = false;

            View selectView = spn_fa_flag_four.getSelectedView();
            TextView selectedTextView = (TextView) selectView;

            selectedTextView.setError("");
            Toast.makeText(this, "Please select FA Flag", Toast.LENGTH_SHORT).show();
        }

        else if (expected_date_of_harvesting_four.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Expected date of Harvesting", Toast.LENGTH_SHORT).show();
        }
        else if (expected_date_of_dispatch_four.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Expected date of dispatch", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(four_breeder_remark.getText().toString())){
            VALID = false;
            four_breeder_remark.setError("Please enter Breeder Remark");
        }
//
//        else if (files.size()<=0){
//            VALID = false;
//            Toast.makeText(this, "Please select atleast 1 image", Toast.LENGTH_SHORT).show();
//        }



        return VALID;

    }

}