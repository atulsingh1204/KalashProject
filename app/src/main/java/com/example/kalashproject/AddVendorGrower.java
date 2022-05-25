package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.animation.Animator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telecom.CallScreeningService;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.CropList;
import com.example.kalashproject.ModelList.DistrictList;
import com.example.kalashproject.ModelList.GradeofGrowerList;
import com.example.kalashproject.ModelList.GrowerorVendorList;
import com.example.kalashproject.ModelList.NewVillageList;
import com.example.kalashproject.ModelList.SourceofIrrigationList;
import com.example.kalashproject.ModelList.StateList;
import com.example.kalashproject.ModelList.TalukaList;
import com.example.kalashproject.ModelList.VarietyList;
import com.example.kalashproject.ModelList.VillageList;

import com.example.kalashproject.MyLibrary.MyValidator;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.StartActivities.MainActivity;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVendorGrower extends AppCompatActivity {

    Spinner spnGrowerorvendor, spnCrop, spnVariety, spnGrade, spnSource, spnLstCrop, spnState, spnDistrict, spnTaluka, spnVillage, Spn_LastCropTaken, Spn_Variety, Spn_Crop, Spn_growerorvendor, Spn_SourceOfIrrigartion, Spn_select_growerorvendor, Spn_Village;
    TextView tv_next;
    public String state_id = "1", district = "1", taluka = "1", village = "", GrowerVendorId = "", CropId = "", Varietyid = "", SourceOfIrrigationId = "", GradeId = "", LastCropTakenId = "", str_Grower_vendor_id = "";
    Button btn_scanner;

    Dialog dialog;
    String GrowerOrvendorName = "";
    TextView text_view;

    CircleImageView fab, fab1;
    LinearLayout fabLayout1;
    boolean isFABOpen = false;
    FrameLayout linLayHeader;
    LinearLayout linearLayout_order_two;

    public String spstate, spdist, sptaluka, spvillage, SpLastCropTaken, Sp_GradeofGrower, Sp_Variety, Sp_Crop, Sp_growerorvendor, sp_source_of_irrigation, Spnselect_growerorvendor, str_Village, str_gradeOfGrower;


    ArrayList<CropList> croplist = new ArrayList<CropList>();
    ArrayList<GradeofGrowerList> gradeofGrowerLists = new ArrayList<GradeofGrowerList>();
    ArrayList<GrowerorVendorList> growerorVendorLists = new ArrayList<GrowerorVendorList>();
    ArrayList<SourceofIrrigationList> sourceofIrrigationLists = new ArrayList<SourceofIrrigationList>();
    ArrayList<VarietyList> varietyLists = new ArrayList<VarietyList>();
    ArrayList<StateList> stateLists = new ArrayList<StateList>();
    ArrayList<DistrictList> districtLists = new ArrayList<DistrictList>();
    ArrayList<TalukaList> talukaLists = new ArrayList<TalukaList>();
    ArrayList<VillageList> VillageLists = new ArrayList<VillageList>();
    ArrayList<CropList> lastTakenCropLists = new ArrayList<CropList>();


    ////////////////////////////////////////// Testing start
    ArrayList<NewVillageList> newVillageLists = new ArrayList<NewVillageList>();


    /////////////////////////////// Testing End


    //LoadingDialog loadingDialog = new LoadingDialog(AddVendorGrower.this);

    private ProgressDialog pDialog;

    EditText edt_full_name, edt_email, edt_contact, edt_adhar;
    EditText distance_from_center, total_land_holding, crop_details, previous_company, last_crop_taken;

    String str_full_name, str_email, str_contact, str_adhar;

    String str_distance_from_center, str_total_land_holding, str_crop_details, str_previous_company, str_last_crop_taken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor_grower);
        fab = (CircleImageView) findViewById(R.id.fab);
        fab1 = (CircleImageView) findViewById(R.id.fab1);
        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);

        ///////////////////////// Testing Start

        newVillageLists.add(new NewVillageList("1", "Ghoti"));
        newVillageLists.add(new NewVillageList("2", "Gonde"));
        newVillageLists.add(new NewVillageList("3", "vilhodi"));


        //////////////////////// Testing End


        //spinner with search
        text_view = findViewById(R.id.text_view);

        // toolbar

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tentative Booking");

        FrameLayout linLayHeader;

        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);
        linearLayout_order_two = findViewById(R.id.linearLayout_order_two);
        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);

        tv_next = findViewById(R.id.tv_next);
        //   camera = new Camera(AddNewFarmerActivity.this);
        //  iv_photo1 = findViewById(R.id.iv_photo1);
        spnGrowerorvendor = findViewById(R.id.Spn_growerorvendor);
        spnCrop = findViewById(R.id.Spn_Crop);
        spnVariety = findViewById(R.id.Spn_Variety);
        spnGrade = findViewById(R.id.Spn_GradeofGrower);
        spnSource = findViewById(R.id.Spn_SourceOfIrrigartion);
        // spnLstCrop = findViewById(R.id.Spn_LastCropTaken);
        spnState = findViewById(R.id.Spn_State);
        spnDistrict = findViewById(R.id.Spn_District);
        spnTaluka = findViewById(R.id.Spn_Taluka);
        spnVillage = findViewById(R.id.Spn_Village);
        //Spn_LastCropTaken= findViewById(R.id.Spn_LastCropTaken);
        Spn_Variety = findViewById(R.id.Spn_Variety);
        Spn_Crop = findViewById(R.id.Spn_Crop);
        Spn_growerorvendor = findViewById(R.id.Spn_growerorvendor);
        Spn_SourceOfIrrigartion = findViewById(R.id.Spn_SourceOfIrrigartion);
        Spn_select_growerorvendor = findViewById(R.id.Spn_select_growerorvendor);
        Spn_Village = findViewById(R.id.Spn_Village);


        distance_from_center = findViewById(R.id.distance_from_center);
        total_land_holding = findViewById(R.id.total_land_holding);

        crop_details = findViewById(R.id.crop_details);

        edt_full_name = findViewById(R.id.edt_full_name);
        edt_email = findViewById(R.id.edt_email);
        edt_contact = findViewById(R.id.edt_contact);
        edt_adhar = findViewById(R.id.edt_adhar);
        previous_company = findViewById(R.id.previous_company);
        last_crop_taken = findViewById(R.id.last_crop_taken);

        btn_scanner = findViewById(R.id.btn_scanner);

        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddVendorGrower.this, QRCodeScanner.class));
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    linLayHeader.setBackgroundColor(getResources().getColor(R.color.trans_grey_color));
                    showFABMenu();
                } else {
                    linLayHeader.setBackgroundColor(getResources().getColor(R.color.white2));
                    closeFABMenu();
                }
            }
        });


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddVendorGrower.this, AddVendor.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                if (!isFABOpen) {
                } else {
                    closeFABMenu();
                }
            }
        });


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                spstate = spnState.getSelectedItem().toString();
                spdist = spnDistrict.getSelectedItem().toString();
                sptaluka = spnTaluka.getSelectedItem().toString();
                spvillage = spnVillage.getSelectedItem().toString();
                Log.e("response", "spvillage: " + spvillage);

                // SpLastCropTaken = Spn_LastCropTaken.getSelectedItem().toString();
                sp_source_of_irrigation = Spn_SourceOfIrrigartion.getSelectedItem().toString();
                Sp_GradeofGrower = Spn_growerorvendor.getSelectedItem().toString();
                Sp_Variety = spnVariety.getSelectedItem().toString();
                Sp_Crop = Spn_Crop.getSelectedItem().toString();
                Sp_growerorvendor = spnGrowerorvendor.getSelectedItem().toString();

                Log.e("check", "grade_old " +Sp_GradeofGrower);

                //testing for validation (Start)
                str_gradeOfGrower = spnGrade.getSelectedItem().toString();
                Log.e("check", "grade_new: " +str_gradeOfGrower);
                //testing (End)

                //Spnselect_growerorvendor = Spn_select_growerorvendor.getSelectedItem().toString();


                Log.e("LastCropTaken", " " + SpLastCropTaken);
                Log.e("Sp_growerorvendor_one", " " + Sp_growerorvendor);


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

                if (validation()) {

                    sendData();
                }


            }
        });


        selectType();

        SpinnerWithSearch();

        //  getVendorGrowerVillage();

        getcroplist();
        getGradeofGrowerList();

        getSourceofIrrigationList();

        // lastCroptaken();

        //  getStatedata("1");


    }

