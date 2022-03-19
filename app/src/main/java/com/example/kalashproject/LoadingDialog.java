package com.example.kalashproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class LoadingDialog {

private Activity activity;
private AlertDialog alertDialog;


LoadingDialog(Activity MyActivity){

    activity = MyActivity;

}

    void startLoadingDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custome_dialog,null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismissDialog(){
    alertDialog.dismiss();
    }

}
