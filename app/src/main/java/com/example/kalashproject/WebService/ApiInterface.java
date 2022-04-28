package com.example.kalashproject.WebService;


import androidx.annotation.Nullable;

import com.google.android.gms.common.annotation.KeepForSdkWithFieldsAndMethods;

import java.security.SecureRandom;
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
    Call<ResponseBody>sendQRCodeList(@Field("qr_list") String qr_list,
                                     @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("fetch_village_of_fdo.php")
    Call<ResponseBody> fetch_village_of_fdo(@Field("fdo_id") String fdo_id);


    @FormUrlEncoded
    @POST("fetch_growervendor_on_gv_vill_id.php")
    Call<ResponseBody> fetch_growervendor_on_gv_vill_id(@Field("gv")String gv,
                                                        @Field("vlid")String vlid);



    @FormUrlEncoded
    @POST("qr_code_details.php")
    Call<ResponseBody> GetQRDetails(@Field("qr_data") String qr_data);



    @FormUrlEncoded
    @POST("first_inspection_add.php")
    Call<ResponseBody> first_inspection_add(@Field("male_sowing_date") String qr_male_sowing_date,
                                            @Field("female_sowing_date")String female_sowing_date,
                                            @Field("male_row")String male_row,
                                            @Field("female_row") String female_row,
                                            @Field("male_plant_in_row")String male_plant_in_row,
                                            @Field("female_plant_in_row")String female_plant_in_row,
                                            @Field("is_isolation")String is_isolation,
                                            @Field("pld_acre")String pld_acre,
                                            @Field("reason_of_pld")String reason_of_pld,
                                            @Field("rejected_acre")String rejected_acre,
                                            @Field("reason_of_rejected")String reason_of_rejected,
                                            @Field("expected_date_of_dispatch")String expected_date_of_dispatch,
                                            @Field("fq_flag_id")String fq_flag_id,
                                            @Field("breeder_remark")String breeder_remark);




    @GET("fq_flag_list.php")
    Call<ResponseBody>fq_flag_list();



//    @Field("order_id")String order_id,
//    @Field("fdo_id")String fod_id,


//    @Field("order_id")String order_id,
//    @Field("fdo_id")String fdo_id,


    @FormUrlEncoded
    @POST("second_inspection_add.php")
    Call<ResponseBody> second_inspection_add(@Field("order_id") String order_id,
                                             @Field("fdo_id") String fdo_id,
                                             @Field("gps_location") String gps_location,
                                             @Field("ot_plant_in_female")String ot_plant_in_female,
                                             @Field("female_date_of_roughing") String female_date_of_roughing,
                                             @Field("ot_plant_in_male") String ot_plant_in_male,
                                             @Field("male_date_of_roughing")String male_date_of_roughing,
                                             @Field("disease_plant_in_male")String disease_plant_in_male,
                                             @Field("details")String details,
                                             @Field("pld_acre")String pld_acre,
                                             @Field("reason_of_pld")String reason_of_pld,
                                             @Field("rejected_acre")String rejected_acre,
                                             @Field("reason_of_rejected")String reason_of_rejected,
                                             @Field("fq_flag_id")String fq_flag_id,
                                             @Field("expected_date_of_dispatch")String expected_date_of_dispatch,
                                             @Field("breeder_remark")String breeder_remark);






    @FormUrlEncoded
    @POST("third_inspection_add.php")
    Call<ResponseBody> third_inspection_add(
                                             @Field("order_id") String order_id,
                                            @Field("fdo_id") String fdo_id,
                                             @Field("ot_plant_in_female") String ot_plant_in_female,
                                            @Field("details")String details,
                                            @Field("disease_plant_in_f")String disease_plant_in_f,
                                            @Field("date_of_roughing") String date_of_roughing,
                                            @Field("polln_start_date") String polln_start_date,
                                            @Field("pld_acre") String pld_acre,
                                            @Field("reason_of_pld")String reason_of_pld,
                                            @Field("rejected_acre")String rejected_acre,
                                            @Field("reason_of_rejected")String reason_of_rejected,
                                            @Field("exi_fruit")String exi_fruit,
                                            @Field("exp_fruit")String exp_fruit,
                                            @Field("total_fruit")String total_fruit,
                                            @Field("avg_wt_of_seed_fruit")String avg_wt_of_seed_fruit,
                                            @Field("fq_flag_id")String fq_flag_id,
                                            @Field("expected_date_of_dispatch")String expected_date_of_dispatch,
                                            @Field("breeder_remark")String breeder_remark);




    @FormUrlEncoded
    @POST("fourth_inspection_add")
    Call<ResponseBody> fourth_inspection_add(@Field("order_id")String order_id,
                                             @Field("fdo_id")String fdo_id,
                                             @Field("ot_fruit_f")String ot_fruit_f,
                                             @Field("date_of_roughing")String date_of_roughing,
                                             @Field("polln_end_date")String polln_end_date,
                                             @Field("pld_acre")String pld_acre,
                                             @Field("reason_of_pld")String reason_of_pld,
                                             @Field("rejected_acre")String rejected_acre,
                                             @Field("reason_of_rejected")String reason_of_rejected,
                                             @Field("exi_fruit")String exi_fruit,
                                             @Field("exp_fruit")String exp_fruit,
                                             @Field("total_fruit")String total_fruit,
                                             @Field("avg_wt_of_seed_fruit")String avg_wt_of_seed_fruit,
                                             @Field("fq_flag_id")String fq_flag_id,
                                             @Field("expected_date_of_harvesting")String expected_date_of_harvesting,
                                             @Field("expected_date_of_dispatch")String expected_date_of_dispatch,
                                             @Field("breeder_remark")String breeder_remark);


}


