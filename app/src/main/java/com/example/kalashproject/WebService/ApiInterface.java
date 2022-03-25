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


    @FormUrlEncoded
    @POST("fsd")
    Call<ResponseBody> AddVendorGrower(@Field("str_full_name") String str_full_name,
                                       @Field("str_contact") String str_contact,
                                       @Field("str_adhar") String str_adhar,
                                       @Field("str_email") String str_email,
                                       @Field("distance") String distance,
                                       @Field("total_land_holding") String total_land_holding,
                                       @Field("st_id") String st_id,
                                       @Field("dist_id") String dist_id,
                                       @Field("tal_id") String tal_id,
                                       @Field("vil_id") String vil_id,
                                       @Field("SpLastCropTaken") String SpLastCropTaken,
                                       @Field("sp_source_of_irrigation") String sp_source_of_irrigation,
                                       @Field("Sp_GradeofGrower") String Sp_GradeofGrower,
                                       @Field("Sp_Variety") String Sp_Variety,
                                       @Field("Sp_Crop") String Sp_Crop,
                                       @Field("Sp_growerorvendor") String Sp_growerorvendor);









}


