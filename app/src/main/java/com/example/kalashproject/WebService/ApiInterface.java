package com.example.kalashproject.WebService;


import androidx.annotation.Nullable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface
{
    @GET("growervender_list.php")
    Call<ResponseBody>getgrowerorvendor();

    @GET("crops_list.php")
    Call<ResponseBody>getcroplist();

    @GET("variety_list.php")
    Call<ResponseBody>getvarientlist();

    @GET("grade_of_growers_list.php")
    Call<ResponseBody>getgradeofgrower();

    @GET("source_of_irrigation_list.php")
    Call<ResponseBody>getSourceofirrigation();

    @FormUrlEncoded
    @POST("state_list.php")
    Call<ResponseBody> getState(@Field("fld_country_id") String fld_country_id);

    @FormUrlEncoded
    @POST("fetch_district_on_state_id.php")
    Call<ResponseBody> getDistrict(@Field("state_id") String state_id);

    @FormUrlEncoded
    @POST("fetch_taluka_on_state_district_id.php")
    Call<ResponseBody> getTaluka(@Field("state_id") String state_id,
                                 @Field("district_id") String distric_id);
    @FormUrlEncoded
    @POST("fetch_village_on_state_district_taluka_id.php")
    Call<ResponseBody> getVillage(@Field("state_id") String state_id,
                                  @Field("district_id") String district_id,
                                  @Field("taluka_id") String taluka_id);









}


