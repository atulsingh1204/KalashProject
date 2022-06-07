package com.example.kalashproject.WebService;


import android.util.Log;

import androidx.annotation.Nullable;

import com.example.kalashproject.ModelList.ApiModel;
import com.google.errorprone.annotations.FormatMethod;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.SAXResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


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



//    @FormUrlEncoded
//    @POST("first_inspection_add.php")
//    Call<ResponseBody> first_inspection_add(@Field("order_id") String order_id,
//                                            @Field("fdo_id") String fdo_id,
//                                            @Field("male_sowing_date") String qr_male_sowing_date,
//                                            @Field("female_sowing_date")String female_sowing_date,
//                                            @Field("male_row")String male_row,
//                                            @Field("female_row") String female_row,
//                                            @Field("male_plant_in_row")String male_plant_in_row,
//                                            @Field("female_plant_in_row")String female_plant_in_row,
//                                            @Field("is_isolation")String is_isolation,
//                                            @Field("pld_acre")String pld_acre,
//                                            @Field("reason_of_pld")String reason_of_pld,
//                                            @Field("rejected_acre")String rejected_acre,
//                                            @Field("reason_of_rejected")String reason_of_rejected,
//                                            @Field("expected_date_of_dispatch")String expected_date_of_dispatch,
//                                            @Field("fq_flag_id")String fq_flag_id,
//                                            @Field("breeder_remark")String breeder_remark);






    @Multipart
    @POST("first_inspection_add.php")
    Call<ResponseBody> first_inspection_add(@Part("order_id")@Nullable RequestBody order_id,
                                            @Part("fdo_id") @Nullable RequestBody fdo_id,
                                            @Part("fq_flag_id") @Nullable RequestBody fq_flag_id,
                                            @Part("breeder_remark") @Nullable RequestBody breeder_remark,
                                            @Part("male_sowing_date") @Nullable RequestBody male_sowing_date,
                                            @Part("female_sowing_date") @Nullable RequestBody female_sowing_date,
                                            @Part("male_row") @Nullable RequestBody male_row,
                                            @Part("female_row") @Nullable RequestBody female_row,
                                            @Part("male_plant_in_row") @Nullable RequestBody male_plant_in_row,
                                            @Part("female_plant_in_row") @Nullable RequestBody female_plant_in_row,
                                            @Part("is_isolation") @Nullable RequestBody is_isolation,
                                            @Part("pld_acre") @Nullable RequestBody pld_acre,
                                            @Part("reason_of_pld") @Nullable RequestBody reason_of_pld,
                                            @Part("rejected_acre") @Nullable RequestBody rejected_acre,
                                            @Part("reason_of_rejected") @Nullable RequestBody reason_of_rejected,
                                            @Part("expected_date_of_dispatch") @Nullable RequestBody expected_date_of_dispatch,
                                            @Part List<MultipartBody.Part> files);




    @GET("fq_flag_list.php")
    Call<ResponseBody>fq_flag_list();



//    @Field("order_id")String order_id,
//    @Field("fdo_id")String fod_id,


