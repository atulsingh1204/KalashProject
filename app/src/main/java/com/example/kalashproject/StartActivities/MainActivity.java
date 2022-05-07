package com.example.kalashproject.StartActivities;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.kalashproject.AddVendor;
import com.example.kalashproject.AddVendorGrower;
import com.example.kalashproject.ApprovedOrderActivity;
import com.example.kalashproject.Form3;
import com.example.kalashproject.GRPO;
import com.example.kalashproject.InsoectionOneList;
import com.example.kalashproject.InspectionFormFour;
import com.example.kalashproject.InspectionFormOne;
import com.example.kalashproject.InspectionFormThree;
import com.example.kalashproject.InspectionFormTwo;
import com.example.kalashproject.InspectionTwoList;
import com.example.kalashproject.ModelList.InspectionOneList;
import com.example.kalashproject.ModelList.SliderData;
import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.PendingOrderActivity;
import com.example.kalashproject.QRCheckerActivity;
import com.example.kalashproject.R;
import com.example.kalashproject.RejectedOrderActivity;
import com.example.kalashproject.TestTwoActivity;
import com.example.kalashproject.WebService.AppConfig;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
//    boolean isFABOpen = false;
//    FrameLayout linLayHeader;

    private AppConfig appConfig;

    private Dialog mdialog;
    private Button btn_yes, btn_no;

    Context context;
    private TableRow trprofile, trtarget, tr_visits, li_farmar_meeting, li_distributor, li_youtube, tr_subscribe, tr_sct_result, tr_meetings, tr_orders, li_orders, li_sales, tr_massage_com, li_massage, li_compilent, tr_new_dist, tr_downloads, li_brochure, li_banner, tr_video, li_all_vedio, li_target, tr_blogs, tr_product, tr_farmerGroup, tr_about, tr_Logout;
    private TableRow trprofile_two, tr_orders_two, tr_add_order, tr_pending_order, tr_approved_order, tr_closed_orders, tr_inspection, tr_qr_checker, tr_inspection_one, tr_inspection_two, tr_inspection_three, tr_inspection_four, tr_tentative_grpo;
    private LinearLayout tv_teargets, linLay_farmer_vist, linLay_farmer_meet, linLay_new_farmer, linLay_watch_video,
            linLayyuotube_subscriber, linLay_upload_photo;

    ArrayList<SlideModel> sliderModelList = new ArrayList<>();
    private ImageSlider imageSlider;
    TextView tv_drwer_user_name;

    LinearLayout fabLayout1;

    LinearLayout ll_inspection_one, ll_inspection_two, ll_inspection_three, ll_inspection_four, ll_inspection_grpo;

    LinearLayout linearLayout_meeting, linearLayout_orders, linearLayout_massages, linearLayout_download, linearLayout_vedio;
    // Slider
    ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

    LinearLayout linearLayout_order_two, linearlayout_inspection;

    String url1 = "https://wallpapercave.com/wp/wp4184149.jpg";
    String url2 = "https://thumbs.dreamstime.com/z/vegetables-1430407.jpg";
    String url3 = "https://thumbs.dreamstime.com/z/vegetables-1430407.jpg";
    String url4 = "https://www.news-medical.net/image.axd?picture=2020%2F1%2Fshutterstock_321864554.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Reg_id ", " " + Shared_Preferences.getPrefs(MainActivity.this, "Reg_id"));
        Log.e("user_name ", " " + Shared_Preferences.getPrefs(MainActivity.this, "User_name"));
        Log.e("Email Id ", " " + Shared_Preferences.getPrefs(MainActivity.this, "Email_id"));

        sliderModelList.add(new SlideModel("https://d3mvlb3hz2g78.cloudfront.net/wp-content/uploads/2016/12/thumb_720_450_Plants_Make_Fruits_and_Vegetablesdreamstime_xxl_10601543.jpg", ScaleTypes.FIT));
        sliderModelList.add(new SlideModel("https://www.news-medical.net/image.axd?picture=2020%2F1%2Fshutterstock_321864554.jpg", ScaleTypes.FIT));
        sliderModelList.add(new SlideModel("https://www.lalpathlabs.com/blog/wp-content/uploads/2019/01/Fruits-and-Vegetables.jpg", ScaleTypes.FIT));
        sliderModelList.add(new SlideModel("https://sunnewsonline.com/wp-content/uploads/2018/09/fruits_and_vegetables.jpg", ScaleTypes.FIT));

        //Slider
        imageSlider = findViewById(R.id.slider);
        imageSlider.setImageList(sliderModelList);
        tv_drwer_user_name = findViewById(R.id.tv_drwer_user_name);

        trprofile_two = findViewById(R.id.trprofile_two);
        tr_orders_two = findViewById(R.id.tr_orders_two);
        tr_add_order = findViewById(R.id.tr_add_order);
        tr_pending_order = findViewById(R.id.tr_pending_order);
        tr_approved_order = findViewById(R.id.tr_approved_order);
        tr_closed_orders = findViewById(R.id.tr_closed_orders);
        tr_inspection = findViewById(R.id.tr_inspection);
        tr_pending_order = findViewById(R.id.tr_pending_order);
        tr_approved_order = findViewById(R.id.tr_approved_order);
        tr_closed_orders = findViewById(R.id.tr_closed_orders);
        tr_qr_checker = findViewById(R.id.tr_qr_checker);
        tr_inspection_one = findViewById(R.id.tr_inspection_one);
        tr_inspection_two = findViewById(R.id.tr_inspection_two);
        tr_inspection_three = findViewById(R.id.tr_inspection_three);
        tr_inspection_four = findViewById(R.id.tr_inspection_four);
        tr_tentative_grpo = findViewById(R.id.tr_tentative_grpo);

