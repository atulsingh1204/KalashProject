package com.example.kalashproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.kalashproject.ModelList.DistrictList;
import com.example.kalashproject.ModelList.FamilyCodeList;
import com.example.kalashproject.ModelList.StateList;
import com.example.kalashproject.ModelList.TalukaList;
import com.example.kalashproject.ModelList.VillageList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.Utils.FileUtil;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVendor extends AppCompatActivity {

    Spinner SpState, SpDistrict, SpTaluka, SpVillage;
    Spinner spn_type_grower, spn_generateFamilyCode;
    String str_spn_type_grower, str_spn_generateFamilyCode;

    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

    CircleImageView upload_AadhaarCard, upload_PANCard, upload_other;
    TextView text_aadhaarCard, text_panCard, text_other;
    String imgPath = "", imgPath2 = "", imgPath3 = "";
    TextView tv_familyCode;

    TextView addVendor_tv_next;

    String str_edt_full_name, str_edt_mobileNumber, str_edt_aadharNumber, str_edt_Address;
    String familyCodeName = "", familyCodeId = "";

    TextView edt_family_code;
    EditText edt_full_name, edt_mobileNumber, edt_aadharNumber, edt_Address;

    ArrayList<FamilyCodeList> familyCodeLists = new ArrayList<FamilyCodeList>();

    Dialog dialog;
    ArrayList<StateList> stateLists = new ArrayList<StateList>();
    ArrayList<DistrictList> districtLists = new ArrayList<DistrictList>();
    ArrayList<TalukaList> talukaLists = new ArrayList<TalukaList>();
    ArrayList<VillageList> VillageLists = new ArrayList<VillageList>();
    public String state_id = "1", district = "1", taluka = "1", village = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor);

        addVendor_tv_next = findViewById(R.id.addVendor_tv_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Vendor");


        SpState = findViewById(R.id.Spn_State);
        SpDistrict = findViewById(R.id.Spn_District);
        SpTaluka = findViewById(R.id.Spn_Taluka);
        SpVillage = findViewById(R.id.Spn_Village);

        //TextView
        //  text_aadhaarCard = findViewById(R.id.text_aadhaarCard);
//        text_panCard = findViewById(R.id.text_panCard);
//        text_other = findViewById(R.id.text_other);


        // EditText

        edt_full_name = findViewById(R.id.edt_full_name);
        edt_family_code = findViewById(R.id.edt_family_code);
        edt_mobileNumber = findViewById(R.id.edt_mobileNumber);
        edt_aadharNumber = findViewById(R.id.edt_aadharNumber);
        edt_Address = findViewById(R.id.edt_Address);

        //Textview
        tv_familyCode = findViewById(R.id.tv_familyCode);

        // Circular Image
        upload_AadhaarCard = findViewById(R.id.upload_AadhaarCard);
        upload_PANCard = findViewById(R.id.upload_PANCard);
        upload_other = findViewById(R.id.upload_other);

        // Spinner
        spn_type_grower = findViewById(R.id.spn_type_grower);
        spn_generateFamilyCode = findViewById(R.id.spn_generateFamilyCode);


        addVendor_tv_next = findViewById(R.id.addVendor_tv_next);


        getStatedata("1");

        selectType();
        GenerateFamilyCode();

        GetFamilyCodeList();
        GetFamilyCode();

        GetAadhaar();
        GetPAN();
        GetOthersDocuments();


        addVendor_tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidationData();
                ValidateSpinner();
                if (awesomeValidation.validate()) {
                    SendData();
                }

            }
        });


    }

    private void ValidateSpinner() {

        if (spn_generateFamilyCode.getSelectedItem().toString().trim().equals("--- Choose Options ---")){
            Toast.makeText(this, "Please Select Family Code Option!", Toast.LENGTH_SHORT).show();

        }

    }

    private void ValidationData() {
        awesomeValidation.addValidation(this, R.id.edt_full_name,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        awesomeValidation.addValidation(this, R.id.spn_type_grower,
                RegexTemplate.NOT_EMPTY, R.string.invalid_spinner);

        awesomeValidation.addValidation(this, R.id.edt_mobileNumber,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_number);

        awesomeValidation.addValidation(this, R.id.edt_aadharNumber,
                "^[2-9][0-9]{11}$", R.string.invalid_aadhaar);

        awesomeValidation.addValidation(this, R.id.edt_Address,
                RegexTemplate.NOT_EMPTY, R.string.invalid_address);


    }


    private void selectType() {
        spn_type_grower.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_spn_type_grower = spn_type_grower.getSelectedItem().toString();
                Log.e("response", " str_spn_type_grower " + str_spn_type_grower);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void GenerateFamilyCode() {

        spn_generateFamilyCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_spn_generateFamilyCode = spn_generateFamilyCode.getSelectedItem().toString();
                Log.e("Response", "str_spn_generateFamilyCode " + str_spn_generateFamilyCode);

                if (str_spn_generateFamilyCode.equals("No")) {
                    tv_familyCode.setVisibility(View.VISIBLE);
                    edt_family_code.setVisibility(View.VISIBLE);
                } else {
                    tv_familyCode.setVisibility(View.GONE);
                    edt_family_code.setVisibility(View.GONE);
                    familyCodeName = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void SendData() {


        Log.e("sendData", "str_edt_full_name " + str_edt_full_name);
        Log.e("sendData", "str_edt_mobileNumber " + str_edt_mobileNumber);
        Log.e("sendData", "str_edt_aadharNumber " + str_edt_aadharNumber);
        Log.e("sendData", "str_edt_Address " + str_edt_Address);
        Log.e("sendData", "state_id " + state_id);
        Log.e("sendData", "district " + district);
        Log.e("sendData", "taluka " + taluka);
        Log.e("sendData", "village " + village);
        Log.e("sendData", "str_spn_generateFamilyCode " + str_spn_generateFamilyCode);
        Log.e("sendData", "Fdo_id " + Shared_Preferences.getPrefs(AddVendor.this, "Reg_id"));
        Log.e("sendData", "generate_fc " + familyCodeName);


        str_edt_full_name = edt_full_name.getText().toString().trim();
        str_edt_mobileNumber = edt_mobileNumber.getText().toString().trim();
        str_edt_aadharNumber = edt_aadharNumber.getText().toString().trim();
        str_edt_Address = edt_Address.getText().toString().trim();

        MultipartBody.Part aadhaar = null;
        MultipartBody.Part PAN = null;
        MultipartBody.Part Other = null;


        if (!imgPath.equalsIgnoreCase("")) {

            aadhaar = prepareImagePart(imgPath, "aadhar_card");
        }

        if (!imgPath2.equalsIgnoreCase("")) {

            PAN = prepareImagePart(imgPath2, "pan_card");
        }

        if (!imgPath3.equalsIgnoreCase("")) {

            Other = prepareImagePart(imgPath3, "extra_document");
        }


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.sendAddVendorDetails(RequestBody.create(MediaType.parse("text/plain"), str_edt_full_name),
                RequestBody.create(MediaType.parse("text/plain"), str_edt_mobileNumber),
                RequestBody.create(MediaType.parse("text/plain"), str_edt_aadharNumber),
                RequestBody.create(MediaType.parse("text/plain"), str_edt_Address),
                RequestBody.create(MediaType.parse("text/plain"), state_id),
                RequestBody.create(MediaType.parse("text/plain"), district),
                RequestBody.create(MediaType.parse("text/plain"), taluka),
                RequestBody.create(MediaType.parse("text/plain"), village),
                RequestBody.create(MediaType.parse("text/plain"), str_spn_generateFamilyCode),
                RequestBody.create(MediaType.parse("text/plain"), Shared_Preferences.getPrefs(AddVendor.this, "Reg_id")),
                aadhaar,
                PAN,
                Other,
                RequestBody.create(MediaType.parse("text/plain"), familyCodeName)


        );


        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String output;
                    output = response.body().string();
                    Log.e("response", "response: " + output);
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        Toast.makeText(AddVendor.this, "Data Submitted Successfully!", Toast.LENGTH_SHORT).show();
                    } else if (jsonObject.getString("ResponseCode").equals("0")) {
                        Toast.makeText(AddVendor.this, "" + jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(AddVendor.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.e("response", "error: " + t);
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
//
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
                        ArrayAdapter<StateList> dataAdapter = new ArrayAdapter<StateList>(AddVendor.this, android.R.layout.simple_spinner_item, stateLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpState.setAdapter(dataAdapter);

                        SpState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


                        ArrayAdapter<DistrictList> dataAdapter = new ArrayAdapter<DistrictList>(AddVendor.this, android.R.layout.simple_spinner_item, districtLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpDistrict.setAdapter(dataAdapter);

                        SpDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        ArrayAdapter<TalukaList> dataAdapter = new ArrayAdapter<>(AddVendor.this, android.R.layout.simple_spinner_item, talukaLists);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SpTaluka.setAdapter(dataAdapter);

                        SpTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                taluka = talukaLists.get(i).getId();

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

    private void getVillage() {
//        pDialog = new ProgressDialog(AddVendorGrower.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("getTaluka: ", "state_id: " + state_id + "District_id " + district);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getVillage(state_id, district, village);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (pDialog.isShowing()){
//                    pDialog.dismiss();
//                }

                Log.e("Village_Error", "" + response.toString());
                String output = "";

                try {
                    output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        VillageLists.clear();
                        VillageLists.add(new VillageList("--Select village---"));

                        for (int i = 0; i < jsonArray.length(); i++) {

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
                                village = VillageLists.get(i).getId();

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


    private void GetAadhaar() {


        upload_AadhaarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(AddVendor.this)
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE

                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {


                            Intent pickPhotoOne = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhotoOne, 0);//one can be replaced with any action code
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });
    }


    private void GetPAN() {

        upload_PANCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(AddVendor.this)
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE

                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {


                            Intent pickPhotoTwo = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhotoTwo, 1);//one can be replaced with any action code
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });
    }


    private void GetOthersDocuments() {


        upload_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(AddVendor.this)
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE

                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {


                            Intent pickPhotoThree = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhotoThree, 2);//one can be replaced with any action code
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode != RESULT_CANCELED) {

            Log.e("response", "requestCode " + requestCode);
            switch (requestCode) {


                case 0:

                    if (resultCode == RESULT_OK && data != null) {

                        Uri img = data.getData();
                        Picasso.get().load(img).into(upload_AadhaarCard);
                        Log.e("response", "img " + img);

                        imgPath = FileUtil.getPath(AddVendor.this, img);
                        Uri imgUri = Uri.parse(imgPath);

                        Log.e("response", "imgPath " + imgPath);
                        Log.e("response", "imgUri " + imgUri);
                        break;

                    }

                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img2 = data.getData();
                        Picasso.get().load(img2).into(upload_PANCard);

                        imgPath2 = FileUtil.getPath(AddVendor.this, img2);
                        Uri imgUri2 = Uri.parse(imgPath2);

                        Log.e("response", "imgPath " + imgPath2);
                        Log.e("response", "imgUri " + imgUri2);


                        break;

                    }

                case 2:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri img3 = data.getData();
                        Picasso.get().load(img3).into(upload_other);

                        imgPath3 = FileUtil.getPath(AddVendor.this, img3);

                        break;
                    }

            }

        }

    }

    private MultipartBody.Part prepareImagePart(String path, String partName) {
        File file = new File(path);
        Log.e("response", "path: " + path);
        Log.e("response", "partName: " + partName);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);

    }


    private void GetFamilyCode() {
        edt_family_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(AddVendor.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<FamilyCodeList> familyCodeListArrayAdapter = new ArrayAdapter<FamilyCodeList>(AddVendor.this, android.R.layout.simple_list_item_1, familyCodeLists);
                listView.setAdapter(familyCodeListArrayAdapter);


                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        familyCodeListArrayAdapter.getFilter().filter(charSequence);
                        int id = i;
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        familyCodeName = familyCodeListArrayAdapter.getItem(i).toString();
                        familyCodeId = familyCodeLists.get(i).getId();

                        Log.e("response", "familyCodeName: " + familyCodeName);
                        Log.e("response", "familyCodeId: " + familyCodeId);

                        edt_family_code.setText(familyCodeName);

                        dialog.dismiss();
                    }
                });

            }
        });
    }


    private void GetFamilyCodeList() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.getFamilyCodeList();
        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();
                    Log.e("response", "response of family code list " + output);

                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        familyCodeLists.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            familyCodeLists.add(new FamilyCodeList(object));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}