//    @Field("order_id")String order_id,
//    @Field("fdo_id")String fdo_id,


    @Multipart
    @POST("second_inspection_add.php")
    Call<ResponseBody> second_inspection_add(@Part("order_id") @Nullable RequestBody order_id,
                                             @Part("fdo_id") @Nullable RequestBody fdo_id,
                                             @Part("gps_location")@Nullable RequestBody gps_location,
                                             @Part("ot_plant_in_female")@Nullable RequestBody ot_plant_in_female,
                                             @Part("female_date_of_roughing")@Nullable RequestBody female_date_of_roughing,
                                             @Part("ot_plant_in_male")@Nullable RequestBody ot_plant_in_male,
                                             @Part("male_date_of_roughing")@Nullable RequestBody male_date_of_roughing,
                                             @Part("disease_plant_in_male")@Nullable RequestBody disease_plant_in_male,
                                             @Part("details")@Nullable RequestBody details,
                                             @Part("pld_acre")@Nullable RequestBody pld_acre,
                                             @Part("reason_of_pld")@Nullable RequestBody reason_of_pld,
                                             @Part("rejected_acre")@Nullable RequestBody rejected_acre,
                                             @Part("reason_of_rejected")@Nullable RequestBody reason_of_rejected,
                                             @Part("fq_flag_id")@Nullable RequestBody fq_flag_id,
                                             @Part("expected_date_of_dispatch")@Nullable RequestBody expected_date_of_dispatch,
                                             @Part("breeder_remark")@Nullable RequestBody breeder_remark,
                                             @Part List<MultipartBody.Part> files,
                                             @Part List<MultipartBody.Part> filesTwo);






    @Multipart
    @POST("third_inspection_add.php")
    Call<ResponseBody> third_inspection_add(
                                             @Part("order_id")@Nullable RequestBody order_id,
                                            @Part("fdo_id")@Nullable RequestBody fdo_id,
                                             @Part("ot_plant_in_female")@Nullable RequestBody ot_plant_in_female,
                                            @Part("details")@Nullable RequestBody details,
                                            @Part("disease_plant_in_f")@Nullable RequestBody disease_plant_in_f,
                                            @Part("date_of_roughing")@Nullable RequestBody date_of_roughing,
                                            @Part("polln_start_date")@Nullable RequestBody polln_start_date,
                                            @Part("pld_acre")@Nullable RequestBody pld_acre,
                                            @Part("reason_of_pld")@Nullable RequestBody reason_of_pld,
                                            @Part("rejected_acre")@Nullable RequestBody rejected_acre,
                                            @Part("reason_of_rejected")@Nullable RequestBody reason_of_rejected,
                                            @Part("exi_fruit")@Nullable RequestBody exi_fruit,
                                            @Part("exp_fruit")@Nullable RequestBody exp_fruit,
                                            @Part("total_fruit")@Nullable RequestBody total_fruit,
                                            @Part("avg_wt_of_seed_fruit")@Nullable RequestBody avg_wt_of_seed_fruit,
                                            @Part("fq_flag_id")@Nullable RequestBody fq_flag_id,
                                            @Part("expected_date_of_dispatch")@Nullable RequestBody expected_date_of_dispatch,
                                            @Part("breeder_remark")@Nullable RequestBody breeder_remark,
                                             @Part List<MultipartBody.Part> files,
                                             @Part List<MultipartBody.Part> filesTwo);




    @Multipart
    @POST("fourth_inspection_add.php")
    Call<ResponseBody> fourth_inspection_add(@Part("order_id")@Nullable RequestBody order_id,
                                             @Part("fdo_id")@Nullable RequestBody fdo_id,
                                             @Part("ot_fruit_f")@Nullable RequestBody ot_fruit_f,
                                             @Part("date_of_roughing")@Nullable RequestBody date_of_roughing,
                                             @Part("polln_end_date")@Nullable RequestBody polln_end_date,
                                             @Part("pld_acre")@Nullable RequestBody pld_acre,
                                             @Part("reason_of_pld")@Nullable RequestBody reason_of_pld,
                                             @Part("rejected_acre")@Nullable RequestBody rejected_acre,
                                             @Part("reason_of_rejected")@Nullable RequestBody reason_of_rejected,
                                             @Part("exi_fruit")@Nullable RequestBody exi_fruit,
                                             @Part("exp_fruit")@Nullable RequestBody exp_fruit,
                                             @Part("total_fruit")@Nullable RequestBody total_fruit,
                                             @Part("avg_wt_of_seed_fruit")@Nullable RequestBody avg_wt_of_seed_fruit,
                                             @Part("fq_flag_id")@Nullable RequestBody fq_flag_id,
                                             @Part("expected_date_of_harvesting")@Nullable RequestBody expected_date_of_harvesting,
                                             @Part("expected_date_of_dispatch")@Nullable RequestBody expected_date_of_dispatch,
                                             @Part("breeder_remark")@Nullable RequestBody breeder_remark,
                                             @Part List<MultipartBody.Part> files);




    @FormUrlEncoded
    @POST("grpo_add.php")
    Call<ResponseBody> grpo_add(@Field("order_id")String order_id,
                                @Field("total_quantity")String total_quantity,
                                @Field("first_bag_size")String first_bag_size,
                                @Field("first_number_of_bags")String first_number_of_bags,
                                @Field("second_bag_size")String second_bag_size,
                                @Field("second_number_of_bags")String second_number_of_bags,
                                @Field("third_bag_size")String third_bag_size,
                                @Field("third_number_of_bags")String third_number_of_bags,
                                @Field("fourth_bag_size")String fourth_bag_size,
                                @Field("fourth_number_of_bags")String fourth_number_of_bags,
                                @Field("fifth_bag_size")String fifth_bag_size,
                                @Field("fifth_number_of_bags")String fifth_number_of_bags,
                                @Field("moisture")String moisture,
                                @Field("want_to_close_document")String want_to_close_document,
                                @Field("pending_quantity")String pending_quantity);

    @FormUrlEncoded
    @POST("first_inspections_list.php")
    Call<ResponseBody> InspectionOneList(@Field("fdo_id")String fdo_id);


    @FormUrlEncoded
    @POST("second_inspections_list.php")
    Call<ResponseBody> InspectionTwoList(@Field("fdo_id")String fdo_id);

