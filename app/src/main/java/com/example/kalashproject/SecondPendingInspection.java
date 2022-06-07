package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kalashproject.Adapters.SecondPendingAdapter;
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

public class SecondPendingInspection extends AppCompatActivity {

    RecyclerView rec_second_pending;
    ArrayList<AllInspectionDataModelList> List = new ArrayList<>();
    SecondPendingAdapter secondPendingAdapter;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_pending_inspection);

        rec_second_pending = findViewById(R.id.rec_second_pending);
        progressDialog = new ProgressDialog(SecondPendingInspection.this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Second Inspection Pending");

        getSecondPendingList();

    }

    private void getSecondPendingList() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Log.e("Response", "Reg_id: " +Shared_Preferences.getPrefs(SecondPendingInspection.this, "Reg_id"));
        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.SecondPendingList(Shared_Preferences.getPrefs(SecondPendingInspection.this, "Reg_id"));

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    String output = response.body().string();
                    Log.e("response", "output: " +output);

                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")){

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        for (int i = 0; i< jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new AllInspectionDataModelList(object));
                        }

                        rec_second_pending.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                        secondPendingAdapter = new SecondPendingAdapter(SecondPendingInspection.this, List);
                        rec_second_pending.setAdapter(secondPendingAdapter);
                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(SecondPendingInspection.this, ""+jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(SecondPendingInspection.this, ""+t, Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing())
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