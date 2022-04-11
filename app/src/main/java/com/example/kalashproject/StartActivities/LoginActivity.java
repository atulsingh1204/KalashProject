package com.example.kalashproject.StartActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.MyLibrary.CheckNetwork;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.R;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.AppConfig;
import com.example.kalashproject.WebService.Myconfig;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText useremail,userpassword;
    Button btn_login;
    TextView tv_forget;

    private boolean isRememberUserLogin = false;
    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useremail = findViewById(R.id.useremail);
        userpassword = findViewById(R.id.userpassword);
        btn_login = findViewById(R.id.btn_login);
        tv_forget = findViewById(R.id.tv_forget);

        appConfig = new AppConfig(this);
        if (appConfig.isUserLogin()){
            String name  = appConfig.getNameofUser();
            Intent ii = new Intent(LoginActivity.this, MainActivity.class);
            ii.putExtra("name", name);
            startActivity(ii);
            finish();
        }



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
                Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getLogin(useremail.getText().toString().trim(), userpassword.getText().toString().trim());
                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        String output = "";

                        try {
                            output = response.body().string();
                            JSONObject jsonObject = new JSONObject(output);

                            if (jsonObject.getString("ResponseCode").equals("1"))
                            {

                                appConfig.updateUserLogin(true);
                                JSONArray jsonArray = jsonObject.getJSONArray("Data");
                                JSONObject object =jsonArray.getJSONObject(0);
                                Shared_Preferences.setPrefs(LoginActivity.this, "Reg_id", object.getString("id"));
                                Shared_Preferences.setPrefs(LoginActivity.this, "User_name", object.getString("user_name"));
                                Shared_Preferences.setPrefs(LoginActivity.this, "Email_id", object.getString("emailid"));



                                Intent ii = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(ii);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });





//        checkConnection();

//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
//                final Call<ResponseBody> result = apiInterface.getLogin();
//                result.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
//
//
//
//
//
//
//
////                if(useremail.getText().toString().equals("abc@gmail.com") && userpassword.getText().toString().equals("abc")){
////                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                    startActivity(intent);
////                    finish();
////                }else{
////                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
////                }
//
//            }
//        });

    }

//    private void checkConnection() {
//        if (CheckNetwork.isInternetAvailable(this))
//        {
//            init();
//        }else {
//
////            Snackbar.make(getWindow().getDecorView().getRootView(),"Please Check Internet Connection", Snackbar.LENGTH_LONG)
////                            .setAction("RETRY", new View.OnClickListener() {
////                                @Override
////                                public void onClick(View view) {
////                                    checkConnection();
////                                }
////                            })
////                    .setActionTextColor(getResources().getColor(android.R.color.holo_green_dark))
////                    .setDuration(50000)
////                    .show();
//
//            Toast.makeText(this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
//
//
//        }
//    }



}