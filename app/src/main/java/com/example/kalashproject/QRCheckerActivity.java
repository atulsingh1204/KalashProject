package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.kalashproject.ModelList.ApprovedOrderList;
import com.example.kalashproject.ModelList.QR_code_details_List;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCheckerActivity extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private EditText codeData;
    Button btn_submit;
    String data;
    String abc = "";
    String abcde = "";
    String order_id;



    // ArrayList<QRCodeList> list = new ArrayList<QRCodeList>();
    //ArrayList<QRCodeList> list = new ArrayList();

    ArrayList<String> listtransfer = new ArrayList<>();
    ArrayList<QR_code_details_List> list = new ArrayList<QR_code_details_List>();

    //ArrayList<String> listOne = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrchecker);
        codeData=findViewById(R.id.edt_data);
        scannerView=findViewById(R.id.Scanner);
        btn_submit = findViewById(R.id.btn_submit);



        int PERMISSION_ALL =1;
        String[] PERMISSIONS={
                android.Manifest.permission.CAMERA
        };

        if(!hasPermission(this,PERMISSIONS))
        {
            ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_ALL);
        }
        else
        {

        }


        runCodeScanner();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                abc = listtransfer.toString();
                abc = abc.replace("[","").replace("]","").replace(" ","");
                Log.e("abc", "" +abc);

                Log.e("convertList", " " +abc);

//                Intent ii = new Intent(QRCheckerActivity.this, ApprovedOrderActivity.class);
//                startActivity(ii);
//                finish();

//                tv_one.setText((CharSequence) list.get(1).QRData);
//                tv_two.setText((CharSequence) list.get(2).QRData);
//                tv_three.setText((CharSequence) list.get(3).QRData);

                SendQRCodedata(); // Hide for now but its usable later on to call API
            }
        });





    }



    private void runCodeScanner()
    {
        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setScanMode(ScanMode.SINGLE);
        codeScanner.setDecodeCallback(new DecodeCallback()
        {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        data = result.getText();

//                         String abcd = "\""+data+"\"";
//
//                        String abcde=abcd.replace("[", "").replace("]","").replace(" ","");
//                        Log.e("abcde", "" +abcde);

                        listtransfer.add(data);

                        int h=listtransfer.size();
                        Log.e("h", "" +h);


//
                        Toast.makeText(QRCheckerActivity.this, "Added successfully!", Toast.LENGTH_SHORT).show();

                        //  codeData.setText(data);

                    }
                });
            }
        });


        scannerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    public static boolean hasPermission(Context context, String... permissions)
    {
        if( context !=null && permissions != null)
        {
            for (String permission : permissions)
            {
                if(ActivityCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void SendQRCodedata() {

        ApiInterface apiInterface = Myconfig.getRetrofit().create(ApiInterface.class);

        Log.e("value_of_abc"," " +abc);
        // abc= abc.trim();
        Call<ResponseBody> result = (Call<ResponseBody>) apiInterface.GetQRDetails(abc);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                        String output = response.body().string();

                        JSONObject jsonObject = new JSONObject(output);
                        Log.e("QRCheckResponse", " " + output);

                        if (jsonObject.getString("ResponseCode").equals("1")) {
                            Toast.makeText(QRCheckerActivity.this, "" + jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = jsonObject.getJSONArray("Data");

                            for(int i = 0; i<jsonArray.length(); i++){

                                JSONObject object = jsonArray.getJSONObject(i);
                                list.add(new QR_code_details_List(object));


                                }

                            Log.e("size_of_list","" +list.size());

                            Intent ii = new Intent(QRCheckerActivity.this, View_Details_QR_Checker_Activity.class);
                            ii.putExtra("qr_for", list.get(0).getQr_for());
                            ii.putExtra("id", list.get(0).getId());
                            ii.putExtra("crop_name",list.get(0).getCrop_name());
                            ii.putExtra("variety_name", list.get(0).getVariety_name());
                            ii.putExtra("batch_number", list.get(0).getBatch_number());
                            ii.putExtra("number_of_packing", list.get(0).getNumber_of_packing());
                            ii.putExtra("weight_in_packets", list.get(0).getWeight_in_packets());
                            ii.putExtra("germination_rate", list.get(0).getGermination_rate());
                            ii.putExtra("genetic_purity", list.get(0).getGenetic_purity());

                            startActivity(ii);


                        } else if (jsonObject.getString("ResponseCode").equals("0")) {

                            Toast.makeText(QRCheckerActivity.this, "" + jsonObject.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();

                            Intent ii = new Intent(QRCheckerActivity.this, View_Details_QR_Checker_Activity.class);
                            startActivity(ii);
                            finish();
                        }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}