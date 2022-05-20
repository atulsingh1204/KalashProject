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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.example.kalashproject.ModelList.CropList;
import com.example.kalashproject.ModelList.FQ_flag_list;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
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

public class InspectionFormOne extends AppCompatActivity {

    TextView male_sowing_date, female_sowing_date, expected_date_of_dispatch;

    EditText male_row, female_row, male_plant_in_row, female_plant_in_row, pld_acre, reason_of_pld, rejected_acre, reason_of_rejected_pld, first_breeder_remark;

    String MaleRow, FemaleRow, MalePlantInRow, FemalePlantInRow, PLDAcre, ReasonOfPLD, RejectedAcre, ReasonOfRejectedPLD;
    String MaleSowingDate,FemaleSowingDate, ExpectedDateOfDispatch;



    ///Multiple Images
    ImageView selectedImage;
    List<Uri> files = new ArrayList<>();

    private LinearLayout parentLinearLayout;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;











    ArrayList<FQ_flag_list> fq_flag_lists = new ArrayList<FQ_flag_list>();




    //String str_isolation;

   // String str_Fa_flag_id = "";

    RequestBody str_isolation, str_Fa_flag_id;

    RequestBody order_id , fdo_id;


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
        first_breeder_remark = findViewById(R.id.first_breeder_remark);

        inspection_one_tv_next = findViewById(R.id.inspection_one_tv_next);



        //Spinner

        spn_isolation = findViewById(R.id.spn_isolation);
        spn_fa_flag = findViewById(R.id.spn_fa_flag);

        // Images


        parentLinearLayout= findViewById(R.id.parent_linear_layout);

        ImageView addImage = findViewById(R.id.iv_add_image);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
            }
        });


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





        inspection_one_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendInspectionFormOne();

               // sendImages();
                Toast.makeText(InspectionFormOne.this, "Clicked!", Toast.LENGTH_SHORT).show();



            }
        });





    }