//    private void getVendorGrowerVillage()
//    {
//
//        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
//        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getVendorGrowerVillageList();
//
//        result.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    String output = response.body().string();
//                    JSONObject jsonObject = new JSONObject(output);
//
//                    if (jsonObject.getString("ResponseCode").equals("1")){
//
//                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
//
//                        for (int i = 0; i<jsonArray.length(); i++)
//                    }
//
//
//
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//    }

    private void SpinnerWithSearch() {

        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(AddVendorGrower.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);


                ArrayAdapter<GrowerorVendorList> newGrowerVendorAdapter = new ArrayAdapter<GrowerorVendorList>(AddVendorGrower.this, android.R.layout.simple_list_item_1, growerorVendorLists);
                listView.setAdapter(newGrowerVendorAdapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        newGrowerVendorAdapter.getFilter().filter(charSequence);
                        int id = i;

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //text_view.setText((CharSequence) newGrowerVendorAdapter.getItem(i));
                        GrowerOrvendorName = String.valueOf(newGrowerVendorAdapter.getItem(i));
                        str_Grower_vendor_id = growerorVendorLists.get(i).getId();
                        Log.e("grower_id", " " + str_Grower_vendor_id);
                        text_view.setText(GrowerOrvendorName);
//                        String abc = newGrowerVendorAdapter.getItem(i).getId();
//                        Log.e("grower_two"," " +abc);

                        dialog.dismiss();

                    }
                });
            }
        });
    }

    private void selectType() {

        Spn_select_growerorvendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Spnselect_growerorvendor = Spn_select_growerorvendor.getSelectedItem().toString();
                Log.e("this", " " + Spnselect_growerorvendor);

                // getGrowerorvendorList(Spnselect_growerorvendor);
                getVillage();

                //  Toast.makeText(AddVendorGrower.this, " " +Spnselect_growerorvendor, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void lastCroptaken() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getcroplist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                // loadingDialog.dismissDialog();
                Log.e("getCropList", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        lastTakenCropLists.add(new CropList("--- Select crop ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            lastTakenCropLists.add(new CropList(object));
                        }
                        ArrayAdapter<CropList> lastCropTakenAdapter = new ArrayAdapter<CropList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, lastTakenCropLists);
                        lastCropTakenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spn_LastCropTaken.setAdapter(lastCropTakenAdapter);

                        Spn_LastCropTaken.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                LastCropTakenId = lastTakenCropLists.get(i).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }


    private void getVarietyList() {
//      pDialog = new ProgressDialog(AddVendorGrower.this);
//      pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getNewvarientlist(CropId);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//              if (pDialog.isShowing()){
//                  pDialog.dismiss();
//              }
                // loadingDialog.dismissDialog();

                Log.e("varietyList", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        varietyLists.clear();
                        varietyLists.add(new VarietyList("--Select Variety--"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            varietyLists.add(new VarietyList(object));
                        }
                        ArrayAdapter<VarietyList> varietyAdapter = new ArrayAdapter<VarietyList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, varietyLists);
                        varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnVariety.setAdapter(varietyAdapter);

                        spnVariety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                Varietyid = varietyLists.get(i).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                // loadingDialog.dismissDialog();
                Log.e("Error", " " + t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        });
    }

    private void getSourceofIrrigationList() {
        //  loadingDialog.startLoadingDialog();
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getSourceofirrigation();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                //   loadingDialog.dismissDialog();
                Log.e("SourceOfIrrigation", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        sourceofIrrigationLists.add(new SourceofIrrigationList("---Select Source of Irrigation---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            sourceofIrrigationLists.add(new SourceofIrrigationList(object));
                        }
                        ArrayAdapter<SourceofIrrigationList> sourceofIrrigationListArrayAdapter = new ArrayAdapter<SourceofIrrigationList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, sourceofIrrigationLists);
                        sourceofIrrigationListArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnSource.setAdapter(sourceofIrrigationListArrayAdapter);

                        spnSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                SourceOfIrrigationId = sourceofIrrigationLists.get(i).getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    private void getGrowerorvendorList(String Grower_Vendor) {
        //loadingDialog.startLoadingDialog();

//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


        String abc = Grower_Vendor;

        Log.e("two", " " + Spnselect_growerorvendor);

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("GetVendor_Grower_List", "value of send parameter: " + abc + " " + village);
        Call<ResponseBody> result = apiInterface.fetch_growervendor_on_gv_vill_id(abc, village);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                // loadingDialog.dismissDialog();
                Log.e("GrowerVendor", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    Log.e("GetVendor_Grower_List", " " + output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        growerorVendorLists.clear();
                        //  growerorVendorLists.add(new GrowerorVendorList("--Select Grower or Vendor--"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            growerorVendorLists.add(new GrowerorVendorList(object));
                        }
                        ArrayAdapter<GrowerorVendorList> growerorvendorAdapter = new ArrayAdapter<GrowerorVendorList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, growerorVendorLists);
                        growerorvendorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnGrowerorvendor.setAdapter(growerorvendorAdapter);

                        spnGrowerorvendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                GrowerVendorId = growerorVendorLists.get(i).getId();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } else if (jsonObject.getString("ResponseCode").equals("0")) {

                        growerorVendorLists.clear();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    private void getGradeofGrowerList() {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getgradeofgrower();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                // loadingDialog.dismissDialog();
                Log.e("GradeOfGrower", " " + response);

                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        Toast.makeText(AddVendorGrower.this, "Run", Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        gradeofGrowerLists.add(new GradeofGrowerList("--- Select Grade ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            gradeofGrowerLists.add(new GradeofGrowerList(object));
                        }
                        ArrayAdapter<GradeofGrowerList> gradeAdapter = new ArrayAdapter<GradeofGrowerList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, gradeofGrowerLists);
                        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnGrade.setAdapter(gradeAdapter);

                        spnGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                GradeId = gradeofGrowerLists.get(i).getId();
                                Log.e("GradeId", "" + GradeId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });

    }

    private void getcroplist() {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = apiInterface.getcroplist();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                // loadingDialog.dismissDialog();
                Log.e("getCropList", " " + response);
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        croplist.add(new CropList("--- Select crop ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            croplist.add(new CropList(object));
                        }
                        ArrayAdapter<CropList> cropAdapter = new ArrayAdapter<CropList>(AddVendorGrower.this, android.R.layout.simple_spinner_item, croplist);
                        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnCrop.setAdapter(cropAdapter);

                        spnCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                Log.e("CropItem", " " + item);
                                CropId = croplist.get(i).getId();
                                Log.e("oneCropId", " " + CropId);
                                getVarietyList();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", " " + t);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });
    }

    void getStatedata(String st_id) {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


        // loadingDialog.startLoadingDialog();
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getState("1");
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // loadingDialog.dismissDialog();
                Log.e("getStateData", " " + response);
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                String output = "";
                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        stateLists.add(new StateList("---Select State---"));

                        for (int i = 0; i < jsonArray.length(); i++) {


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
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                String item = adapterView.getItemAtPosition(i).toString();
                                state_id = stateLists.get(i).getId();
                                getDistData();
                                Log.e("district", "state" + state_id);
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
                Log.e("Error", " " + t);
                // loadingDialog.dismissDialog();

//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
            }
        });

    }


    private void getDistData() {
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

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        districtLists.clear();
                        districtLists.add(new DistrictList("---Select District---"));

                        for (int i = 0; i < jsonArray.length(); i++) {
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

                        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //  On selecting a spinner item

                                String item = adapterView.getItemAtPosition(i).toString();
                                district = districtLists.get(i).getId();
                                Log.e("district", "Dist" + district);
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

    private void getTalukaData() {

//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " + state_id + "District_id " + district);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getTaluka(state_id, district);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }
                Log.e("talkula_error", "" + response.toString());
                String output = "";

                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        talukaLists.clear();
                        talukaLists.add(new TalukaList("---Select Taluka---"));

                        for (int i = 0; i < jsonArray.length(); i++) {

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
                                taluka = talukaLists.get(i).getId();

                                // getVillage();

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

    private void getVillage() {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " + state_id + "District_id " + district);
        Log.e("Reg_id_vendor", "" + Shared_Preferences.getPrefs(AddVendorGrower.this, "Reg_id"));
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.fetch_village_of_fdo(Shared_Preferences.getPrefs(AddVendorGrower.this, "Reg_id"));
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                Log.e("Village_Error", "" + response.toString());


                try {
                    String output = "";
                    output = response.body().string();
                    Log.e("ResponseOfVillage", "" + output);
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        VillageLists.clear();
                        VillageLists.add(new VillageList("---Select village---"));

                        for (int i = 0; i < jsonArray.length(); i++) {

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

                        Log.e("village_List", "" + VillageLists);

                        spnVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                village = VillageLists.get(i).getId();
                                Log.e("Village_Id", " " + village);

                                getGrowerorvendorList(Spnselect_growerorvendor);
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


        str_distance_from_center = distance_from_center.getText().toString().trim();
        str_total_land_holding = total_land_holding.getText().toString().trim();
        str_crop_details = crop_details.getText().toString().trim();
        str_previous_company = previous_company.getText().toString().trim();
        str_last_crop_taken = last_crop_taken.getText().toString().trim();

//

//        Log.e("sendData", "distance from center " + distance_from_center);
//        Log.e("sendData", "total land holding " + total_land_holding);
//        Log.e("sendData", "source of irrigation" + SourceOfIrrigationId);
//        Log.e("sendData", "GradeId" + GradeId);
//        Log.e("sendData", "Varietyid " + Varietyid);
//        Log.e("sendData", "CropId " + CropId);
//        Log.e("sendData", "Sp_growerorvendor " + Sp_growerorvendor);
//        Log.e("sendData", "GrowerVendorId " + GrowerVendorId);
//        Log.e("sendData", "GrowerOrvendorName " + GrowerOrvendorName);

        // check new data send
        Log.e("sendDataNew", "str_Grower_vendor_id= " + str_Grower_vendor_id);
        Log.e("sendDataNew", "str_distance_from_center= " + str_distance_from_center);
        Log.e("sendDataNew", "str_total_land_holding= " + str_total_land_holding);
//        Log.e("sendDataNew", "village= " + village);
        Log.e("sendDataNew", "str_last_crop_taken= " + str_last_crop_taken);
        Log.e("sendDataNew", "SourceOfIrrigationId= " + SourceOfIrrigationId);
        Log.e("sendDataNew", "GradeId= " + GradeId);
        Log.e("sendDataNew", "Varietyid= " + Varietyid);
        Log.e("sendDataNew", "CropId= " + CropId);
        Log.e("sendDataNew", "str_crop_details= " + str_crop_details);
        Log.e("sendDataNew", "Spnselect_growerorvendor= " + Spnselect_growerorvendor);
        Log.e("sendDataNew", "str_previous_company= " + str_previous_company);
        Log.e("sendDataNew", "Reg_id= " + Shared_Preferences.getPrefs(AddVendorGrower.this, "Reg_id"));


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.AddVendorGrower(
                str_Grower_vendor_id,
                str_distance_from_center,
                str_total_land_holding,
                str_last_crop_taken,
                SourceOfIrrigationId,
                GradeId,
                Varietyid,
                CropId,
                str_crop_details,
                Spnselect_growerorvendor,
                str_previous_company,
                Shared_Preferences.getPrefs(AddVendorGrower.this, "Reg_id"));

//        Sp_growerorvendor,
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    Log.e("AddVendorGrower", " " + output);


                    if (jsonObject.getString("ResponseCode").equals("1")) {

                        Toast.makeText(AddVendorGrower.this, "Data sent successfully! ", Toast.LENGTH_SHORT).show();

                        Intent ii = new Intent(AddVendorGrower.this, MainActivity.class);
                        startActivity(ii);
                        finish();

                    } else if (jsonObject.getString("ResponseCode").equals("0")) {

                        Toast.makeText(AddVendorGrower.this, "" + jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }
//
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("sendDataFailed", " " + t);

            }
        });


    }


    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
    }


    private void closeFABMenu() {
        linLayHeader.setBackgroundColor(getResources().getColor(R.color.white2));
        isFABOpen = false;
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout1.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ii = new Intent(AddVendorGrower.this, MainActivity.class);
        startActivity(ii);
        finish();
    }


    private boolean validation() {
        boolean VALID = true;
        if (TextUtils.isEmpty(total_land_holding.getText().toString())) {
            VALID = false;
            total_land_holding.setError("Please Enter Total Land Holding");

        }
        if (TextUtils.isEmpty(distance_from_center.getText().toString())) {
            VALID = false;
            distance_from_center.setError("Please Enter Distance From Center");
        }

        if (TextUtils.isEmpty(last_crop_taken.getText().toString())) {
            VALID = false;
            last_crop_taken.setError("Please Enter Last Crop Taken");
        }
        if (TextUtils.isEmpty(crop_details.getText().toString())) {
            VALID = false;
            crop_details.setError("Please Enter Crop Details");
        }
        if (TextUtils.isEmpty(previous_company.getText().toString())) {

            VALID = false;
            previous_company.setError("Please Enter Previous Company");
        } else if (spvillage.equals("--Select--") || spvillage.equals("---Select village---")) {
            VALID = false;

            View selectedView = Spn_Village.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("");
            Toast.makeText(this, "Please Select Village", Toast.LENGTH_SHORT).show();
        }
        else if (GrowerOrvendorName.equals("")) {
            VALID = false;
            Toast.makeText(this, "Please Select Grower or Vendor", Toast.LENGTH_SHORT).show();
        }
        else if (Sp_Crop.equals("--Select--") || Sp_Crop.equals("--- Select crop ---")) {

            VALID = false;

            View selectedView = Spn_Crop.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("");
            Toast.makeText(this, "Please Select Crop", Toast.LENGTH_SHORT).show();
        }

        else if (Sp_Variety.equals("--Select--") || Sp_Variety.equals("--Select Variety--")){
            VALID = false;

            View selectedView = Spn_Variety.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("Please Select Variety");
            Toast.makeText(this, "Please Select Variety", Toast.LENGTH_SHORT).show();
        }

        else if (sp_source_of_irrigation.equals("--Select--") || sp_source_of_irrigation.equals("---Select Source of Irrigation---")){


            View selectedView = Spn_SourceOfIrrigartion.getSelectedView();
            TextView selectedTextView = (TextView)  selectedView;
            selectedTextView.setError("Please Select Source of Irrigation");
            Toast.makeText(this, "Please Select Source of Irrigation", Toast.LENGTH_SHORT).show();
            VALID = false;

        }
        else if (str_gradeOfGrower.equals("--Select--") || str_gradeOfGrower.equals("--- Select Grade ---")){

            VALID = false;
            View selectedView = spnGrade.getSelectedView();
            TextView selectedText = (TextView) selectedView;
            selectedText.setError("");
            Toast.makeText(this, "Please Select Grade of Grower", Toast.LENGTH_SHORT).show();
        }


        return VALID;


    }

//    boolean validateSpinner(Spinner spinner, String error) {
//        View selectedView = spinner.getSelectedView();
//        if (selectedView != null && selectedView instanceof TextView) {
//            TextView selectedTextView = (TextView) selectedView;
//            if (selectedTextView.getText().equals("")) {
//                selectedTextView.setError(error);
//                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//                return false;
//            }
//        }
//        return true;
//    }

}