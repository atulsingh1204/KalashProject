package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kalashproject.Adapters.ApprovedOrderAdapter;
import com.example.kalashproject.Adapters.PendingOrdersAdapter;
import com.example.kalashproject.ModelList.ApprovedOrderList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedOrderActivity extends AppCompatActivity {

    RecyclerView rec_approved_order;
    ArrayList<ApprovedOrderList> list = new ArrayList<ApprovedOrderList>();
    ApprovedOrderAdapter approvedOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order);

        rec_approved_order = findViewById(R.id.rec_approved_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Approved Orders");

        getApprovedData();
    }

    private void getApprovedData()
    {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.getApprovedOrderList(Shared_Preferences.getPrefs(ApprovedOrderActivity.this,"Reg_id"));

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {




                try {
                    String output = response.body().string();

                    JSONObject jsonObject = new JSONObject(output);

                    Log.e("ApprovedOrderResponse", " " +output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(ApprovedOrderActivity.this, "Fetched Successfully", Toast.LENGTH_SHORT).show();

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        for(int i = 0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            list.add(new ApprovedOrderList(object));

                        }

                        rec_approved_order.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
                        approvedOrderAdapter = new ApprovedOrderAdapter(ApprovedOrderActivity.this, list);
                        rec_approved_order.setAdapter(approvedOrderAdapter);

                    }


                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}