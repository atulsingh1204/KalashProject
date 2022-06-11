package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionFormThree extends AppCompatActivity {


    EditText three_total_female, three_ot_plant_in_f, three_details, disease_plant_in_f, three_pld_acre, three_reason_of_pld, three_rejected_acre, three_reason_rejected_acre, three_exi_fruit, three_exp_fruit, three_total_fruit, three_avg_wt_seed_fruit, three_breeder_remark;
    Spinner spn_fa_flag_three;
    TextView three_date_of_roughing, three_polln_start_date, expected_date_of_dispatch_two;

    ArrayList<FQ_flag_list> fq_flag_lists_three = new ArrayList<FQ_flag_list>();
    String str_Fa_flag_id_three;

    // Multiple Images via gallery

    ///Multiple Images
    ImageView selectedImage;
    List<Uri> files = new ArrayList<>();

    private LinearLayout parentLinearLayout;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    //Multiple images via camera

    ImageView selectedImageTwo;
    List<Uri> filesTwo = new ArrayList<>();
    private  LinearLayout parentLinearLayoutTwo;

    String id="";


    TextView inspection_three_tv_next;

    String str_three_total_female, str_three_ot_plant_in_f, str_three_details, str_disease_plant_in_f, str_three_pld_acre, str_three_reason_of_pld, str_three_rejected_acre, str_three_reason_rejected_acre, str_three_exi_fruit, str_three_exp_fruit, str_three_total_fruit, str_three_avg_wt_seed_fruit, str_three_breeder_remark;
    String str_spn_fa_flag_three;
    String str_three_date_of_roughing, str_three_polln_start_date, str_expected_date_of_dispatch_two;

    DatePickerDialog.OnDateSetListener three_SetListnerDateOfRoughing;
    DatePickerDialog.OnDateSetListener three_Setdate_of_roughing_two;
    DatePickerDialog.OnDateSetListener three_SetExpected_date_of_dispatch_two;

    ProgressDialog progressDialog;

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

        progressDialog =  new ProgressDialog(InspectionFormThree.this);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("Response", "Id is: " +id);

        //TextView

        three_date_of_roughing = findViewById(R.id.three_date_of_roughing);
        three_polln_start_date = findViewById(R.id.three_polln_start_date);
        expected_date_of_dispatch_two = findViewById(R.id.expected_date_of_dispatch_two);

        inspection_three_tv_next = findViewById(R.id.inspection_three_tv_next);

        //ImageView
        parentLinearLayout= findViewById(R.id.parent_linear_layout);

        ImageView addImage = findViewById(R.id.iv_add_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection Form Three");


        getFaFlag();

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


        //Initialize for second image

        parentLinearLayoutTwo =  findViewById(R.id.parent_linear_layoutTwo);
        ImageView addImageTwo = findViewById(R.id.iv_add_imageTwo);

        addImageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageTwo();
            }
        });


        inspection_three_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
                    Log.e("str_spn_fa_flag_three" ,"str_spn_fa_flag_three: " +str_spn_fa_flag_three);
                    Log.e("Validations", "Validations: " +Validations());

                    if (Validations()){
                        sendData();
                    }

