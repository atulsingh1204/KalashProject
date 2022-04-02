package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kalashproject.Adapters.RejectOrderAdapter;
import com.example.kalashproject.ModelList.RejectOrderList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.android.gms.common.api.Api;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RejectedOrderActivity extends AppCompatActivity {

    RecyclerView rec_rejected_oder;
    ArrayList<RejectOrderList> list = new ArrayList<RejectOrderList>();
    RejectOrderAdapter rejectOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejected_order);

        rec_rejected_oder = findViewById(R.id.rec_rejected_oder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Rejected Orders");


        getRejectedOrderList();
    }

    private void getRejectedOrderList() {


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.rejectedOrderList(Shared_Preferences.getPrefs(RejectedOrderActivity.this, "Reg_id"));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();
                    Log.e("RejectedOrdersResponse", " " +output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        for(int i = 1; i<jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            list.add(new RejectOrderList(object));
                        }

//                        Toast.makeText(RejectedOrderActivity.this, "Size of rejected list is " +list, Toast.LENGTH_SHORT).show();
                        Log.e("rejectedList", " " +list);
                        rec_rejected_oder.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
                        rejectOrderAdapter = new RejectOrderAdapter(RejectedOrderActivity.this, list);
                        rec_rejected_oder.setAdapter(rejectOrderAdapter);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}