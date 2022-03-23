package com.example.kalashproject;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    boolean isFABOpen = false;
    FrameLayout linLayHeader;
    Context context;

   private TableRow trprofile,trtarget,tr_visits,li_farmar_meeting,li_distributor,li_youtube,tr_subscribe,tr_sct_result,tr_meetings,tr_orders,li_orders,li_sales,tr_massage_com,li_massage,li_compilent,tr_new_dist,tr_downloads,li_brochure,li_banner,tr_video,li_all_vedio,li_target,tr_blogs,tr_product,tr_farmerGroup,tr_about,tr_Logout;
   private  TableRow trprofile_two,tr_orders_two,tr_add_order,tr_pending_order,tr_approved_order,tr_closed_orders,tr_inspection;
   private LinearLayout tv_teargets, linLay_farmer_vist, linLay_farmer_meet, linLay_new_farmer, linLay_watch_video,
           linLayyuotube_subscriber, linLay_upload_photo;

    CircleImageView fab, fab1;
    LinearLayout fabLayout1;

    LinearLayout linearLayout_meeting, linearLayout_orders, linearLayout_massages, linearLayout_download, linearLayout_vedio;

    LinearLayout linearLayout_order_two;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (CircleImageView) findViewById(R.id.fab);
        fab1 = (CircleImageView) findViewById(R.id.fab1);

        trprofile_two = findViewById(R.id.trprofile_two);
        tr_orders_two = findViewById(R.id.tr_orders_two);
        tr_add_order = findViewById(R.id.tr_add_order);
        tr_pending_order = findViewById(R.id.tr_pending_order);
        tr_approved_order = findViewById(R.id.tr_approved_order);
        tr_closed_orders = findViewById(R.id.tr_closed_orders);
        tr_inspection = findViewById(R.id.tr_inspection);

        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);
        linearLayout_order_two = findViewById(R.id.linearLayout_order_two);
        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);

        TableRow tr_Logout = findViewById(R.id.tr_Logout);
        linLayHeader = (FrameLayout) findViewById(R.id.linLayHeader);
        linearLayout_meeting = findViewById(R.id.linearLayout_meeting);
        drawerLayout = findViewById(R.id.drawer_layout);


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle =    new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        trprofile_two.setOnClickListener(this);
        tr_orders_two.setOnClickListener(this);
        tr_add_order.setOnClickListener(this);
        tr_inspection.setOnClickListener(this);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    linLayHeader.setBackgroundColor(getResources().getColor(R.color.trans_grey_color));
                    showFABMenu();
                } else {
                    linLayHeader.setBackgroundColor(getResources().getColor(R.color.white2));
                    closeFABMenu();
                }
            }
        });


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddVendor.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                if (!isFABOpen) {
                } else {
                    closeFABMenu();
                }
            }
        });






    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;

        switch (view.getId()){

            case R.id.tr_orders_two:
                if(!linearLayout_order_two.isShown()){
                    linearLayout_order_two.setVisibility(View.VISIBLE);

                }else {
                    linearLayout_order_two.setVisibility(View.GONE);
                }
                break;


            case R.id.tr_add_order:
                startActivity(new Intent(MainActivity.this, AddVendorGrower.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
               // linearLayout_orders.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
              //  Toast.makeText(MainActivity.this, "Add orders", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tr_inspection:
                startActivity(new Intent(MainActivity.this, Form3.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                // linearLayout_orders.setVisibility(View.GONE);
                drawerLayout.closeDrawers();

                break;

            case R.id.tr_Logout:

                startActivity(new Intent(MainActivity.this, Form3.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
               // linearLayout_orders.setVisibility(View.GONE);
                drawerLayout.closeDrawers();

                break;

            default:
                Toast.makeText(this, "Switch case", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
    }


    private void closeFABMenu() {
        linLayHeader.setBackgroundColor(getResources().getColor(R.color.white2));
        isFABOpen = false;
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout1.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

}