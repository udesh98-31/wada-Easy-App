package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class ServiceHome2 extends AppCompatActivity {
Button myService;
Button appoiment;
Button Request;
Button Feedback;
ImageView signout;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home2);
        this.setTitle("වැඩ Easy - Home");

        mAuth=FirebaseAuth.getInstance();

        myService = findViewById(R.id.AddService);
        appoiment=findViewById(R.id.Appoiments);
        Request=findViewById(R.id.Request);
        Feedback=findViewById(R.id.FeedBack);
        signout=findViewById(R.id.logoutbt);

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

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(ServiceHome2.this,FeedbackView.class);
                startActivity(intent2);

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });




    }

    private void signOutUser() {
        Intent intent=new Intent(ServiceHome2.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}