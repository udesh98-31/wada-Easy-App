package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceView extends AppCompatActivity {

TextView name;
TextView Service;
TextView Area1;
TextView Area2;
TextView Time1;
TextView Time2;
TextView Qul;
TextView day1,day2,day3,day4,day5,day6,day7;
TextView more;
TextView phone1,phone2;
TextView ch;
FirebaseUser user;
String uid;

Button view;
DatabaseReference refdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);
        this.setTitle("වැඩ Easy - My Service");

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        name = findViewById(R.id.Nameview);
        Service=findViewById(R.id.Servicetype);
        Area1 = findViewById(R.id.Areview1);
        Area2 = findViewById(R.id.Areaview2);
        Time1 = findViewById(R.id.StimeView);
        Time2 = findViewById(R.id.EtimeView);
        Qul = findViewById(R.id.qul);
        day1 = findViewById(R.id.d1);
        day2 = findViewById(R.id.d2);
        day3 = findViewById(R.id.d3);
        day4 = findViewById(R.id.d4);
        day5 = findViewById(R.id.d5);
        day6 = findViewById(R.id.d6);
        day7 = findViewById(R.id.d7);
        phone1 =findViewById(R.id.ph1);
        phone2 =findViewById(R.id.ph2);
        ch =findViewById(R.id.chargeView);


                refdb = FirebaseDatabase.getInstance().getReference().child("Service").child("1");
                refdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name2 = snapshot.child("name").getValue().toString();
                        String ser = snapshot.child("service").getValue().toString();
                        String ar1 = snapshot.child("area1").getValue().toString();
                        String ar2 = snapshot.child("area2").getValue().toString();
                        String tim1 =snapshot.child("stime").getValue().toString();
                        String tim2 =snapshot.child("etime").getValue().toString();
                        String qul =snapshot.child("qualification").getValue().toString();
                        String dy1=snapshot.child("day1").getValue().toString();
                        String dy2=snapshot.child("day2").getValue().toString();
                        String dy3=snapshot.child("day3").getValue().toString();
                        String dy4=snapshot.child("day4").getValue().toString();
                        String dy5=snapshot.child("day5").getValue().toString();
                        String dy6=snapshot.child("day6").getValue().toString();
                        String dy7=snapshot.child("day7").getValue().toString();
                        String p1 =snapshot.child("phone1").getValue().toString();
                        String p2 = snapshot.child("phone2").getValue().toString();
                        String c = snapshot.child("charge").getValue().toString();



                        day1.setText(dy1);
                        day2.setText(dy2);
                        day3.setText(dy3);
                        day4.setText(dy4);
                        day5.setText(dy5);
                        day6.setText(dy6);
                        day7.setText(dy7);
                        name.setText(name2);
                        Service.setText(ser);
                        Area1.setText(ar1);
                        Area2.setText(ar2);
                        Time1.setText(tim1);
                        Time2.setText(tim2);
                        Qul.setText(qul);
                        phone1.setText(p1);
                        phone2.setText(p2);
                        ch.setText(c);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


}