package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kalashproject.ModelList.DistrictList;
import com.example.kalashproject.ModelList.StateList;
import com.example.kalashproject.ModelList.TalukaList;
import com.example.kalashproject.ModelList.VillageList;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVendor extends AppCompatActivity
{

    Spinner SpState , SpDistrict, SpTaluka, SpVillage;
    Button btnSubmit;

    ArrayList<StateList> stateLists = new ArrayList<StateList>();
    ArrayList<DistrictList> districtLists = new ArrayList<DistrictList>();
    ArrayList<TalukaList> talukaLists = new ArrayList<TalukaList>();
    ArrayList<VillageList> VillageLists = new ArrayList<VillageList>();
    public String state_id = "1", district = "1", taluka = "1", village = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Vendor");

        SpState = findViewById(R.id.Spn_State);
        SpDistrict = findViewById(R.id.Spn_District);
        SpTaluka = findViewById(R.id.Spn_Taluka);
        SpVillage = findViewById(R.id.Spn_Village);
        btnSubmit = findViewById(R.id.tv_next);

        getStatedata("1");

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
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                // loadingDialog.dismissDialog();
                Log.e("getStateData", " " +response);
//
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
                        ArrayAdapter<StateList> dataAdapter = new ArrayAdapter<StateList>(AddVendor.this, android.R.layout.simple_spinner_item, stateLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpState.setAdapter(dataAdapter);

                        SpState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


                        ArrayAdapter<DistrictList> dataAdapter = new ArrayAdapter<DistrictList>(AddVendor.this, android.R.layout.simple_spinner_item, districtLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpDistrict.setAdapter(dataAdapter);

                        SpDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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

                        ArrayAdapter<TalukaList> dataAdapter = new ArrayAdapter<>(AddVendor.this, android.R.layout.simple_spinner_item, talukaLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpTaluka.setAdapter(dataAdapter);

                        SpTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        ArrayAdapter<VillageList> dataAdapter = new ArrayAdapter<>(AddVendor.this, android.R.layout.simple_spinner_item, VillageLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpVillage.setAdapter(dataAdapter);

                        SpVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
}