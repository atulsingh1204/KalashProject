package com.example.kalashproject.WebService;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyConfig
{
    //https://sumagosolutions.in/Appointments_Services_Booking_Application/API_User/login.php
    public static final String JSON_BASE_URL = "http://localhost/kalashseeds/";
    public static final String JSON_Sub_URL = "";
    public static final String Demo = "";


    public static Retrofit retrofit=null;
        public static Retrofit getRetrofit() {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(JSON_BASE_URL)
                    .client(new OkHttpClient.Builder().
                            connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60,TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit;
    }


}
