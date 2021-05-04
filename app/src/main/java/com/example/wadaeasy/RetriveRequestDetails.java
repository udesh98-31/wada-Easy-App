package com.example.wadaeasy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RetriveRequestDetails extends AppCompatActivity {


    TextView txtname, txtlocation, txtcategory, txtdate, txtphone, txtservicetype;
    Button buttonview;
    DatabaseReference dbreff;
    Client client;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_request_details);
        this.setTitle("වැඩ Easy -Request Service");

        txtname = findViewById(R.id.nameview);
        txtlocation = findViewById(R.id.locationview);
        txtcategory =findViewById(R.id.categoryview);
        txtdate =findViewById(R.id.dateview);
        txtphone = findViewById(R.id.phoneview);
        txtservicetype = findViewById(R.id.spview);

        buttonview = findViewById(R.id.btnview);

        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbreff = FirebaseDatabase.getInstance().getReference().child("Client").child("1");
                dbreff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String name = snapshot.child("name").getValue().toString();
                        String location = snapshot.child("location").getValue().toString();
                        String category = snapshot.child("category").getValue().toString();
                        String date = snapshot.child("date").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();
                        String serviceType = snapshot.child("serviceType").getValue().toString();

                        txtname.setText(name);
                        txtlocation.setText(location);
                        txtcategory.setText(category);
                        txtdate.setText(date);
                        txtphone.setText(phone);
                        txtservicetype.setText(serviceType);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }


}