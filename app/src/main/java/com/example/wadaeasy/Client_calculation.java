package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Client_calculation extends AppCompatActivity {

    Button cal;
    DatabaseReference dbreff;
    FirebaseAuth mAuth;
    String currentUserId;
    Client client;

    int countClients = 0;
    String req_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_calculation);
        this.setTitle("වැඩ Easy -Calculate Total Clients");

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        cal = findViewById(R.id.cl_calculate);
        dbreff = FirebaseDatabase.getInstance().getReference().child("Client");

        dbreff.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    countClients = (int) snapshot.getChildrenCount();
                    Client.setText(Integer.toString(countClients )+ "  Clients");
                }
                else{

                    Client.setText("No clients");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}