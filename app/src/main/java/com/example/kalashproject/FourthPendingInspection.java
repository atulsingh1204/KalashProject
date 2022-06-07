package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kalashproject.Adapters.FourthPendingAdapter;
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

public class FourthPendingInspection extends AppCompatActivity {

    RecyclerView rec_fourth_pending;
    ArrayList<AllInspectionDataModelList> List = new ArrayList<AllInspectionDataModelList>();
    FourthPendingAdapter fourthPendingAdapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_pending_inspection);

        rec_fourth_pending = findViewById(R.id.rec_fourth_pending);
        progressDialog = new ProgressDialog(FourthPendingInspection.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Fourth Inspection Pending");

        getFourthPendingList();

    }

    private void getFourthPendingList() {

        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.FourthPendingList(Shared_Preferences.getPrefs(FourthPendingInspection.this, "Reg_id"));
        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if (jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new AllInspectionDataModelList(object));
                        }

                        rec_fourth_pending.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                        fourthPendingAdapter = new FourthPendingAdapter(FourthPendingInspection.this, List);
                        rec_fourth_pending.setAdapter(fourthPendingAdapter);
                    }else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(FourthPendingInspection.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(FourthPendingInspection.this, ""+t, Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}