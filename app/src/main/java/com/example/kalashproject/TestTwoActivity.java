package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kalashproject.ModelList.ApiModel;
import com.example.kalashproject.Utils.FileUtil;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestTwoActivity extends AppCompatActivity {

    ImageView selectedImage;
    CircularProgressButton btnSubmit;
//    ServiceInterface serviceInterface;
    List<Uri> files = new ArrayList<>();

    private LinearLayout parentLinearLayout;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);



        parentLinearLayout= findViewById(R.id.parent_linear_layout);

        ImageView addImage = findViewById(R.id.iv_add_image);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
            }
        });

        btnSubmit = findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
                uploadImages();
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
        selectImage(TestTwoActivity.this);
    }

    //===== select image
    private void selectImage(Context context) {
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap img = (Bitmap) data.getExtras().get("data");
                        selectedImage.setImageBitmap(img);
                       // Picasso.get().load(getImageUri(TestTwoActivity.this,img)).into(selectedImage);

                        String imgPath = FileUtil.getPath(TestTwoActivity.this,getImageUri(TestTwoActivity.this,img));

                        files.add(Uri.parse(imgPath));
                        Log.e("image", imgPath);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img = data.getData();
                        Picasso.get().load(img).into(selectedImage);

                        String imgPath = FileUtil.getPath(TestTwoActivity.this,img);

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

    //===== bitmap to Uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "intuenty", null);
        Log.d("image uri",path);
        return Uri.parse(path);
    }

    //===== Upload files to server
    public void uploadImages(){

        btnSubmit.startAnimation();

        List<MultipartBody.Part> list = new ArrayList<>();

        for (Uri uri:files) {

            Log.i("uris",uri.getPath());

            Log.e("response","Uris: " +uri.getPath());

            list.add(prepareFilePart("file", uri));

            Log.e("response", "listSizeUpload: " +list);
        }

//        serviceInterface = ApiConstants.getClient().create(ServiceInterface.class);

        //ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);



//        Call<ApiModel> call = apiInterface.uploadNewsFeedImages(list);
//        Call<ApiModel> call = apiInterface.uploadNewsFeedImages(list);
        Log.e("response", "SizeOfList: " +list.size());
//        call.enqueue(new Callback<ApiModel>() {
//            @Override
//            public void onResponse(Call<ApiModel> call, Response<ApiModel> response) {
//                btnSubmit.revertAnimation();
//                try {
//
//                    ApiModel addMediaModel = response.body();
//
//                    Log.e("response","response: " +addMediaModel);
//
//                    if(addMediaModel.getStatusCode().equals("200")){
//                        Toast.makeText(TestTwoActivity.this, "Files uploaded successfuly", Toast.LENGTH_SHORT).show();
//                    }
//
//                    Log.e("main", "the status is ----> " + addMediaModel.getStatusCode());
//                    Log.e("main", "the message is ----> " + addMediaModel.getStatusMessage());
//
//                }
//                catch (Exception e){
//                    Log.d("Exception","|=>"+e.getMessage());
////
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiModel> call, Throwable t) {
//                btnSubmit.revertAnimation();
//                Log.i("my",t.getMessage());
//            }
//        });


        ApiInterface apiInterface1 = Myconfig.getRetrofit().create(ApiInterface.class);

        Log.e("response", "ListSize: " +list.size());

        for (int i=0; i<list.size(); i++){
            Log.e("respone", "uri: "+ list.get(i));
        }

        Call<ResponseBody> Result = apiInterface1.upload(list);

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                btnSubmit.revertAnimation();
                try {
                    String output = response.body().string();
                    Log.e("response", "SuccessRes" +output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){
                        Toast.makeText(TestTwoActivity.this, "Submitted Successfully!", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                btnSubmit.revertAnimation();
                Toast.makeText(TestTwoActivity.this, "" +t, Toast.LENGTH_SHORT).show();

                Log.e("response", "ErrorRes" +t);

            }
        });

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


    // this is all you need to grant your application external storage permision
    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TestTwoActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    uploadImages();
                }
                else {
                    Toast.makeText(TestTwoActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}