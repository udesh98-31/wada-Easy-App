package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Switch2 extends AppCompatActivity {

    Button serviceProvider;
    Button client;
    ImageView singout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch2);

        mAuth=FirebaseAuth.getInstance();


        serviceProvider =findViewById(R.id.ServiceProvider);
        client =findViewById(R.id.Client);
        singout=findViewById(R.id.singoutbt);
        serviceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch2.this, ServiceHome2.class);
                startActivity(intent);
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch2.this, addApointment.class);
                startActivity(intent);
            }
        });

        singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });
    }

    private void signOutUser() {

        Intent intent=new Intent(Switch2.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}