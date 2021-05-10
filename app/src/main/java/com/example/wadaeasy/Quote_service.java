package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Quote_service extends AppCompatActivity {

    String chrage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_service);

        Intent intent=getIntent();
        chrage=intent.getStringExtra("CHARGE");
    }
}