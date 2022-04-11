package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.GrowerorVendorList;
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

public class TestActivity extends AppCompatActivity {

    Spinner Spn_select_growerorvendor, spnGrowerorvendor;
    TextView text_view;
    String Spnselect_growerorvendor;
    String GrowerVendorId="";
    ArrayList<GrowerorVendorList> growerorVendorLists = new ArrayList<GrowerorVendorList>();
    Dialog dialog;
    String GrowerOrvendorName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Spn_select_growerorvendor = findViewById(R.id.Spn_select_growerorvendor);
        spnGrowerorvendor = findViewById(R.id.Spn_growerorvendor);
        text_view = findViewById(R.id.text_view);

        selectType();




        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(TestActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);


                ArrayAdapter<GrowerorVendorList> newGrowerVendorAdapter = new ArrayAdapter<GrowerorVendorList>(TestActivity.this, android.R.layout.simple_list_item_1,growerorVendorLists);
                listView.setAdapter(newGrowerVendorAdapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        newGrowerVendorAdapter.getFilter().filter(charSequence);
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
                            text_view.setText(GrowerOrvendorName);

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
                getGrowerorvendorList(Spnselect_growerorvendor);
                //  Toast.makeText(AddVendorGrower.this, " " +Spnselect_growerorvendor, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        Call<ResponseBody> result = apiInterface.getVendorListByOption(abc);
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
                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        growerorVendorLists.clear();
                       // growerorVendorLists.add(new GrowerorVendorList("--Select Grower or Vendor--"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            growerorVendorLists.add(new GrowerorVendorList(object));
                        }
                        ArrayAdapter<GrowerorVendorList> growerorvendorAdapter = new ArrayAdapter<GrowerorVendorList>(TestActivity.this, android.R.layout.simple_spinner_item, growerorVendorLists);
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

}