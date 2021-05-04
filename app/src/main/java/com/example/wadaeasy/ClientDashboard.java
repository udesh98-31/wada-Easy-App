package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDashboard extends AppCompatActivity {
    Button addRequest, addApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);
        this.setTitle("වැඩ Easy ");
        addRequest = findViewById(R.id.button13);
        addApp = findViewById(R.id.button11);

        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDashboard.this, myrequestinsert.class);
                startActivity(intent);
            }
        });
        addApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDashboard.this, ServiceView.class);
                startActivity(intent);
            }
        });
    }

}