package com.example.kalashproject;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.GrowerorVendorList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.common.api.Api;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {


    ImageView iv_photo1,iv_photo2,iv_photo3,iv_photo4,iv_photo5;
    Button submit;
    Bitmap bitmap;
    String encodedimage;
    String photo1_path="", photo2_path="";
    int i;
    String filePath1, filePath2;
    String path = "";
    ArrayList<MultipartBody.Part> imageList = new ArrayList<MultipartBody.Part>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        iv_photo1 = findViewById(R.id.iv_photo1);
        iv_photo2 = findViewById(R.id.iv_photo2);
        iv_photo3 = findViewById(R.id.iv_photo3);
        iv_photo4 = findViewById(R.id.iv_photo4);
        iv_photo5 = findViewById(R.id.iv_photo5);

        submit = findViewById(R.id.submit);



        iv_photo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dexter.withContext(getApplicationContext())
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                                if (multiplePermissionsReport.areAllPermissionsGranted()){
                                    Intent ii = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(ii,1);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });




        iv_photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dexter.withContext(getApplicationContext())
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                                if (multiplePermissionsReport.areAllPermissionsGranted()){
                                    Intent ii = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(ii,2);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });



//        iv_photo2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dexter.withContext(getApplicationContext())
//                        .withPermission(Manifest.permission.CAMERA)
//                        .withListener(new PermissionListener() {
//                            @Override
//                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                                Intent ii = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(ii,2);
//                            }
//
//                            @Override
//                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//
//                                permissionToken.continuePermissionRequest();
//                            }
//                        }).check();
//            }
//        });


        iv_photo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(TestActivity.this)
                        .crop()
                        .cameraOnly()//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(3);


            }
        });


        iv_photo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(TestActivity.this)
                        .crop()
                        .cameraOnly()//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(4);


            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadMultipleImage();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            bitmap = (Bitmap)data.getExtras().get("data");
            iv_photo1.setImageBitmap(bitmap);
            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
            File image1Path = new File(getRealPathFromURI(tempUri));

            Log.e("path", "img1_uri" +tempUri);
            Log.e("path", "img1_path" +image1Path);
            // encodebitmap(bitmap);
        }

        else if (requestCode == 2 && resultCode == RESULT_OK){
            bitmap = (Bitmap)data.getExtras().get("data");
            iv_photo2.setImageBitmap(bitmap);
            Uri tempUri2 = getImageUri(getApplicationContext(), bitmap);
            File image2Path = new File(getRealPathFromURI(tempUri2));
            Log.e("path", "img2_uri" +tempUri2);
            Log.e("path", "img2_path" +image2Path);
        }

        else if (requestCode == 3 && resultCode == RESULT_OK){

            Uri filePath1 = data.getData();
            path = filePath1.getPath();
            Log.e("response", "path: " +path);
            Log.e("response", "Uri: " +filePath1);
            iv_photo3.setImageURI(filePath1);
            Log.e("response","image3 " +filePath1);
            MultipartBody.Part image = prepareImagePart(path, "image1");
           // imageList.add(image);

            Log.e("response","image " +imageList.size());

//            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
//            File image1Path = new File(getRealPathFromURI(tempUri));

        }

        else if (requestCode == 4 && resultCode == RESULT_OK){

            Uri filePath2 = data.getData();
            iv_photo4.setImageURI(filePath2);

//            bitmap = (Bitmap)data.getExtras().get("data");
//            iv_photo4.setImageBitmap(bitmap);

            Log.e("imagePicker","image4 " +filePath2);

//            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
//            File image1Path = new File(getRealPathFromURI(tempUri));

        }

    }

//    private void encodebitmap(Bitmap bitmap)
//    {
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//
//        byte[] byteofimages =byteArrayOutputStream.toByteArray();
//        encodedimage = android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
//
//    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }



    private void uploadMultipleImage()
    {
//        MultipartBody.Part image1 = prepareImagePart(filePath1, "image1");
//        MultipartBody.Part image2 = prepareImagePart(filePath2, "image2");

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> uploader = apiInterface.uploadImages(imageList);

        uploader.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(TestActivity.this, "Images Inserted Successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(TestActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private MultipartBody.Part prepareImagePart(String path, String partName){
        File file = new File(path);
        Log.e("response","file " +file);
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(Uri.fromFile(file))),file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }

}