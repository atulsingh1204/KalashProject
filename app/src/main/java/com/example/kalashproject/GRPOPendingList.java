package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.kalashproject.Adapters.GRPOAdapter;
import com.example.kalashproject.ModelList.AllInspectionDataModelList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GRPOPendingList extends AppCompatActivity {

    RecyclerView rec_grpo_pending;
    ArrayList<AllInspectionDataModelList> List = new ArrayList<AllInspectionDataModelList>();
    GRPOAdapter grpoAdapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grpopending_list);

        rec_grpo_pending = findViewById(R.id.rec_grpo_pending);
        progressDialog = new ProgressDialog(GRPOPendingList.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("GRPO Pending List");

        getGRPOPendingList();
    }

    private void getGRPOPendingList() {

        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.GRPOPendingList(Shared_Preferences.getPrefs(GRPOPendingList.this, "Reg_id"));
        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    Log.e("Atul", "Checkit" +jsonObject);
                    if (jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Data");

                        for (int i = 0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            List.add(new AllInspectionDataModelList(object));

                        }
                        rec_grpo_pending.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false));
                        grpoAdapter = new GRPOAdapter(GRPOPendingList.this, List);
                        rec_grpo_pending.setAdapter(grpoAdapter);

                    }

                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(GRPOPendingList.this, "" +jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(GRPOPendingList.this, "" +t, Toast.LENGTH_SHORT).show();
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