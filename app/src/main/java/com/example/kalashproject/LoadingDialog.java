package com.example.kalashproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class LoadingDialog {

    private Activity activity;
//private AlertDialog alertDialog;

    ProgressDialog progressDialog;

    LoadingDialog(Activity MyActivity) {

        activity = MyActivity;

    }

    void startLoadingDialog() {
//    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.custome_dialog,null));
//        builder.setCancelable(true);
//
//        alertDialog = builder.create();
//        alertDialog.show();
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
        progressDialog.setContentView(R.layout.custome_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    void dismissDialog() {
        progressDialog.dismiss();
    }

}
