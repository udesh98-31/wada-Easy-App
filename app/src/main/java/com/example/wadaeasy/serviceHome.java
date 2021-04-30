package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class serviceHome extends AppCompatActivity {
Button addService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home);
        this.setTitle("වැඩ Easy - Home");

        addService = findViewById(R.id.AddService);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(serviceHome.this, AddService1.class);
                startActivity(intent);
            }
        });
    }
}