package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.example.kalashproject.StartActivities.LoginActivity;
import com.example.kalashproject.StartActivities.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private String version;
    private String versionName = "";
    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv_version = findViewById(R.id.tv_version);
//        try {
//            versionName = SplashActivity.this.getPackageManager()
//                    .getPackageInfo(SplashActivity.this.getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        tv_version.setText(versionName);
//        Log.d("TAG", "onCreate: " + Shared_Preferences.getPrefs(SplashActivity.this, "lang"));
//        if (Shared_Preferences.getPrefs(SplashActivity.this, "lang")==null) {
//            changeLang("en", SplashActivity.this);
//
//        } else {
//            String lang = Shared_Preferences.getPrefs(SplashActivity.this, "lang");
//            changeLang(lang, SplashActivity.this);
//
//        }
//        check();
//        if (CheckNetwork.isInternetAvailable(SplashActivity.this)) //returns true if internet available
//        {
//            // noConnectionLayout.setVisibility(View.GONE);
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    ConnectivityManager conMgr = (ConnectivityManager) getSystemService(SplashActivity.this.CONNECTIVITY_SERVICE);
//                    if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                            conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//                        SharedPreferences prfs = getSharedPreferences("SCTDist", Context.MODE_PRIVATE);
//                        String Astatus = prfs.getString("Login", "");
//                        String token = prfs.getString("Token", "");
//                        Log.i("##Token", token);
//                        Constant.token = token;
//                        if (Astatus.equals("1")) {
//                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//
//                        } else {
//                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//                            finish();
//                        }
//                    }
//                }
//
//
//            }, SPLASH_TIME_OUT);
//        } else {
//            Snackbar.make(getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG)
//                    .setAction("OK", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            check();
//                        }
//                    })
//                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
//                    .setDuration(50000)
//                    .show();
//
//        }
//
//
//    }
//
//    private void check() {
//        if (CheckNetwork.isInternetAvailable(SplashActivity.this)) //returns true if internet available
//        {
//
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    ConnectivityManager conMgr = (ConnectivityManager) getSystemService(SplashActivity.this.CONNECTIVITY_SERVICE);
//
//                    if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                            conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//
//                        SharedPreferences prfs = getSharedPreferences("SCTDist", Context.MODE_PRIVATE);
//                        String Astatus = prfs.getString("Login", "");
//                        String token = prfs.getString("Token", "");
//                        Log.i("##Token", token);
//                        Constant.token = token;
//                        if (Astatus.equals("1")) {
//                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//
//                        } else {
//                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
//                            finish();
//                        }
//                    }
//                }
//
//
//            }, SPLASH_TIME_OUT);
//        } else {
//            Snackbar.make(getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG)
//                    .setAction("OK", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            check();
//                        }
//                    })
//                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
//                    .setDuration(50000)
//                    .show();
//
//        }
//    }
//
//    public static void changeLang(String lang, Activity activity) {
//
//        Resources activityRes = activity.getResources();
//        Configuration activityConf = activityRes.getConfiguration();
//        Locale newLocale = new Locale(lang);
//        activityConf.setLocale(newLocale);
//        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());
//        Resources applicationRes = activity.getResources();
//        Configuration applicationConf = applicationRes.getConfiguration();
//        applicationConf.setLocale(newLocale);
//        applicationRes.updateConfiguration(applicationConf,
//                applicationRes.getDisplayMetrics());

//    }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    Intent ii = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(ii);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    finish();
                }



        },2000);


    }

}