//                }catch (Exception e){
//                    e.printStackTrace();
//                    Toast.makeText(InspectionFormThree.this, "" +e, Toast.LENGTH_SHORT).show();
//                }

            }
        });






    }



    private void sendData()
    {
        requestPermission();

        List<MultipartBody.Part> list = new ArrayList<>();
        List<MultipartBody.Part> listTwo = new ArrayList<>();

        for (Uri uri: files){


            Log.i("uris",uri.getPath());

            Log.e("response","Uris: " +uri.getPath());

            list.add(prepareFilePart("gallery_document_name[]", uri));

            Log.e("response", "listSizeUpload: " +list);
        }


        for (Uri uri:filesTwo){
            Log.i("urisTwo",uri.getPath());

            Log.e("response","Uris Two: " +uri.getPath());

            listTwo.add(prepareFilePart("camera_document_name[]", uri));

            Log.e("response", "listSizeUpload: " +listTwo
            );

        }


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


        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.third_inspection_add(
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),id),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"), Shared_Preferences.getPrefs(InspectionFormThree.this,"Reg_id")),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_ot_plant_in_f),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_details),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_disease_plant_in_f),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_date_of_roughing),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_polln_start_date),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_pld_acre),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_reason_of_pld),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_rejected_acre),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"), str_three_reason_rejected_acre),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"), str_three_exi_fruit),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"), str_three_exp_fruit),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_total_fruit),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"), str_three_avg_wt_seed_fruit),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_Fa_flag_id_three),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_expected_date_of_dispatch_two),
                                                                                                    RequestBody.create(MediaType.parse("text/plain"),str_three_breeder_remark),
                                                                                                    list,
                                                                                                    listTwo
                                                                                                        );

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                try {

                    String output = response.body().string();

                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(InspectionFormThree.this, "Data Sent Successfully!", Toast.LENGTH_SHORT).show();
                        Log.e("Check_Response","" +jsonObject.getString("ResponseMessage"));

                        Intent intent = new Intent(InspectionFormThree.this, MainActivity.class);
                        startActivity(intent);
                        finish();




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

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }
        });






    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

                        fq_flag_lists_three.add(new FQ_flag_list("--- Select FA Flag ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            fq_flag_lists_three.add(new FQ_flag_list(object));
                        }
                        ArrayAdapter<FQ_flag_list> cropAdapter = new ArrayAdapter<FQ_flag_list>(InspectionFormThree.this, android.R.layout.simple_spinner_item, fq_flag_lists_three);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spn_fa_flag_three.setAdapter(cropAdapter);

                        spn_fa_flag_three.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                Log.e("FA_Flag_Item", " " + item);
                                str_Fa_flag_id_three = fq_flag_lists_three.get(i).getId();

                                str_spn_fa_flag_three = spn_fa_flag_three.getSelectedItem().toString();
                                Log.e("check", "str_spn_fa_flag_three: " +str_spn_fa_flag_three);
                                Log.e("FA_Id"," " +str_Fa_flag_id_three);

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


    //===== add image in layout
    public void addImage() {
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        parentLinearLayout.isFocusable();

        selectedImage = rowView.findViewById(R.id.number_edit_text);
        selectImage(InspectionFormThree.this);
    }

    //===== select image
    private void selectImage(Context context) {

        Dexter.withContext(InspectionFormThree.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                if (multiplePermissionsReport.areAllPermissionsGranted()){




                    final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

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
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                            } else if (options[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();

                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImage.setImageBitmap(img);
                        // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormThree.this,getImageUri(InspectionFormThree.this,img));

                        files.add(Uri.parse(imgPath));
                        Log.e("image", imgPath);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img = data.getData();
                        Picasso.get().load(img).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormThree.this,img);

                        files.add(Uri.parse(imgPath));
                        /////Testing start
                        Log.e("newresponse" , "Uri1: " +imgPath);
                        Uri temp = Uri.parse(imgPath);
                        Log.e("newresponse" , "addedInList: " +Uri.parse(imgPath));
                        Log.e("newresponse" , "addedInList: " +temp);
                        Log.e("image", imgPath);


                        ///// Testing End

                    }
                    break;

                case 2:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImageTwo.setImageBitmap(img);

                        Log.e("case 2", " " +img);
                        // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormThree.this,getImageUri(InspectionFormThree.this,img));

                        filesTwo.add(Uri.parse(imgPath));

                        Log.e("Response","case 2 Files " +filesTwo);

                        Log.e("image", imgPath);
                    }

                    break;
            }
        }
    }


    //===== bitmap to Uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "intuenty", null);
        //Log.d("image uri",path);
        Log.e("image_path", " " +path);
        return Uri.parse(path);
    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(InspectionFormThree.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
        }
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

    //===== add image in layout
    public void addImageTwo() {
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayoutTwo.addView(rowView, parentLinearLayoutTwo.getChildCount() - 1);
        parentLinearLayoutTwo.isFocusable();

        selectedImageTwo = rowView.findViewById(R.id.number_edit_text);
        selectImageTwo(InspectionFormThree.this);
    }

    //===== select image
    private void selectImageTwo(Context context) {

        Dexter.withContext(InspectionFormThree.this)
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
                                startActivityForResult(takePicture, 2);

//                } else if (options[item].equals("Choose from Gallery")) {
//                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                            } else if (options[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();
            }
        }).check();





    }

    private boolean Validations(){
        boolean VALID = true;

        if (TextUtils.isEmpty(three_total_female.getText().toString().trim())){
            VALID = false;
            three_total_female.setError("Please enter total female");
        }
        else if (TextUtils.isEmpty(three_ot_plant_in_f.getText().toString().trim())){
            VALID = false;

            three_ot_plant_in_f.setError("Please enter OT Plant in F");
        }

        else if (TextUtils.isEmpty(three_details.getText().toString().trim())){
            VALID = false;
            three_details.setError("Please enter Details");
        }

        else if (TextUtils.isEmpty(disease_plant_in_f.getText().toString().trim())){
            VALID = false;
            disease_plant_in_f.setError("Please enter Disease Plant in F");
        }
        else if (files.size()==0){
            VALID = false;
            Toast.makeText(this, "Please upload atleast 1 image", Toast.LENGTH_SHORT).show();
        }

        else if (three_date_of_roughing.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Date of Roughing", Toast.LENGTH_SHORT).show();
        }

        else if (three_polln_start_date.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Polln Start Date", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(three_pld_acre.getText().toString().trim())){
            VALID = false;
            three_pld_acre.setError("Please select PLD Acre");
        }

        else if (TextUtils.isEmpty(three_reason_of_pld.getText().toString().trim())){
            VALID = false;
            three_reason_of_pld.setError("Please select Reasons of PLD");
        }

        else if (TextUtils.isEmpty(three_rejected_acre.getText().toString().trim())){
            VALID = false;
            three_rejected_acre.setError("Please select Reject Acre");
        }
        else if (TextUtils.isEmpty(three_reason_rejected_acre.getText().toString().trim())){
            VALID = false;
            three_reason_rejected_acre.setError("Please select Reasons of Rejected Acre");
        }

        else if (TextUtils.isEmpty(three_exi_fruit.getText().toString().trim())){
            VALID = false;
            three_exi_fruit.setError("Please select Exi Fruit");
        }
        else if (TextUtils.isEmpty(three_exp_fruit.getText().toString().trim())){
            VALID = false;
            three_exp_fruit.setError("Please select Exp Fruit");
        }

        else if (TextUtils.isEmpty(three_total_fruit.getText().toString().trim())){
            VALID = false;

            three_total_fruit.setError("Please select Total Fruit");
        }

        else if (TextUtils.isEmpty(three_avg_wt_seed_fruit.getText().toString().trim())){
            VALID = false;
            three_avg_wt_seed_fruit.setError("Please select Avg Weight of Seed Fruit");
        }

        else if (str_spn_fa_flag_three.equals("--- Select FA Flag ---") || str_spn_fa_flag_three.equals("--- Choose Options ---")){
            VALID = false;

            View selectedView = spn_fa_flag_three.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("Please Select FA Flag");
            Toast.makeText(this, "Please select FA Flag", Toast.LENGTH_SHORT).show();
        }

        else if (expected_date_of_dispatch_two.getText().toString().trim().equals("Select date")){
            VALID = false;
            Toast.makeText(this, "Please select Expected Date of Dispatch", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(three_breeder_remark.getText().toString().trim())){
            VALID = false;
            three_breeder_remark.setError("Please select Breeder Remark");
        }

        else if (filesTwo.size()==0){
            VALID = false;
            Toast.makeText(this, "Please Select At least 1 Image", Toast.LENGTH_SHORT).show();
        }

        return VALID;
    }

}