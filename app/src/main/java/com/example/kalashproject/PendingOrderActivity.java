package com.example.kalashproject;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.widget.Toast;

import com.example.kalashproject.Adapters.PendingOrdersAdapter;
import com.example.kalashproject.ModelList.PendigOrderList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingOrderActivity extends AppCompatActivity {

    RecyclerView rec_pending_order;
    PendingOrdersAdapter pendingOrdersAdapter;
    Context context;
    ArrayList<PendigOrderList> pendLists = new ArrayList<PendigOrderList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);

        rec_pending_order = findViewById(R.id.rec_pending_oder);


        getPendiOrderList();
    }

    private void getPendiOrderList() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.getPendingOrderList(Shared_Preferences.getPrefs(PendingOrderActivity.this, "Reg_id"));
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {




                try {
                    String output = response.body().string();
                    JSONObject jsonObject = new JSONObject(output);
                    if(jsonObject.getString("ResponseCode").equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");


                        for(int i = 0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            pendLists.add(new PendigOrderList(object));
                        }

                        Log.e("PendingOrderList", " " +pendLists);
                        rec_pending_order.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
                        pendingOrdersAdapter = new PendingOrdersAdapter(context, pendLists);
                        rec_pending_order.setAdapter(pendingOrdersAdapter);
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
}