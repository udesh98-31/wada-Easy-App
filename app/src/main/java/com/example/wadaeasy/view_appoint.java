package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_appoint extends AppCompatActivity {

    DatabaseReference dbref;
    TextView name,contact,email,location,date,time,remark,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appoint);
        this.setTitle("වැඩ Easy -View");


        Intent aboutScreen2= getIntent();
        String ap1= aboutScreen2.getStringExtra("numbers");

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact1);
        email=findViewById(R.id.email);
        location=findViewById(R.id.loc);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        remark=findViewById(R.id.rem);
        status=findViewById(R.id.status);





        dbref = FirebaseDatabase.getInstance().getReference().child("Appointment").child(ap1);
        dbref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1=snapshot.child("name").getValue().toString();
                String contact1=snapshot.child("contact").getValue().toString();
                String email1=snapshot.child("email").getValue().toString();
                String location1=snapshot.child("location").getValue().toString();
                String date1=snapshot.child("date").getValue().toString();
                String time1=snapshot.child("time").getValue().toString();
                String remark1=snapshot.child("remark").getValue().toString();
                String providerNo=snapshot.child("provider_no").getValue().toString();
                String status1=snapshot.child("status").getValue().toString();

                name.setText(name1);
                contact.setText(contact1);
                email.setText(email1);
                location.setText(location1);
                date.setText(date1);
                time.setText(time1);
                remark.setText(remark1);
               status.setText(status1);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}