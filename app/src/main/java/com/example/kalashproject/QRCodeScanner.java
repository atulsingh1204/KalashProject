package com.example.kalashproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
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
import com.example.kalashproject.ModelList.QRCodeList;
import com.google.zxing.Result;

import java.util.ArrayList;

public class QRCodeScanner extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private EditText codeData;
    Button btn_qr_code_submit;
    String data;
    TextView tv_one, tv_two, tv_three;

    ArrayList<QRCodeList> list = new ArrayList<QRCodeList>();

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

                list.add(new QRCodeList(data));
                Log.e("listSize", " " +list.size());
                Toast.makeText(QRCodeScanner.this, " " + list.toString(), Toast.LENGTH_SHORT).show();
//
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

                        list.add(new QRCodeList(data));

                        Toast.makeText(QRCodeScanner.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                        tv_one.setText(" " +list.size());

                      //  codeData.setText(data);

                        Log.e("QRCode", " " +list.toString());
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
}

