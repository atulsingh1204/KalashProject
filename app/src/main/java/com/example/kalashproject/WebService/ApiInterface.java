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

    @GET("crops_list.php")
    Call<ResponseBody>getLastCropTaken();

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
    @POST("order_add.php")
    Call<ResponseBody> AddVendorGrower(@Field("growervendorid") String growervendorid,
                                       @Field("distance") String distance,
                                       @Field("land_holding") String land_holding,
                                       @Field("st_id") String st_id,
                                       @Field("dist_id") String dist_id,
                                       @Field("tal_id") String tal_id,
                                       @Field("vil_id") String vil_id,
                                       @Field("last_crop_taken") String last_crop_taken,
                                       @Field("irrigationid") String irrigationid,
                                       @Field("gradeid") String gradeid,
                                       @Field("varietyid") String varietyid,
                                       @Field("cropid") String cropid,
                                       @Field("crop_details") String crop_details,
                                       @Field("grower_or_vendor") String grower_or_vendor,
                                       @Field("previous_company") String previous_company);



//    @Field("grower_or_vendor") String grower_or_vendor,



    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> getLogin(@Field("emailid") String emailid,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("pending_order_list.php")
    Call<ResponseBody> getPendingOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("pending_order_list.php")
    Call<ResponseBody> getApprovedOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("rejected_order_list.php")
    Call<ResponseBody>rejectedOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("fetch_growervendor_list_on_grower_or_vendor.php")
    Call<ResponseBody>getVendorListByOption(@Field("growr_or_vendor") String growr_or_vendor);

}


