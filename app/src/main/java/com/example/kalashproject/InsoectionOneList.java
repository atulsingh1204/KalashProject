package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.kalashproject.Adapters.InspectionOneAdapter;
import com.example.kalashproject.ModelList.InspectionOneList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
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

public class InsoectionOneList extends AppCompatActivity {

    RecyclerView rec_inspection_one;
    ArrayList<InspectionOneList> list = new ArrayList<InspectionOneList>();
    InspectionOneAdapter inspectionOneAdapter;

    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insoection_one_list);

        rec_inspection_one = findViewById(R.id.rec_inspection_one);
        spinKitView = findViewById(R.id.spin_kit);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection One List");

        getInspectionOneData();

    }

    private void getInspectionOneData()
    {

        spinKitView.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.InspectionOneList(Shared_Preferences.getPrefs(InsoectionOneList.this, "Reg_id"));

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    spinKitView.setVisibility(View.GONE);

                    String output = response.body().string();
                    Log.e("Response", "Output" +output);

                    JSONObject jsonObject = new JSONObject(output);
                   if (jsonObject.getString("ResponseCode").equals("1")){

                       Toast.makeText(InsoectionOneList.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                       JSONArray jsonArray = jsonObject.getJSONArray("Data");

                       for (int i = 0; i<jsonArray.length(); i++){
                           JSONObject object = jsonArray.getJSONObject(i);
                           list.add(new InspectionOneList(object));
                       }

                       rec_inspection_one.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                       inspectionOneAdapter = new InspectionOneAdapter(InsoectionOneList.this, list);
                       rec_inspection_one.setAdapter(inspectionOneAdapter);
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