package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.kalashproject.ModelList.QRCodeList;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCodeScanner extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private EditText codeData;
    Button btn_qr_code_submit;
    String data;
    String abc = "";
    String abcde = "";

    TextView tv_one, tv_two, tv_three;

   // ArrayList<QRCodeList> list = new ArrayList<QRCodeList>();
    //ArrayList<QRCodeList> list = new ArrayList();

    ArrayList<String> listtransfer = new ArrayList<>();

    //ArrayList<String> listOne = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);
        codeData=findViewById(R.id.edt_data);
        scannerView=findViewById(R.id.Scanner);

        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
//        tv_three = findViewById(R.id.tv_three);

        btn_qr_code_submit = findViewById(R.id.btn_qr_code_submit);


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


        btn_qr_code_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                 abc = listtransfer.toString();
                 abc = abc.replace("[","").replace("]","").replace(" ","");
                 Log.e("abc", "" +abc);

                Log.e("convertList", " " +abc);

                Intent ii = new Intent(QRCodeScanner.this, ApprovedOrderActivity.class);
                startActivity(ii);
                finish();

//                for(int i = 0; i<list.size(); i++){
//
//                    codeData.setText(list.get(i).getQRData());
////                    tv_one.setText(list.get(1).getQRData());
////                    tv_two.setText(list.get(2).getQRData());
//
//
//                }

                

//                tv_one.setText((CharSequence) list.get(1).QRData);
//                tv_two.setText((CharSequence) list.get(2).QRData);
//                tv_three.setText((CharSequence) list.get(3).QRData);

                SendQRCodedata();
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
                        tv_one.setText(String.valueOf(h));
                        Log.e("h", "" +h);


//
                        Toast.makeText(QRCodeScanner.this, "Added successfully!", Toast.LENGTH_SHORT).show();

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
        Call<ResponseBody> result= apiInterface.sendQRCodeList(abc);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String output = response.body().string();

                    JSONObject jsonObject = new JSONObject(output);

                    if (jsonObject.getString("ResponseCode").equals("1")){

                        Toast.makeText(QRCodeScanner.this, ""+jsonObject.getString("ResponseMessage") ,Toast.LENGTH_SHORT).show();

                        Log.e("sendDataResponse",""+jsonObject.getString("ResponseMessage") );
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