//        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);
        linearLayout_order_two = findViewById(R.id.linearLayout_order_two);
        linearlayout_inspection = findViewById(R.id.linearlayout_inspection);
        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);

        TableRow tr_Logout = findViewById(R.id.tr_Logout);
//        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);
        linearLayout_meeting = findViewById(R.id.linearLayout_meeting);
        drawerLayout = findViewById(R.id.drawer_layout);

        //Linear Layout

        ll_inspection_one = findViewById(R.id.ll_inspection_one);
        ll_inspection_two = findViewById(R.id.ll_inspection_two);
        ll_inspection_three = findViewById(R.id.ll_inspection_three);
        ll_inspection_four = findViewById(R.id.ll_inspection_four);
        ll_inspection_grpo = findViewById(R.id.ll_inspection_grpo);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        trprofile_two.setOnClickListener(this);
        tr_orders_two.setOnClickListener(this);
        tr_add_order.setOnClickListener(this);
        tr_inspection.setOnClickListener(this);
        tr_pending_order.setOnClickListener(this);
        tr_approved_order.setOnClickListener(this);
        tr_closed_orders.setOnClickListener(this);
        tr_Logout.setOnClickListener(this);
        tr_qr_checker.setOnClickListener(this);
        tr_inspection_one.setOnClickListener(this);
        tr_inspection_two.setOnClickListener(this);
        tr_inspection_three.setOnClickListener(this);
        tr_inspection_four.setOnClickListener(this);
        tr_tentative_grpo.setOnClickListener(this);


        tv_drwer_user_name.setText("Hello " + Shared_Preferences.getPrefs(MainActivity.this, "User_name"));  // showing user name in navigation drawer



        ll_inspection_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsoectionOneList.class);
                startActivity(intent);
            }
        });

        ll_inspection_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InspectionTwoList.class);
                startActivity(intent);
            }
        });

        ll_inspection_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(MainActivity.this, TestTwoActivity.class);
                startActivity(ii);
            }
        });

    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;

        switch (view.getId()) {

            case R.id.tr_orders_two:
                if (!linearLayout_order_two.isShown()) {
                    linearLayout_order_two.setVisibility(View.VISIBLE);

                } else {
                    linearLayout_order_two.setVisibility(View.GONE);
                }
                break;


            case R.id.tr_add_order:
                startActivity(new Intent(MainActivity.this, AddVendorGrower.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearLayout_order_two.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                //  Toast.makeText(MainActivity.this, "Add orders", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.tr_inspection:
                if (!linearlayout_inspection.isShown()) {
                    linearlayout_inspection.setVisibility(View.VISIBLE);
                } else {
                    linearlayout_inspection.setVisibility(View.GONE);
                }

                break;

            case R.id.tr_pending_order:
                startActivity(new Intent(MainActivity.this, PendingOrderActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearLayout_order_two.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;

            case R.id.tr_approved_order:
                startActivity(new Intent(MainActivity.this, ApprovedOrderActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearLayout_order_two.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_closed_orders:
                startActivity(new Intent(MainActivity.this, RejectedOrderActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearLayout_order_two.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_qr_checker:
                startActivity(new Intent(MainActivity.this, QRCheckerActivity.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                // linearLayout_orders.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_Logout:

                //  startActivity(new Intent(MainActivity.this, Form3.class));
                // overridePendingTransition(R.anim.right_in, R.anim.left_out);

                ShowDialog();
                // linearLayout_orders.setVisibility(View.GONE);

                drawerLayout.closeDrawers();

                break;

            case R.id.tr_inspection_one:
                startActivity(new Intent(MainActivity.this, InspectionFormOne.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearlayout_inspection.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;

            case R.id.tr_inspection_two:
                startActivity(new Intent(MainActivity.this, InspectionFormTwo.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearlayout_inspection.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_inspection_three:
                startActivity(new Intent(MainActivity.this, InspectionFormThree.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearlayout_inspection.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_inspection_four:
                startActivity(new Intent(MainActivity.this, InspectionFormFour.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearlayout_inspection.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            case R.id.tr_tentative_grpo:
                startActivity(new Intent(MainActivity.this, GRPO.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                linearlayout_inspection.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                break;


            default:
                // Toast.makeText(this, "Switch case", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    void ShowDialog() {

        mdialog = new Dialog(MainActivity.this);
        mdialog.setCancelable(false);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mdialog.setContentView(R.layout.alert_dialog);
        btn_yes = mdialog.findViewById(R.id.btn_logout_yes);
        btn_no = mdialog.findViewById(R.id.btn_logout_no);
        btn_yes.setText("Submit");
        btn_no.setText("Cancel");
        TextView text_msg = (TextView) mdialog.findViewById(R.id.text_msg);
        ImageView iv_image = (ImageView) mdialog.findViewById(R.id.iv_image);
        iv_image.setImageDrawable(getResources().getDrawable(R.drawable.logout));
        TextView text = (TextView) mdialog.findViewById(R.id.text);
        text.setText("Logout...");
        text_msg.setText("Are You Sure .. !!");
        text.setGravity(View.TEXT_ALIGNMENT_CENTER);
        appConfig = new AppConfig(this);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appConfig.updateUserLogin(false);
                Intent ii = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(ii);
                finish();
                mdialog.dismiss();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdialog.dismiss();
            }
        });
        mdialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}