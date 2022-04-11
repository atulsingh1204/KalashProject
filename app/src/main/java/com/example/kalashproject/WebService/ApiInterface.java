package com.example.kalashproject.WebService;


import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    @FormUrlEncoded
    @POST("fetch_variety_on_crop_id.php")
    Call<ResponseBody> getNewvarientlist(@Field("crop_id") String crop_id);

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
                                       @Field("last_crop_taken") String last_crop_taken,
                                       @Field("irrigationid") String irrigationid,
                                       @Field("gradeid") String gradeid,
                                       @Field("varietyid") String varietyid,
                                       @Field("cropid") String cropid,
                                       @Field("crop_details") String crop_details,
                                       @Field("grower_or_vendor") String grower_or_vendor,
                                       @Field("previous_company") String previous_company,
                                       @Field("fdo_id") String fdo_id);



//    @Field("grower_or_vendor") String grower_or_vendor,



    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> getLogin(@Field("email") String emailid,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("pending_order_list.php")
    Call<ResponseBody> getPendingOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("approved_order_list.php")
    Call<ResponseBody> getApprovedOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("rejected_order_list.php")
    Call<ResponseBody>rejectedOrderList(@Field("id") String id);


    @FormUrlEncoded
    @POST("fetch_growervendor_list_on_grower_or_vendor.php")
    Call<ResponseBody>getVendorListByOption(@Field("growr_or_vendor") String growr_or_vendor);



    @GET("village_list.php")
    Call<ResponseBody> getVendorGrowerVillageList();


    @FormUrlEncoded
    @POST("village_list.php")
    Call<ResponseBody>village_list(@Field("fdo_id") String fdo_id);


    @FormUrlEncoded
    @POST("packet_invoice_add.php")
    Call<ResponseBody>sendQRCodeList(@Field("qr_list") String qr_list);

    @FormUrlEncoded
    @POST("fetch_village_of_fdo.php")
    Call<ResponseBody> fetch_village_of_fdo(@Field("fdo_id") String fdo_id);


    @FormUrlEncoded
    @POST("fetch_growervendor_on_gv_vill_id.php")
    Call<ResponseBody> fetch_growervendor_on_gv_vill_id(@Field("gv")String gv,
                                                        @Field("vlid")String vlid);


}


