package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceHome2 extends AppCompatActivity {
Button myService;
Button appoiment;
Button Request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home2);
        this.setTitle("වැඩ Easy - Home");

        myService = findViewById(R.id.AddService);
        appoiment=findViewById(R.id.Appoiments);
        Request=findViewById(R.id.Request);

        myService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHome2.this, ServiceView.class);
                startActivity(intent);
            }
        });

        appoiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ServiceHome2.this,AppoimentVIewS.class);
                startActivity(intent);
            }
        });

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceHome2.this,wantedlistview.class);
                startActivity(intent);
            }
        });




    }
}