//    @Multipart
//    @POST("first_inspection_add.php")
//    Call<ResponseBody> uploadImages (@Part MultipartBody.Part file1, @Part MultipartBody.Part file2);

    @Multipart
    @POST("first_inspection_add.php")
    Call<ResponseBody> uploadImages(@Part ArrayList<MultipartBody.Part> document_name);



//    @Multipart
//    @POST(Constant.ENDPOINT+"NewsFeed/uploadImage")
//    Call<ApiModel> uploadNewsFeedImages(@Part List<MultipartBody.Part> files);

//    @Multipart
//    @POST("first_inspection_add.php")
//    Call<ResponseBody> upload(@Part List<MultipartBody.Part> files);

/////////// Testing start //////////////
    @Multipart
    @POST("first_inspection_add.php")
    Call<ResponseBody> upload(@Part List<MultipartBody.Part> files);

//    @Multipart
//    @POST("try.php")
//    Call<ResponseBody> upload(@Part List<MultipartBody.Part> files);


    /////////// Testing End //////////////
    @Multipart
    @POST("first_inspection_add.php")
    Call<ResponseBody> SimpleImagesList(@Part MultipartBody.Part files);


    @Multipart
    @POST("grower_or_vendor_add.php")
    Call<ResponseBody> sendAddVendorDetails(@Part("full_name") @Nullable RequestBody full_name,
                                            @Part("mobile_number") @Nullable RequestBody mobile_number,
                                            @Part("aadhar_number") @Nullable RequestBody aadhar_number,
                                            @Part("address") @Nullable RequestBody address,
                                            @Part("state") @Nullable RequestBody state,
                                            @Part("district") @Nullable RequestBody district,
                                            @Part("taluka") @Nullable RequestBody taluka,
                                            @Part("village") @Nullable RequestBody village,
                                            @Part("generate_fc") @Nullable RequestBody generate_fc,
                                            @Part("fdo_id") @Nullable RequestBody fdo_id,
                                            @Part MultipartBody.Part file1,
                                            @Part MultipartBody.Part file2,
                                            @Part MultipartBody.Part file3,
                                            @Part("family_code") @Nullable RequestBody family_code);


    @GET("family_code_list.php")
    Call<ResponseBody> getFamilyCodeList();




    @FormUrlEncoded
    @POST("third_inspections_list.php")
    Call<ResponseBody> InspectionThreeList(@Field("fdo_id")String fdo_id);



    @FormUrlEncoded
    @POST("first_inspection_pending_list.php")
    Call<ResponseBody> AllInspectionList(@Field("fdo_id") String fdo_id);

    @FormUrlEncoded
    @POST("second_inspection_pending_list.php")
    Call<ResponseBody> SecondPendingList(@Field("fdo_id") String fdo_id);

    @FormUrlEncoded
    @POST("third_inspection_pending_list.php")
    Call<ResponseBody> ThirdPendingList(@Field("fdo_id")String fdo_id);

    @FormUrlEncoded
    @POST("fourth_inspection_pending_list.php")
    Call<ResponseBody> FourthPendingList(@Field("fdo_id")String fdo_id);

    @FormUrlEncoded
    @POST("grpo_inspection_pending_list.php")
    Call<ResponseBody> GRPOPendingList(@Field("fdo_id") String fdo_id);

}


