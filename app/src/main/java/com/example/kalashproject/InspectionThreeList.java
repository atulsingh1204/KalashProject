package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.kalashproject.Adapters.InspectionThreeAdapter;
import com.example.kalashproject.ModelList.InspectionThreeModelList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

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

public class InspectionThreeList extends AppCompatActivity {


    RecyclerView rec_inspection_three;
    ArrayList<InspectionThreeModelList> List = new ArrayList<InspectionThreeModelList>();
    InspectionThreeAdapter inspectionThreeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_three_list);

        rec_inspection_three = findViewById(R.id.rec_inspection_three);


        getInspectionThreeData();

    }

    private void getInspectionThreeData() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Log.e("fdo_id", "fdo_id: "+ Shared_Preferences.getPrefs(InspectionThreeList.this, "Reg_id"));
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.InspectionThreeList(Shared_Preferences.getPrefs(InspectionThreeList.this, "Reg_id"));

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();

                    Log.e("InspectionThree", "response " +output);

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new InspectionThreeModelList(object));

                        }

                        rec_inspection_three.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                        inspectionThreeAdapter = new InspectionThreeAdapter(InspectionThreeList.this, List);
                        rec_inspection_three.setAdapter(inspectionThreeAdapter);

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
}