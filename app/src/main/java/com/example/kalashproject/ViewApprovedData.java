package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalashproject.ModelList.QRCodeList;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.WebService.ApiInterface;
import com.example.kalashproject.WebService.Myconfig;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewApprovedData extends AppCompatActivity {

    TextView view_approved_growervendorid,view_approved_full_name,view_approved_grower_or_vendor,view_approved_distance,view_approved_crop_name,view_approved_variety_name,view_approved_seed_or_seedling,view_approved_land_holding,view_approved_source_of_irrigation_name,view_approved_grade_of_grower_name,view_approved_crop_details,view_approved_previous_company,view_approved_last_crop_taken;

    Button view_approved_scan_btn,view_approved_submit_btn;
    String growervendorid = "";
    String packing_inward="packing_inward";
    ArrayList<String> list = new ArrayList();
    String list_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approved_data);

        view_approved_growervendorid = findViewById(R.id.view_approved_growervendorid);
        view_approved_full_name = findViewById(R.id.view_approved_full_name);
        view_approved_grower_or_vendor = findViewById(R.id.view_approved_grower_or_vendor);
        view_approved_crop_name = findViewById(R.id.view_approved_crop_name);
        view_approved_distance = findViewById(R.id.view_approved_distance);
        view_approved_variety_name = findViewById(R.id.view_approved_variety_name);
        view_approved_seed_or_seedling = findViewById(R.id.view_approved_seed_or_seedling);
        view_approved_land_holding = findViewById(R.id.view_approved_land_holding);
        view_approved_source_of_irrigation_name = findViewById(R.id.view_approved_source_of_irrigation_name);
        view_approved_grade_of_grower_name = findViewById(R.id.view_approved_grade_of_grower_name);
        view_approved_crop_details = findViewById(R.id.view_approved_crop_details);
        view_approved_previous_company = findViewById(R.id.view_approved_previous_company);
        view_approved_last_crop_taken = findViewById(R.id.view_approved_last_crop_taken);

        view_approved_scan_btn = findViewById(R.id.view_approved_scan_btn);
        view_approved_submit_btn = findViewById(R.id.view_approved_submit_btn);



        Intent ii = getIntent();

       String order_id = ii.getStringExtra("order_id");
       Log.e("order_id","" +order_id);
      String growervendorid =  ii.getStringExtra("growervendorid");
      String full_name =  ii.getStringExtra("full_name");
      String grower_or_vendor =  ii.getStringExtra("grower_or_vendor");
      String distance =  ii.getStringExtra("distance");
      String crop_name =  ii.getStringExtra("crop_name");
      String variety_name =  ii.getStringExtra("variety_name");
      String seed_or_seedling =  ii.getStringExtra("seed_or_seedling");
      String land_holding =  ii.getStringExtra("land_holding");
      String source_of_irrigation_name =  ii.getStringExtra("source_of_irrigation_name");
      String grade_of_grower_name =  ii.getStringExtra("grade_of_grower_name");
      String crop_details =  ii.getStringExtra("crop_details");
      String previous_company =  ii.getStringExtra("previous_company");
      String last_crop_taken =  ii.getStringExtra("last_crop_taken");

      // list = ii.getStringExtra("QRList");

       Bundle bundle =  getIntent().getExtras();
       list = bundle.getStringArrayList("QRList");
       Log.e("qrCodeList","" +list);






        view_approved_growervendorid.setText(growervendorid);
        view_approved_full_name.setText(full_name);
        view_approved_grower_or_vendor.setText(grower_or_vendor);
        view_approved_crop_name.setText(crop_name);
        view_approved_distance.setText(distance);
        view_approved_variety_name.setText(variety_name);
        view_approved_seed_or_seedling.setText(seed_or_seedling);
        view_approved_land_holding.setText(land_holding);
        view_approved_source_of_irrigation_name.setText(source_of_irrigation_name);
        view_approved_grade_of_grower_name.setText(grade_of_grower_name);
        view_approved_crop_details.setText(crop_details);
        view_approved_previous_company.setText(previous_company);
        view_approved_last_crop_taken.setText(last_crop_taken);




        view_approved_scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(ViewApprovedData.this, QRCodeScanner.class);
                ii.putExtra("order_id","" +order_id);
                startActivity(ii);
                finish();

            }
        });




        Log.e("AtulOne", "" +growervendorid);
        Log.e("AtulOne", "" +full_name);
        Log.e("AtulOne", "" +grower_or_vendor);
        Log.e("AtulOne", "" +distance);
        Log.e("AtulOne", "" +crop_name);
        Log.e("AtulOne", "" +variety_name);
        Log.e("AtulOne", "" +seed_or_seedling);
        Log.e("AtulOne", "" +source_of_irrigation_name);
        Log.e("AtulOne", "" +grade_of_grower_name);
        Log.e("AtulOne", "" +crop_details);
        Log.e("AtulOne", "" +previous_company);
        Log.e("AtulOne", "" +last_crop_taken);
        Log.e("AtulOne", "" +land_holding);



    }




}