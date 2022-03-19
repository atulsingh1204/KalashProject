package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    Spinner  spnGrowerorvendor , spnCrop , spnVariety, spnGrade, spnSource, spnLstCrop, spnState, spnDistrict, spnTaluka, spnVillage;
    TextView tv_next;
    public String state_id = "1", district = "1", taluka = "1", village = "1";

    ArrayList<CropList> croplist = new ArrayList<CropList>();
    ArrayList<GradeofGrowerList> gradeofGrowerLists = new ArrayList<GradeofGrowerList>();
    ArrayList<GrowerorVendorList> growerorVendorLists = new ArrayList<GrowerorVendorList>();
    ArrayList<SourceofIrrigationList> sourceofIrrigationLists = new ArrayList<SourceofIrrigationList>();
    ArrayList<VarietyList> varietyLists = new ArrayList<VarietyList>();
    ArrayList<StateList> stateLists = new ArrayList<StateList>();
    ArrayList<DistrictList> districtLists = new ArrayList<DistrictList>();
    ArrayList<TalukaList> talukaLists = new ArrayList<TalukaList>();
    ArrayList<VillageList> VillageLists = new ArrayList<VillageList>();

    final LoadingDialog loadingDialog = new LoadingDialog(AddVendorGrower.this);
    EditText edt_farmer_name, edt_farmer_email, edt_farmer_contact, edt_farmer_adhar;

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

        edt_farmer_name = findViewById(R.id.edt_farmer_name);
        edt_farmer_email = findViewById(R.id.edt_farmer_email);
        edt_farmer_contact = findViewById(R.id.edt_farmer_contact);
        edt_farmer_adhar = findViewById(R.id.edt_farmer_adhar);

        getcroplist();
        getGradeofGrowerList();
        getGrowerorvendorList();
        getSourceofIrrigationList();
        getVarietyList();

        getStatedata("1");
    }

    private void getVarietyList()
    {
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getvarientlist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

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

                Log.e("Error", " " +t);
            }
        });
    }

    private void getSourceofIrrigationList()
    {
        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getSourceofirrigation();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                loadingDialog.dismissDialog();
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
            }
        });
    }

    private void getGrowerorvendorList()
    {
        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getgrowerorvendor();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                loadingDialog.dismissDialog();
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
            }
        });
    }

    private void getGradeofGrowerList()
    {
        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getgradeofgrower();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                loadingDialog.dismissDialog();
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
            }
        });

    }

    private void getcroplist()
    {
        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getcroplist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                loadingDialog.dismissDialog();
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
            }
        });
    }

    void getStatedata(String st_id){

        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result =(Call<ResponseBody>) apiInterface.getState("1");
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadingDialog.dismissDialog();
                Log.e("getStateData", " " +response);
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
            }
        });

    }



    private void getDistData()
    {
        loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getDistrict(state_id);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                loadingDialog.dismissDialog();
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

            }
        });
    }

    private void getTalukaData() {

        loadingDialog.startLoadingDialog();

        ApiInterface apiInterface =Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " +state_id+ "District_id " +district);
        final Call<ResponseBody> result =(Call<ResponseBody>)apiInterface.getTaluka(state_id, district);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                loadingDialog.dismissDialog();
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

                                Toast.makeText(AddVendorGrower.this, "Village Method called", Toast.LENGTH_SHORT).show();
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

            }
        });


    }

    private void getVillage() {


        ApiInterface apiInterface =Myconfig.getRetrofit().create(ApiInterface.class);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getVillage(state_id, district,taluka);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String output = "";


                try {
                    output = response.body().toString();
                    JSONObject jsonObject = new JSONObject(output);

                    Toast.makeText(AddVendorGrower.this, jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    if(jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray =jsonObject.getJSONArray("Data");
                        VillageLists.add(new VillageList("---Select Village---"));
                        for(int i = 0; i<jsonArray.length(); i++){

                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                VillageLists.add(new VillageList(object));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ArrayAdapter<VillageList> dataAdapter = new ArrayAdapter<VillageList>(AddVendorGrower.this, android.R.layout.simple_spinner_item,VillageLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnVillage.setAdapter(dataAdapter);

                        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                village =VillageLists.get(i).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


}