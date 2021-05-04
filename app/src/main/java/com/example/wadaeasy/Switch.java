package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Switch extends AppCompatActivity {
Button serviceProvider;
Button client;
DatabaseReference refdb;
FirebaseUser user;
String uid;
String uid2;
String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        user = FirebaseAuth.getInstance().getCurrentUser();




        serviceProvider =findViewById(R.id.ServiceProvider);
        client =findViewById(R.id.Client);
        serviceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch.this, ServiceHome2.class);
                Intent intent2 = new Intent(Switch.this, serviceHome.class);
                refdb = FirebaseDatabase.getInstance().getReference().child("Service");
                refdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(user.getUid()).exists()){
                            startActivity(intent);
                        }
                        else if(!(snapshot.child(user.getUid()).exists())){
                           startActivity(intent2);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch.this, clientHome.class);
                startActivity(intent);
            }
        });
    }
}