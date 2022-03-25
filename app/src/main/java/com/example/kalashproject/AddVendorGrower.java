package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.CropList;
import com.example.kalashproject.ModelList.DistrictList;
import com.example.kalashproject.ModelList.GradeofGrowerList;
import com.example.kalashproject.ModelList.GrowerorVendorList;
import com.example.kalashproject.ModelList.SourceofIrrigationList;
import com.example.kalashproject.ModelList.StateList;
import com.example.kalashproject.ModelList.TalukaList;
import com.example.kalashproject.ModelList.VarietyList;
import com.example.kalashproject.ModelList.VillageList;
import com.example.kalashproject.MyLibrary.MyValidator;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVendorGrower extends AppCompatActivity {

    Spinner  spnGrowerorvendor , spnCrop , spnVariety, spnGrade, spnSource, spnLstCrop, spnState, spnDistrict, spnTaluka, spnVillage, Spn_LastCropTaken,Spn_Variety,Spn_Crop,Spn_growerorvendor, Spn_SourceOfIrrigartion;
    TextView tv_next;
    public String state_id = "1", district = "1", taluka = "1", village = "1";
    Button btn_scanner;

    private String spstate,spdist,sptaluka,spvillage,SpLastCropTaken, Sp_GradeofGrower,Sp_Variety, Sp_Crop, Sp_growerorvendor, sp_source_of_irrigation;

    ArrayList<CropList> croplist = new ArrayList<CropList>();
    ArrayList<GradeofGrowerList> gradeofGrowerLists = new ArrayList<GradeofGrowerList>();
    ArrayList<GrowerorVendorList> growerorVendorLists = new ArrayList<GrowerorVendorList>();
    ArrayList<SourceofIrrigationList> sourceofIrrigationLists = new ArrayList<SourceofIrrigationList>();
    ArrayList<VarietyList> varietyLists = new ArrayList<VarietyList>();
    ArrayList<StateList> stateLists = new ArrayList<StateList>();
    ArrayList<DistrictList> districtLists = new ArrayList<DistrictList>();
    ArrayList<TalukaList> talukaLists = new ArrayList<TalukaList>();
    ArrayList<VillageList> VillageLists = new ArrayList<VillageList>();

     //LoadingDialog loadingDialog = new LoadingDialog(AddVendorGrower.this);

    private ProgressDialog pDialog;

    EditText edt_full_name, edt_email, edt_contact, edt_adhar;
    EditText distance_from_center, total_land_holding;

    String str_full_name, str_email, str_contact, str_adhar;

    String str_distance_from_center, str_total_land_holding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor_grower);

        tv_next = findViewById(R.id.tv_next);
     //   camera = new Camera(AddNewFarmerActivity.this);
      //  iv_photo1 = findViewById(R.id.iv_photo1);
        spnGrowerorvendor=findViewById(R.id.Spn_growerorvendor);
        spnCrop=findViewById(R.id.Spn_Crop);
        spnVariety=findViewById(R.id.Spn_Variety);
        spnGrade=findViewById(R.id.Spn_GradeofGrower);
        spnSource=findViewById(R.id.Spn_SourceOfIrrigartion);
        spnLstCrop=findViewById(R.id.Spn_LastCropTaken);
        spnState=findViewById(R.id.Spn_State);
        spnDistrict=findViewById(R.id.Spn_District);
        spnTaluka=findViewById(R.id.Spn_Taluka);
        spnVillage=findViewById(R.id.Spn_Village);
        Spn_LastCropTaken= findViewById(R.id.Spn_LastCropTaken);
        Spn_Variety = findViewById(R.id.Spn_Variety);
        Spn_Crop = findViewById(R.id.Spn_Crop);
        Spn_growerorvendor = findViewById(R.id.Spn_growerorvendor);
        Spn_SourceOfIrrigartion = findViewById(R.id.Spn_SourceOfIrrigartion);

        distance_from_center = findViewById(R.id.distance_from_center);
        total_land_holding = findViewById(R.id.total_land_holding);

        edt_full_name = findViewById(R.id.edt_full_name);
        edt_email = findViewById(R.id.edt_email);
        edt_contact = findViewById(R.id.edt_contact);
        edt_adhar = findViewById(R.id.edt_adhar);

        btn_scanner = findViewById(R.id.btn_scanner);

        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddVendorGrower.this, QRCodeScanner.class));
            }
        });

        getcroplist();
        getGradeofGrowerList();
        getGrowerorvendorList();
        getSourceofIrrigationList();
        getVarietyList();

        getStatedata("1");


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spstate = spnState.getSelectedItem().toString();
                spdist = spnDistrict.getSelectedItem().toString();
                sptaluka = spnTaluka.getSelectedItem().toString();
                spvillage = spnVillage.getSelectedItem().toString();

                SpLastCropTaken = Spn_LastCropTaken.getSelectedItem().toString();
                sp_source_of_irrigation = Spn_SourceOfIrrigartion.getSelectedItem().toString();
                Sp_GradeofGrower = Spn_growerorvendor.getSelectedItem().toString();
                Sp_Variety = spnVariety.getSelectedItem().toString();
                Sp_Crop = Spn_Crop.getSelectedItem().toString();
                Sp_growerorvendor = spnGrowerorvendor.getSelectedItem().toString();

                btn_scanner = findViewById(R.id.btn_scanner);

