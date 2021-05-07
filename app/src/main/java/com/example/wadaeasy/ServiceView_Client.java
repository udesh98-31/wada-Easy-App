package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ServiceView_Client extends AppCompatActivity {

    String t;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view__client);

        test = findViewById(R.id.textView17);

        Intent i = getIntent();
        t =i.getStringExtra("ID");

        test.setText(t);

    }
}