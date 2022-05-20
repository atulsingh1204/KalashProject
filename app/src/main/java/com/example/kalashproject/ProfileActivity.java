package com.example.kalashproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.example.kalashproject.MyLibrary.Shared_Preferences;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfileActivity extends AppCompatActivity {

    CircularImageView circularImageView;

    EditText id, user_name, emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        circularImageView = findViewById(R.id.logo);

        //circularImageView.setCircleColor(Color.WHITE);


        id= findViewById(R.id.id);
        user_name= findViewById(R.id.user_name);
        emailid= findViewById(R.id.emailid);

        id.setText(Shared_Preferences.getPrefs(ProfileActivity.this, "Reg_id"));
        user_name.setText(Shared_Preferences.getPrefs(ProfileActivity.this, "User_name"));
        emailid.setText(Shared_Preferences.getPrefs(ProfileActivity.this, "Email_id"));


    }
}