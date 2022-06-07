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
import android.provider.MediaStore;
import android.speech.RecognitionService;
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
import com.github.dhaval2404.imagepicker.ImagePicker;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionFormTwo extends AppCompatActivity {

    EditText total_female, ot_plant_in_f, ot_plant_in_m, details, disease_plant_in_m, pld_acre, reason_of_pld, rejected_acre, reason_rejected_acre, breeder_remark;
    TextView date_of_roughing, date_of_roughing_two, expected_date_of_dispatch_two;

    Spinner spn_fa_flag_two;
    String str_Fa_flag_id_two;
    String str_fa_flag_two;


    // Multiple Images via gallery

    ///Multiple Images
    ImageView selectedImage;
    List<Uri> files = new ArrayList<>();

    private LinearLayout parentLinearLayout;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    //Multiple images via camera

    ImageView selectedImageTwo;
    List<Uri> filesTwo = new ArrayList<>();
    private LinearLayout parentLinearLayoutTwo;


    ArrayList<Uri> iv_photo_list = new ArrayList<Uri>();
    ArrayList<FQ_flag_list> fq_flag_lists_two = new ArrayList<FQ_flag_list>();

    String str_total_female, str_ot_plant_in_f, str_ot_plant_in_m, str_details, str_disease_plant_in_m, str_pld_acre, str_reason_of_pld, str_rejected_acre, str_reason_rejected_acre, str_breeder_remark,
            str_date_of_roughing, str_date_of_roughing_two, str_expected_date_of_dispatch_two;

    DatePickerDialog.OnDateSetListener SetListnerDateOfRoughing;
    DatePickerDialog.OnDateSetListener Setdate_of_roughing_two;
    DatePickerDialog.OnDateSetListener SetExpected_date_of_dispatch_two;

    private CharSequence[] options = {"camera", "Gallery", "Cancel"};

    TextView inspection_two_tv_next;
    String path;

    String Date = new SimpleDateFormat("yyyymmdd", Locale.getDefault()).format(new Date());
    String Time = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());

    ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(InspectionFormTwo.this);


        spn_fa_flag_two = findViewById(R.id.spn_fa_flag_two);

        inspection_two_tv_next = findViewById(R.id.inspection_two_tv_next);


        //TextView
        date_of_roughing = findViewById(R.id.date_of_roughing);
        date_of_roughing_two = findViewById(R.id.date_of_roughing_two);
        expected_date_of_dispatch_two = findViewById(R.id.expected_date_of_dispatch_two);


        // Images


        parentLinearLayout = findViewById(R.id.parent_linear_layout);

        ImageView addImage = findViewById(R.id.iv_add_image);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
            }
        });

        // Initialize variables for second sending images

        parentLinearLayoutTwo = findViewById(R.id.parent_linear_layoutTwo);
        ImageView addImageTwo = findViewById(R.id.iv_add_imageTwo);


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

        getFaFlag();


        //onClick of second Image send
        addImageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageTwo();

            }
        });


        inspection_two_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Validations()) {

                    sendData();
                }


            }
        });


    }


    private void sendData() {


        List<MultipartBody.Part> list = new ArrayList<>();
        List<MultipartBody.Part> listTwo = new ArrayList<>();

        for (Uri uri : files) {

            Log.i("uris", uri.getPath());

            Log.e("response", "Uris: " + uri.getPath());
            list.add(prepareFilePart("gallery_document_name[]", uri));

            Log.e("response", "listSizeUpload: " + list);
        }

        for (Uri uri : filesTwo) {
            Log.i("urisTwo", uri.getPath());

            Log.e("response", "Uris Two: " + uri.getPath());

            listTwo.add(prepareFilePart("camera_document_name[]", uri));

            Log.e("response", "listSizeUpload: " + listTwo
            );

        }

//        str_total_female = total_female.getText().toString().trim();
//        str_ot_plant_in_f = ot_plant_in_f.getText().toString().trim();
//        str_ot_plant_in_m = ot_plant_in_m.getText().toString().trim();
//        str_details = details.getText().toString().trim();
//        str_disease_plant_in_m = disease_plant_in_m.getText().toString().trim();
//        str_pld_acre = pld_acre.getText().toString().trim();
//        str_reason_of_pld = reason_of_pld.getText().toString().trim();
//        str_rejected_acre = rejected_acre.getText().toString().trim();
//        str_reason_rejected_acre = reason_rejected_acre.getText().toString().trim();
//        str_breeder_remark = breeder_remark.getText().toString().trim();
//
//        str_date_of_roughing = date_of_roughing.getText().toString().trim();
//        str_date_of_roughing_two = date_of_roughing_two.getText().toString().trim();
//        str_expected_date_of_dispatch_two = expected_date_of_dispatch_two.getText().toString().trim();

        //Preparing Request Body

        RequestBody str_total_female = RequestBody.create(MediaType.parse("text/plain"), total_female.getText().toString().trim());
        RequestBody str_ot_plant_in_f = RequestBody.create(MediaType.parse("text/plain"), ot_plant_in_f.getText().toString().trim());
        RequestBody str_ot_plant_in_m = RequestBody.create(MediaType.parse("text/plain"), ot_plant_in_m.getText().toString().trim());
        RequestBody str_details = RequestBody.create(MediaType.parse("text/plain"), details.getText().toString().trim());
        RequestBody str_disease_plant_in_m = RequestBody.create(MediaType.parse("text/plain"), disease_plant_in_m.getText().toString().trim());
        RequestBody str_pld_acre = RequestBody.create(MediaType.parse("text/plain"), pld_acre.getText().toString().trim());
        RequestBody str_reason_of_pld = RequestBody.create(MediaType.parse("text/plain"), reason_of_pld.getText().toString().trim());
        RequestBody str_rejected_acre = RequestBody.create(MediaType.parse("text/plain"), rejected_acre.getText().toString().trim());
        RequestBody str_reason_rejected_acre = RequestBody.create(MediaType.parse("text/plain"), reason_rejected_acre.getText().toString().trim());
        RequestBody str_breeder_remark = RequestBody.create(MediaType.parse("text/plain"), breeder_remark.getText().toString().trim());
        RequestBody str_date_of_roughing = RequestBody.create(MediaType.parse("text/plain"), date_of_roughing.getText().toString().trim());
        RequestBody str_date_of_roughing_two = RequestBody.create(MediaType.parse("text/plain"), date_of_roughing_two.getText().toString().trim());
        RequestBody str_expected_date_of_dispatch_two = RequestBody.create(MediaType.parse("text/plain"), expected_date_of_dispatch_two.getText().toString().trim());


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
        Log.e("sendInspectionTwo", "fdo_id: " + Shared_Preferences.getPrefs(InspectionFormTwo.this, "Reg_id"));


        RequestBody fdo_id = RequestBody.create(MediaType.parse("text/plain"), Shared_Preferences.getPrefs(InspectionFormTwo.this, "Reg_id"));
        Log.e("fdo_id", "" + fdo_id);

        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.second_inspection_add(
                RequestBody.create(MediaType.parse("text/plain"), "1"),
                fdo_id,
                RequestBody.create(MediaType.parse("text/plain"), "abc"),
                str_ot_plant_in_f,
                str_date_of_roughing,
                str_ot_plant_in_m,
                str_date_of_roughing_two,
                str_disease_plant_in_m,
                str_details, str_pld_acre,
                str_reason_of_pld,
                str_rejected_acre,
                str_reason_rejected_acre,
                RequestBody.create(MediaType.parse("text/plain"), str_Fa_flag_id_two),
                str_expected_date_of_dispatch_two,
                str_breeder_remark,
                list,
                listTwo
        );

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    String output = response.body().string();
                    Log.e("sendDataResponse", "" + output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {

                        Toast.makeText(InspectionFormTwo.this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
                        Log.e("error", "1" + jsonObject.getString("ResponseMessage"));
                        Intent ii = new Intent(InspectionFormTwo.this, MainActivity.class);
                        startActivity(ii);
                        finish();

                    } else if (jsonObject.getString("ResponseCode").equals("0")) {

                        Toast.makeText(InspectionFormTwo.this, "" + jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        Log.e("error", "0" + jsonObject.getString("ResponseMessage"));
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(InspectionFormTwo.this, "" + t, Toast.LENGTH_SHORT).show();

                Log.e("error", "Failure" + t);
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }
        });

    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {

        File file = new File(fileUri.getPath());
        Log.i("here is error", file.getAbsolutePath());
        // create RequestBody instance from file

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }


    private void getFaFlag() {
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

                        fq_flag_lists_two.add(new FQ_flag_list("--- Select FA Flag ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            fq_flag_lists_two.add(new FQ_flag_list(object));
                        }
                        ArrayAdapter<FQ_flag_list> cropAdapter = new ArrayAdapter<FQ_flag_list>(InspectionFormTwo.this, android.R.layout.simple_spinner_item, fq_flag_lists_two);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spn_fa_flag_two.setAdapter(cropAdapter);

                        spn_fa_flag_two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                Log.e("FA_Flag_Item", " " + item);
                                str_Fa_flag_id_two = fq_flag_lists_two.get(i).getId();
                                //    Req_Fa_flag_id_two = RequestBody.create(MediaType.parse("text/plain"),str_Fa_flag_id_two);

                                str_fa_flag_two = spn_fa_flag_two.getSelectedItem().toString();
                                Log.e("str_fa_flag_two", "str_fa_flag_two: " + str_fa_flag_two);
                                Log.e("FA_Id", " " + str_Fa_flag_id_two);

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


    private void addImage() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        parentLinearLayout.isFocusable();

        selectedImage = rowView.findViewById(R.id.number_edit_text);
        selectImage(InspectionFormTwo.this);

    }

    private void selectImage(Context context) {
        //requestPermission();

        Dexter.withContext(InspectionFormTwo.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                if (multiplePermissionsReport.areAllPermissionsGranted()) {

                    final CharSequence[] options = {"Choose from Gallery", "Cancel"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setTitle("Choose a Media");

                    builder.setItems(options, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int item) {

                            if (options[item].equals("Take Photo")) {
//                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(takePicture, 0);

                            } else if (options[item].equals("Choose from Gallery")) {
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                            } else if (options[item].equals("Cancel")) {
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();

                } else if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {

                    Toast.makeText(context, "Permission is Denied", Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(InspectionFormTwo.this, MainActivity.class);
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

                        String imgPath = FileUtil.getPath(InspectionFormTwo.this, getImageUri(InspectionFormTwo.this, img));

                        files.add(Uri.parse(imgPath));
                        Log.e("image", imgPath);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img = data.getData();
                        Picasso.get().load(img).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormTwo.this, img);

                        files.add(Uri.parse(imgPath));
                        /////Testing start
                        Log.e("newresponse", "Uri1: " + imgPath);
                        Uri temp = Uri.parse(imgPath);
                        Log.e("newresponse", "addedInList: " + Uri.parse(imgPath));
                        Log.e("newresponse", "addedInList: " + temp);
                        Log.e("image", imgPath);


                        ///// Testing End

                    }
                    break;

                case 2:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImageTwo.setImageBitmap(img);

                        Log.e("case 2", " " + img);
                        // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormTwo.this, getImageUri(InspectionFormTwo.this, img));

                        filesTwo.add(Uri.parse(imgPath));

                        Log.e("Response", "case 2 Files " + filesTwo);

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
        Log.d("image uri", path);
        return Uri.parse(path);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(InspectionFormTwo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }


    //===== add image in layout
    public void addImageTwo() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayoutTwo.addView(rowView, parentLinearLayoutTwo.getChildCount() - 1);
        parentLinearLayoutTwo.isFocusable();

        selectedImageTwo = rowView.findViewById(R.id.number_edit_text);
        selectImageTwo(InspectionFormTwo.this);
    }


    //===== select image
    private void selectImageTwo(Context context) {

        Dexter.withContext(InspectionFormTwo.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {

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

    private boolean Validations() {

        boolean VALID = true;

        if (TextUtils.isEmpty(total_female.getText().toString().trim())) {
            VALID = false;
            total_female.setError("Please enter total female");
        } else if (TextUtils.isEmpty(ot_plant_in_f.getText().toString().trim())) {
            VALID = false;
            ot_plant_in_f.setError("Please select OT/Disease Plant in F");
        } else if (date_of_roughing.getText().toString().trim().equals("Select date")) {
            VALID = false;
            Toast.makeText(this, "Please Select Date of Roughing", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ot_plant_in_m.getText().toString().trim())) {
            VALID = false;
            ot_plant_in_m.setError("Please enter OT Plant in M");
        } else if (TextUtils.isEmpty(details.getText().toString().trim())) {
            VALID = false;
            details.setError("Please enter Details");
        } else if (TextUtils.isEmpty(disease_plant_in_m.getText().toString().trim())) {
            VALID = false;
            disease_plant_in_m.setError("Please enter Disease Plant in M");
        } else if (files.size() == 0) {
            VALID = false;
            Toast.makeText(this, "Please add At least 1 image", Toast.LENGTH_SHORT).show();
        } else if (date_of_roughing_two.getText().toString().trim().equals("Select date")) {
            VALID = false;
            Toast.makeText(this, "Please Select Date of Roughing", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pld_acre.getText().toString().trim())) {
            VALID = false;
            pld_acre.setError("Please enter PLD Acre");
        } else if (TextUtils.isEmpty(reason_of_pld.getText().toString().trim())) {
            VALID = false;
            reason_of_pld.setError("Please enter Reasons of Pld");
        } else if (TextUtils.isEmpty(rejected_acre.getText().toString().trim())) {
            VALID = false;
            rejected_acre.setError("Please enter Rejected Acre");
        } else if (TextUtils.isEmpty(reason_rejected_acre.getText().toString().trim())) {
            VALID = false;
            reason_rejected_acre.setError("Please enter Reasons of Rejected Acre");
        } else if (str_fa_flag_two.equals("--- Choose Options ---") || str_fa_flag_two.equals("--- Select FA Flag ---")) {
            VALID = false;
            Toast.makeText(this, "Please select FA Flag", Toast.LENGTH_SHORT).show();

        } else if (expected_date_of_dispatch_two.getText().toString().equals("Select date")) {
            VALID = false;
            Toast.makeText(this, "Please select expected date of dispatch", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(breeder_remark.getText().toString().trim())) {
            VALID = false;
            breeder_remark.setError("Please enter Breeder Remark");
        } else if (filesTwo.size() == 0) {
            VALID = false;
            Toast.makeText(this, "Please select At least 1 image", Toast.LENGTH_SHORT).show();
        }


        return VALID;
    }

}