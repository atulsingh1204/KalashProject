package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.kalashproject.Adapters.AllInspectionDataAdapter;
import com.example.kalashproject.ModelList.AllInspectionDataModelList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllInspectionList extends AppCompatActivity {

    RecyclerView rec_inspection_all_list;
    
    ArrayList<AllInspectionDataModelList> List = new ArrayList<AllInspectionDataModelList>();
    AllInspectionDataAdapter allInspectionDataAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_inspection_list);

        rec_inspection_all_list = findViewById(R.id.rec_inspection_all_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("First Inspection List");


        getAllLIst();
    }

    private void getAllLIst() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.AllInspectionList(Shared_Preferences.getPrefs(AllInspectionList.this, "Reg_id"));

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String output;
                    output = response.body().string();

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new AllInspectionDataModelList(object));
                        }
                        
                        rec_inspection_all_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                        allInspectionDataAdapter = new AllInspectionDataAdapter(AllInspectionList.this, List);
                        rec_inspection_all_list.setAdapter(allInspectionDataAdapter);
                        
                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(AllInspectionList.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                    }



                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(AllInspectionList.this, "" +t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}