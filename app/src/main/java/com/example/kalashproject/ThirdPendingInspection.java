package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kalashproject.Adapters.ThirdPendingAdapter;
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

public class ThirdPendingInspection extends AppCompatActivity {

    RecyclerView rec_third_pending;
    ThirdPendingAdapter thirdPendingAdapter;
    ArrayList<AllInspectionDataModelList> List = new ArrayList<AllInspectionDataModelList>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_pending_inspection);

        rec_third_pending = findViewById(R.id.rec_third_pending);
        progressDialog = new ProgressDialog(ThirdPendingInspection.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Third Pending List");


        GetThirdPendingList();



    }

    private void GetThirdPendingList() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.ThirdPendingList(Shared_Preferences.getPrefs(ThirdPendingInspection.this, "Reg_id"));
        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    String output = response.body().string();
                    Log.e("thirdPendingInspection", " " +output);
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new AllInspectionDataModelList(object));
                        }
                        thirdPendingAdapter = new ThirdPendingAdapter(ThirdPendingInspection.this, List);
                        rec_third_pending.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false));
                        rec_third_pending.setAdapter(thirdPendingAdapter);
                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(ThirdPendingInspection.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ThirdPendingInspection.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}