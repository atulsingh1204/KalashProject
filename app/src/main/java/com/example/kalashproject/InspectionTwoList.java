package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.kalashproject.Adapters.InspectionOneAdapter;
import com.example.kalashproject.Adapters.InspectionTwoAdapter;
import com.example.kalashproject.ModelList.InspectionOneList;
import com.example.kalashproject.ModelList.InspectionTwoModelList;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectionTwoList extends AppCompatActivity {

    RecyclerView rec_inspection_two;
    ArrayList<InspectionTwoModelList> list = new ArrayList<InspectionTwoModelList>();
    InspectionTwoAdapter inspectionTwoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_two_list);

        rec_inspection_two = findViewById(R.id.rec_inspection_two);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inspection One List");


        getInspectionTwoData();


    }

    private void getInspectionTwoData()
    {
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.InspectionTwoList("5");
        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    Log.e("Response", "Output" +output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        for (int i = 0; i < jsonArray.length(); i ++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            list.add(new InspectionTwoModelList(object));
                        }
                        rec_inspection_two.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                        inspectionTwoAdapter = new InspectionTwoAdapter(InspectionTwoList.this, list);
                        rec_inspection_two.setAdapter(inspectionTwoAdapter);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}