//
//                if(spstate.equals("---Select State---") || spstate.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select state", Toast.LENGTH_SHORT).show();
//                }
//                else if(spdist.equals("---Select State---") || spdist.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select district", Toast.LENGTH_SHORT).show();
//                }
//                else if(sptaluka.equals("---Select State---") || sptaluka.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select Taluka", Toast.LENGTH_SHORT).show();
//                }
//                else if(spvillage.equals("---Select State---") || spvillage.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select Village", Toast.LENGTH_SHORT).show();
//                }
//                else if(SpLastCropTaken.equals("---Select State---") || SpLastCropTaken.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select last taken crop", Toast.LENGTH_SHORT).show();
//                }
//                else if(sp_source_of_irrigation.equals("---Select State---") || sp_source_of_irrigation.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select source of irrigation", Toast.LENGTH_SHORT).show();
//                }
//                else if(Sp_GradeofGrower.equals("---Select State---") || Sp_GradeofGrower.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select grade of grower", Toast.LENGTH_SHORT).show();
//                }
//                else if(Sp_Variety.equals("---Select State---") || Sp_Variety.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select variety", Toast.LENGTH_SHORT).show();
//                }
//                else if(Sp_Crop.equals("---Select State---") || Sp_Crop.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select crop", Toast.LENGTH_SHORT).show();
//                }
//                else if(Sp_growerorvendor.equals("---Select State---") || Sp_growerorvendor.equals("--Select--")){
//                    Toast.makeText(AddVendorGrower.this, "Please select grower or vendor", Toast.LENGTH_SHORT).show();
//                }


                sendData();


            }
        });
    }



    private void getVarietyList()
    {
//      pDialog = new ProgressDialog(AddVendorGrower.this);
//      pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

       // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getvarientlist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//              if (pDialog.isShowing()){
//                  pDialog.dismiss();
//              }
               // loadingDialog.dismissDialog();

                Log.e("varietyList", " " +response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        varietyLists.add(new VarietyList(" Select Variety "));
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            varietyLists.add(new VarietyList(object));
                        }
                        ArrayAdapter<VarietyList> varietyAdapter = new ArrayAdapter<VarietyList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, varietyLists);
                        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnVariety.setAdapter(varietyAdapter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

               // loadingDialog.dismissDialog();
                Log.e("Error", " " +t);
                if (pDialog.isShowing()){
                    pDialog.dismiss();
                }
            }
        });
    }

    private void getSourceofIrrigationList()
    {
      //  loadingDialog.startLoadingDialog();
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getSourceofirrigation();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

             //   loadingDialog.dismissDialog();
                Log.e("SourceOfIrrigation", " " +response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        sourceofIrrigationLists.add(new SourceofIrrigationList(" Select Source of irrigation "));
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            sourceofIrrigationLists.add(new SourceofIrrigationList(object));
                        }
                        ArrayAdapter<SourceofIrrigationList> sourceofIrrigationListArrayAdapter = new ArrayAdapter<SourceofIrrigationList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, sourceofIrrigationLists);
                        sourceofIrrigationListArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnSource.setAdapter(sourceofIrrigationListArrayAdapter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("Error", " " +t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    private void getGrowerorvendorList()
    {
        //loadingDialog.startLoadingDialog();

//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getgrowerorvendor();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

               // loadingDialog.dismissDialog();
                Log.e("GrowerVendor", " " +response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        growerorVendorLists.add(new GrowerorVendorList(" Select Grower or Vendor "));
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            growerorVendorLists.add(new GrowerorVendorList(object));
                        }
                        ArrayAdapter<GrowerorVendorList> growerorvendorAdapter = new ArrayAdapter<GrowerorVendorList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, growerorVendorLists);
                        growerorvendorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnGrowerorvendor.setAdapter(growerorvendorAdapter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("Error", " " +t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    private void getGradeofGrowerList()
    {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

       // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getgradeofgrower();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

               // loadingDialog.dismissDialog();
                Log.e("GradeOfGrower", " " +response);

                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1"))
                    {
                        Toast.makeText(AddVendorGrower.this, "Run", Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        gradeofGrowerLists.add(new GradeofGrowerList("--- Select Grade ---"));
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            gradeofGrowerLists.add(new GradeofGrowerList(object));
                        }
                        ArrayAdapter<GradeofGrowerList> gradeAdapter = new ArrayAdapter<GradeofGrowerList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, gradeofGrowerLists);
                        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnGrade.setAdapter(gradeAdapter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("Error", " " +t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });

    }

    private void getcroplist()
    {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

       // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getcroplist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
               // loadingDialog.dismissDialog();
                Log.e("getCropList", " " +response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        croplist.add(new CropList("--- Select crop ---"));
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            croplist.add(new CropList(object));
                        }
                        ArrayAdapter<CropList> cropAdapter = new ArrayAdapter<CropList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, croplist);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnCrop.setAdapter(cropAdapter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("Error", " " +t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    void getStatedata(String st_id)
    {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


       // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result =(Call<ResponseBody>) apiInterface.getState("1");
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               // loadingDialog.dismissDialog();
                Log.e("getStateData", " " +response);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

             String output = "";
                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if(jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        stateLists.add(new StateList("---Select State---"));

                        for(int i=0; i<jsonArray.length(); i++){


                            JSONObject object = null;
                            try {
                                object = jsonArray.getJSONObject(i);
                                stateLists.add(new StateList(object));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<StateList> dataAdapter = new ArrayAdapter<StateList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, stateLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnState.setAdapter(dataAdapter);

                      spnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                          {

                              String item =adapterView.getItemAtPosition(i).toString();
                                state_id = stateLists.get(i).getId();
                                getDistData();
                                Log.e("district","state"+state_id);
                          }

                          @Override
                          public void onNothingSelected(AdapterView<?> adapterView) {

                          }
                      });

                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " +t);
               // loadingDialog.dismissDialog();

//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });

    }



    private void getDistData()
    {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

//        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getDistrict(state_id);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

//                loadingDialog.dismissDialog();
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                String output = "";

                try {
                    output = response.body().string();
                    Log.d("org", "district: " + output);
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        districtLists.clear();
                        districtLists.add(new DistrictList("---Select District---"));

                        for (int i= 0; i<jsonArray.length(); i++){
                            try {

                                 JSONObject obj = jsonArray.getJSONObject(i);
                                districtLists.add(new DistrictList(obj));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        ArrayAdapter<DistrictList> dataAdapter = new ArrayAdapter<DistrictList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, districtLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnDistrict.setAdapter(dataAdapter);

                        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //  On selecting a spinner item

                                String item = adapterView.getItemAtPosition(i).toString();
                                district = districtLists.get(i).getId();
                                Log.e("district","Dist"+district);
                                getTalukaData();
                            }

                            public void onNothingSelected(AdapterView<?> adapterView) {
                                return;
                            }
                        });


                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                loadingDialog.dismissDialog();
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    private void getTalukaData()
    {

//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        ApiInterface apiInterface =Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " +state_id+ "District_id " +district);
        final Call<ResponseBody> result =(Call<ResponseBody>)apiInterface.getTaluka(state_id, district);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                Log.e("talkula_error", "" +response.toString());
                String output = "";

                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if(jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        talukaLists.clear();
                       talukaLists.add(new TalukaList("---Select Taluka---"));

                       for(int i = 0; i<jsonArray.length(); i++){

                           try {
                               JSONObject object = jsonArray.getJSONObject(i);
                               talukaLists.add(new TalukaList(object));
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }

                        ArrayAdapter<TalukaList> dataAdapter = new ArrayAdapter<>(AddVendorGrower.this, android.R.layout.simple_spinner_item, talukaLists);
                       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       spnTaluka.setAdapter(dataAdapter);

                        spnTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                taluka =talukaLists.get(i).getId();

                                getVillage();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });


    }

    private void getVillage()
    {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


        ApiInterface apiInterface =Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " +state_id+ "District_id " +district);
        final Call<ResponseBody> result =(Call<ResponseBody>)apiInterface.getVillage(state_id, district, village);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                Log.e("Village_Error", "" +response.toString());
                String output = "";

                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if(jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        VillageLists.clear();
                        VillageLists.add(new VillageList("--Select village---"));

                        for(int i = 0; i<jsonArray.length(); i++){

                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                VillageLists.add(new VillageList(object));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ArrayAdapter<VillageList> dataAdapter = new ArrayAdapter<>(AddVendorGrower.this, android.R.layout.simple_spinner_item, VillageLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnVillage.setAdapter(dataAdapter);

                        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                village =VillageLists.get(i).getId();

                                //Toast.makeText(AddVendorGrower.this, "Village Method called", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

            }
        });

    }

//    private boolean validatefield(){
//        boolean result =true;
//
//
//    }

//    public static boolean isvalidfield(){
//
//        if(!MyValidator.isValidField()){
//
//        }
//    }


    private void sendData() {

        str_full_name = edt_full_name.getText().toString().trim();
        str_contact = edt_contact.getText().toString().trim();
        str_adhar = edt_adhar.getText().toString().trim();
        str_email = edt_email.getText().toString().trim();
        str_distance_from_center = distance_from_center.getText().toString();
        str_total_land_holding = total_land_holding.getText().toString();

        Log.e("sendData", "full name" +str_full_name);
        Log.e("sendData", "contact " +str_contact);
        Log.e("sendData", "aadhar " +str_adhar);
        Log.e("sendData", "email " +str_email);
        Log.e("sendData", "state " +state_id);
        Log.e("sendData", " district" +district);
        Log.e("sendData", "taluka " +taluka);
        Log.e("sendData", "village " +village);
        Log.e("sendData", "distance from center " +distance_from_center);
        Log.e("sendData", "total land holding " +total_land_holding);



        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result =(Call<ResponseBody>) apiInterface.AddVendorGrower(str_full_name,
                str_contact,
                str_adhar,
                str_email,
                str_distance_from_center,
                str_total_land_holding,
                state_id,
                district,
                taluka,
                village,
                SpLastCropTaken,
                sp_source_of_irrigation,
                Sp_GradeofGrower,
                Sp_Variety,
                Sp_Crop,
                Sp_growerorvendor);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(AddVendorGrower.this, "Submited", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });


    }

}