//    private void sendImages()
//    {
//
//       // imageFile = new File(filepath);
//
////        Log.e("profileimg", "" + imageFile);
////        Log.e("profileimg", "" + filepath);
//
//        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//        MultipartBody.Part files = MultipartBody.Part.createFormData("files", imageFile.getName(), reqBody);
//
//        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
//        Log.e("response", "ImageList: " +imagesList);
//        Log.e("response", "ImageList: " +files);
//        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.SimpleImagesList(files);
//
//        Result.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    String output = response.body().string();
//
//                    JSONObject jsonObject = new JSONObject(output);
//                    if (jsonObject.getString("ResponseCode").equals("1")){
//
//                        Toast.makeText(InspectionFormOne.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
//                        Log.e("response","SuccessResponse" +jsonObject.getString("ResponseMessage"));
//                    }
//                    else {
//                        Toast.makeText(InspectionFormOne.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
//                        Log.e("response","SuccessZero" +jsonObject.getString("ResponseMessage"));
//                    }
//
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//
//                Toast.makeText(InspectionFormOne.this, ""+t, Toast.LENGTH_SHORT).show();
//
//                Log.e("response", "FailureResponse" +t);
//            }
//        });
//    }






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

                    Log.e("fq", " " +output);

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
                               //  str_Fa_flag_id = RequestBody.create(MediaType.parse("text/plain"),fq_flag_lists.get(i).getId());



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
               // str_isolation = spn_isolation.getSelectedItem().toString();

                 str_isolation = RequestBody.create(MediaType.parse("text/plain"), spn_isolation.getSelectedItem().toString().trim());



                Log.e("spinner","str_isolation" +str_isolation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void sendInspectionFormOne() {



//        MaleSowingDate = male_sowing_date.getText().toString();
//        FemaleSowingDate = female_sowing_date.getText().toString();
//        MaleRow = male_row.getText().toString().trim();
//        FemaleRow = female_row.getText().toString().trim();
//        MalePlantInRow = male_plant_in_row.getText().toString().trim();
//        FemalePlantInRow = female_plant_in_row.getText().toString().trim();
//        PLDAcre = pld_acre.getText().toString().trim();
//        ReasonOfPLD = reason_of_pld.getText().toString().trim();
//        RejectedAcre = rejected_acre.getText().toString().trim();
//        ReasonOfRejectedPLD = reason_of_rejected_pld.getText().toString().trim();
//        ExpectedDateOfDispatch = expected_date_of_dispatch.getText().toString();


        //Preparing Request Body

        RequestBody MaleSowingDate = RequestBody.create(MediaType.parse("text/plain"), male_sowing_date.getText().toString().trim());
        RequestBody FemaleSowingDate = RequestBody.create(MediaType.parse("text/plain"), female_sowing_date.getText().toString().trim());
        RequestBody MaleRow = RequestBody.create(MediaType.parse("text/plain"), male_row.getText().toString().trim());
        RequestBody FemaleRow = RequestBody.create(MediaType.parse("text/plain"), female_row.getText().toString().trim());
        RequestBody MalePlantInRow = RequestBody.create(MediaType.parse("text/plain"), male_plant_in_row.getText().toString().trim());
        RequestBody FemalePlantInRow = RequestBody.create(MediaType.parse("text/plain"), female_plant_in_row.getText().toString().trim());
        RequestBody PLDAcre = RequestBody.create(MediaType.parse("text/plain"), pld_acre.getText().toString().trim());
        RequestBody ReasonOfPLD = RequestBody.create(MediaType.parse("text/plain"), reason_of_pld.getText().toString().trim());
        RequestBody RejectedAcre = RequestBody.create(MediaType.parse("text/plain"), rejected_acre.getText().toString().trim());
        RequestBody ReasonOfRejectedPLD = RequestBody.create(MediaType.parse("text/plain"), reason_of_rejected_pld.getText().toString().trim());
        RequestBody ExpectedDateOfDispatch = RequestBody.create(MediaType.parse("text/plain"), expected_date_of_dispatch.getText().toString().trim());
        RequestBody breeder_remark = RequestBody.create(MediaType.parse("text/plain"), first_breeder_remark.getText().toString().trim());

        order_id = RequestBody.create(MediaType.parse("text/plain"), "1");
        fdo_id = RequestBody.create(MediaType.parse("text/plain"), Shared_Preferences.getPrefs(InspectionFormOne.this, "Reg_id"));
        str_Fa_flag_id = RequestBody.create(MediaType.parse("text/plain"),"1");

       // RequestBody str_isolation = str_isolation;





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
        Log.e("sendFormOne","fq_flag_id " +str_Fa_flag_id);
        Log.e("sendFormOne","breeder_remark " +breeder_remark);
        Log.e("sendFormOne","fdo_id " +fdo_id);
        Log.e("sendFormOne","order_id " +order_id);




        //Upload Images

        List<MultipartBody.Part> list = new ArrayList<>();

        for (Uri uri:files){

            Log.i("uris",uri.getPath());

            Log.e("response","Uris: " +uri.getPath());

            list.add(prepareFilePart("document_name[]", uri));

            Log.e("response", "listSizeUpload: " +list);
        }

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.first_inspection_add(  order_id,
                                                                                            fdo_id,
                                                                                            str_Fa_flag_id,
                                                                                            breeder_remark,
                                                                                            MaleSowingDate,
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
                                                                                            list
                                                                                            );

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
        Log.e("uri","uriNew: " +uri);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImage.setImageBitmap(img);
                        // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormOne.this,getImageUri(InspectionFormOne.this,img));

                        files.add(Uri.parse(imgPath));
                        Log.e("image", imgPath);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img = data.getData();
                        Picasso.get().load(img).into(selectedImage);

                        String imgPath = FileUtil.getPath(InspectionFormOne.this,img);

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
            }
        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }







    private void addImage()
    {

        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.image, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        parentLinearLayout.isFocusable();

        selectedImage = rowView.findViewById(R.id.number_edit_text);
        selectImage(InspectionFormOne.this);

    }

    private void selectImage(Context context)
    {

        Dexter.withContext(InspectionFormOne.this)
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

}