package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.StartActivities.MainActivity;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GRPO extends AppCompatActivity {

    EditText bag_size1, bag_size2, bag_size3, bag_size4, bag_size5;
    EditText numberOfBags1, numberOfBags2, numberOfBags3, numberOfBags4, numberOfBags5;
    EditText moisture, total_quantity, qty_pending;
    Spinner spn_authorization;
    TextView grpo_submit;
    LinearLayout linear_pending_qty;

    String str_authorization;
    String str_total_quantity;
    String str_bag_size1, str_bag_size2, str_bag_size3, str_bag_size4,str_bag_size5;
    String str_numberOfBags1, str_numberOfBags2, str_numberOfBags3, str_numberOfBags4, str_numberOfBags5;
    String str_moisture, str_qty_pending;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grpo);

        bag_size1 = findViewById(R.id.bag_size1);
        bag_size2 = findViewById(R.id.bag_size2);
        bag_size3 = findViewById(R.id.bag_size3);
        bag_size4 = findViewById(R.id.bag_size4);
        bag_size5 = findViewById(R.id.bag_size5);

        numberOfBags1 = findViewById(R.id.numberOfBags1);
        numberOfBags2 = findViewById(R.id.numberOfBags2);
        numberOfBags3 = findViewById(R.id.numberOfBags3);
        numberOfBags4 = findViewById(R.id.numberOfBags4);
        numberOfBags5 = findViewById(R.id.numberOfBags5);

        moisture = findViewById(R.id.moisture);
        spn_authorization = findViewById(R.id.spn_authorization);
        grpo_submit = findViewById(R.id.grpo_submit);
        linear_pending_qty = findViewById(R.id.linear_pending_qty);
        total_quantity = findViewById(R.id.total_quantity);
        qty_pending = findViewById(R.id.qty_pending);



        getAuthorization();

        grpo_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();
            }
        });



    }



    private void getAuthorization()
    {
        spn_authorization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                str_authorization = spn_authorization.getSelectedItem().toString();
                Log.e("authorization","" +str_authorization);

                if (str_authorization.equals("No")){
                    linear_pending_qty.setVisibility(View.VISIBLE);
                }else {
                    linear_pending_qty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void sendData()
    {

        str_total_quantity = total_quantity.getText().toString().trim();
        str_bag_size1 = bag_size1.getText().toString().trim();
        str_bag_size2 = bag_size2.getText().toString().trim();
        str_bag_size3 = bag_size3.getText().toString().trim();
        str_bag_size4 = bag_size4.getText().toString().trim();
        str_bag_size5 = bag_size5.getText().toString().trim();

        str_numberOfBags1 = numberOfBags1.getText().toString().trim();
        str_numberOfBags2 = numberOfBags2.getText().toString().trim();
        str_numberOfBags3 = numberOfBags3.getText().toString().trim();
        str_numberOfBags4 = numberOfBags4.getText().toString().trim();
        str_numberOfBags5 = numberOfBags5.getText().toString().trim();

        str_moisture = moisture.getText().toString().trim();
        str_qty_pending = qty_pending.getText().toString().trim();


        Log.e("sendDataGRPO","str_total_quantity: " +str_total_quantity);
        Log.e("sendDataGRPO","str_bag_size1: " +str_bag_size1);
        Log.e("sendDataGRPO","str_bag_size2: " +str_bag_size2);
        Log.e("sendDataGRPO","str_bag_size3: " +str_bag_size3);
        Log.e("sendDataGRPO","str_bag_size4: " +str_bag_size4);
        Log.e("sendDataGRPO","str_bag_size5: " +str_bag_size5);

        Log.e("sendDataGRPO","str_numberOfBags1: " +str_numberOfBags1);
        Log.e("sendDataGRPO","str_numberOfBags2: " +str_numberOfBags2);
        Log.e("sendDataGRPO","str_numberOfBags3: " +str_numberOfBags3);
        Log.e("sendDataGRPO","str_numberOfBags4: " +str_numberOfBags4);
        Log.e("sendDataGRPO","str_numberOfBags5: " +str_numberOfBags5);

        Log.e("sendDataGRPO","str_moisture: " +str_moisture);
        Log.e("sendDataGRPO","str_authorization: " +str_authorization);


        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);
        Call<ResponseBody> Result = (Call<ResponseBody>) apiInterface.grpo_add("1",str_total_quantity,str_bag_size1, str_numberOfBags1, str_bag_size2, str_numberOfBags2, str_bag_size3, str_numberOfBags3, str_bag_size4, str_numberOfBags4, str_bag_size5, str_numberOfBags5, str_moisture, str_authorization,str_qty_pending);

        Result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String output = response.body().string();
                    Log.e("response", "response " +response.toString());

                    JSONObject jsonObject = new JSONObject(output);
                    Log.e("response", "output " +output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(GRPO.this, "Data Submitted Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GRPO.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (jsonObject.getString("ResponseCode").equals("0")){
                        Toast.makeText(GRPO.this, ""+jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
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