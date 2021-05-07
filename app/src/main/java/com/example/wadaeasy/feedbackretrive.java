package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class feedbackretrive extends AppCompatActivity {

    Intent intent=getIntent();
    String number1=intent.getStringExtra("number");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackretrive);
    }
}