package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.kalashproject.MyLibrary.CustomRequest;
import com.example.kalashproject.WebService.ConstantClass;
import com.example.kalashproject.WebService.ProjectConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AddVendorGrower extends AppCompatActivity {

    Spinner spn_state, spn_dist, spn_tal, spn_village;
    TextView tv_next;
    List<String> stat_Namelist = new ArrayList<>();
    List<String> stat_Idlist = new ArrayList<>();
    ArrayList<String> dist_list = new ArrayList<>();
    List<String> dist_Idlist = new ArrayList<>();
    ArrayList<String> tal_list = new ArrayList<>();
    List<String> tal_Idlist = new ArrayList<>();
    ArrayList<String> village_list = new ArrayList<>();
    List<String> village_Idlist = new ArrayList<>();

    String location_id, name;


    private ProgressDialog pDialog;
    EditText edt_farmer_name, edt_farmer_email, edt_farmer_contact, edt_farmer_adhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor_grower);

        tv_next = findViewById(R.id.tv_next);
        spn_state = findViewById(R.id.spn_state);
        spn_dist = findViewById(R.id.spn_dist);
        spn_tal = findViewById(R.id.spn_tal);
        spn_village = findViewById(R.id.spn_village);
     //   camera = new Camera(AddNewFarmerActivity.this);
      //  iv_photo1 = findViewById(R.id.iv_photo1);
        edt_farmer_name = findViewById(R.id.edt_farmer_name);
        edt_farmer_email = findViewById(R.id.edt_farmer_email);
        edt_farmer_contact = findViewById(R.id.edt_farmer_contact);
        edt_farmer_adhar = findViewById(R.id.edt_farmer_adhar);

        StateList();

        spn_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                ConstantClass.Farmer_state=selectedItem;
                int pos = stat_Namelist.indexOf(selectedItem);
                Log.e("abc", " "+stat_Idlist.get(pos));
                String item = stat_Idlist.get(pos);
                ConstantClass.Farmer_state = item;
                Log.i("##", "## State Name" + selectedItem);
                Log.i("##", "## State Id " + item);

           //     DistrictList(item);


            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    private void StateList() {
        pDialog = new ProgressDialog(AddVendorGrower.this);
        pDialog.setMessage(getString(R.string.please_wait));
        pDialog.setCancelable(false);
        pDialog.show();
        stat_Namelist.clear();
        stat_Idlist.clear();
        stat_Namelist.add("Select State");
        stat_Idlist.add("0");
        Map<String, String> params = new HashMap<>();

        RequestQueue requestQueue = Volley.newRequestQueue(AddVendorGrower.this);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, ProjectConfig.Statelist, params, this.createRequestSuccessListener_Login(), this.createRequestErrorListener_Login());
        requestQueue.add(jsObjRequest);
    }

    private Response.ErrorListener createRequestErrorListener_Login() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        };
    }



    private Response.Listener<JSONObject> createRequestSuccessListener_Login() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                Log.i("##", "##" + response.toString());
                try {
                    JSONArray data1 = response.getJSONArray("data");
                    System.out.print("##State==" + data1);
                    for (int i = 0; i < data1.length(); i++) {
                        JSONObject jobj = data1.getJSONObject(i);

                        name = jobj.getString("name");
                        location_id = jobj.getString("location_id");
                        stat_Namelist.add(name);
                        stat_Idlist.add(location_id);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            AddVendorGrower.this, android.R.layout.simple_spinner_item, stat_Namelist);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn_state.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }



    private void getstateData(String st_ID) {
        stateList.clear();
        mDialog.show();
        APIInterface apiInterface = MyConfig.getRetrofit().create(APIInterface.class);
        final Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getstate("1");
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String output = "";
                mDialog.dismiss();
                try {
                    output = response.body().string();
                    Log.d("org", "state: " + output);
                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("result").equals("true")) {

//                        Toast.makeText(EditUserProfile.this, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = jsonObject.getJSONArray("tbl_state");
                        // Parsing json
                        stateList.add(new StateList("--- Select State ---"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                stateList.add(new StateList(obj));
                            } catch (JSONException e) {
                                //Log.d("Progress Dialog","Progress Dialog");
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<StateList> dataAdapter = new ArrayAdapter<StateList>(AddressFilterActivity.this, android.R.layout.simple_spinner_item, stateList);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        state_spinner.setAdapter(dataAdapter);
                        String speci = Shared_Preferences.getPrefs(AddressFilterActivity.this, Constants.PROFILE_STATE);
                        for (int i = 0; i < stateList.size(); i++) {
                            if (stateList.get(i).getState_Name().equals(speci)) {
                                state_spinner.setSelection(i);
                                break;
                            }
                        }
                        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //  On selecting a spinner item
                                String item = adapterView.getItemAtPosition(i).toString();
                                state_id = stateList.get(i).getState_Id();
                                getDistData(state_id);
                            }

                            public void onNothingSelected(AdapterView<?> adapterView) {
                                return;
                            }
                        });
                    }
                    mDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mDialog.dismiss();
            }
        });
    }


}