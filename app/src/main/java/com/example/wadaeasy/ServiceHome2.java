package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceHome2 extends AppCompatActivity {
Button myService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home2);
        this.setTitle("වැඩ Easy - Home");

        myService = findViewById(R.id.AddService);

        myService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHome2.this, ServiceView.class);
                startActivity(intent);
            }
        });
    }
}