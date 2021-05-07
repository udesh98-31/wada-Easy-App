package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppoimentVIewS extends AppCompatActivity {
    TextView Location,Date,Time,Contact,Remark,Name;
    Button Confirmbtn,cancelbtn;
    ImageView homebtn,appoimentbtn,backbtn;
    DatabaseReference refdb;
    FirebaseUser user;
    String uid;
    Appointment apmt;
    private String contact1,location1,name1,time1,Date1,remark1,apno1,statu1,proid,email1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoiment_v_iew_s);
        this.setTitle("වැඩ Easy-View Appoiment");

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        homebtn=findViewById(R.id.homebt);

        Location=findViewById(R.id.lcationtxt);
        Date=findViewById(R.id.datetxt);
        Time=findViewById(R.id.timetxt);
        Contact=findViewById(R.id.phonetxt);
        Remark=findViewById(R.id.remarktxt);
        Name=findViewById(R.id.nametxt);
        Confirmbtn=findViewById(R.id.conapobt);
        cancelbtn=findViewById(R.id.cancelbt);
        apmt=new Appointment();


                refdb= FirebaseDatabase.getInstance().getReference().child("Confirm Appointment").child(uid);
                refdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        apno1=snapshot.child("appointNo").getValue().toString();
                        contact1=snapshot.child("contact").getValue().toString();
                        Date1=snapshot.child("date").getValue().toString();
                        email1=snapshot.child("email").getValue().toString();
                        location1=snapshot.child("location").getValue().toString();
                        name1=snapshot.child("name").getValue().toString();
                        statu1=snapshot.child("status").getValue().toString();
                        time1=snapshot.child("time").getValue().toString();
                        proid=snapshot.child("uid").getValue().toString();
                        remark1=snapshot.child("remark").getValue().toString();



                        Location.setText(location1);
                        Date.setText(Date1);
                        Time.setText(time1);
                        Contact.setText(contact1);
                        Remark.setText(remark1);
                        Name.setText(statu1);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        Confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AppoimentVIewS.this);
                builder.setMessage("Are You Sure Confirm this Appoiment?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference uprefdb=FirebaseDatabase.getInstance().getReference().child("Appointment");
                                uprefdb.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChild(apno1)){
                                            String Status="Confirm";


                                            apmt.setContact(Integer.parseInt(contact1));
                                            apmt.setDate(Date1);
                                            apmt.setEmail(email1);
                                            apmt.setLocation(location1);
                                            apmt.setName(name1);
                                            apmt.setProvider_no(proid);
                                            apmt.setRemark(remark1);
                                            apmt.setStatus(Status);
                                            apmt.setTime(time1);

                                            refdb=FirebaseDatabase.getInstance().getReference().child("Appointment").child(apno1);
                                            refdb.setValue(apmt);

                                            //delete appoiment from confirm table
                                            DatabaseReference delref=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
                                            delref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if(snapshot.hasChild(uid)){
                                                        refdb=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment").child(uid);
                                                        refdb.removeValue();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });

                                            Toast.makeText(AppoimentVIewS.this, "Sucsessful", Toast.LENGTH_SHORT).show();



                                        }
                                        else {
                                            Toast.makeText(AppoimentVIewS.this, "unsucsess", Toast.LENGTH_SHORT).show();


                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



                            }
                        }).setNegativeButton("No", null);
                          AlertDialog alert = builder.create();
                          alert.show();


            }
        });



                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AppoimentVIewS.this);
                        builder.setMessage("Are You Sure Cancel this Appoiment?")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseReference uprefdb=FirebaseDatabase.getInstance().getReference().child("Appointment");
                                        uprefdb.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.hasChild(apno1)){
                                                    String Status="Service provider cancel Appoiment";


                                                    apmt.setContact(Integer.parseInt(contact1));
                                                    apmt.setDate(Date1);
                                                    apmt.setEmail(name1);
                                                    apmt.setLocation(location1);
                                                    apmt.setName(name1);
                                                    apmt.setProvider_no(proid);
                                                    apmt.setRemark(remark1);
                                                    apmt.setStatus(Status);
                                                    apmt.setTime(time1);

                                                    refdb=FirebaseDatabase.getInstance().getReference().child("Appointment").child(apno1);
                                                    refdb.setValue(apmt);

                                                    //delete appoiment from confirm table

                                                    DatabaseReference delref=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
                                                    delref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.hasChild(uid)){
                                                                refdb=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment").child(uid);
                                                                refdb.removeValue();
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });

                                                    Toast.makeText(AppoimentVIewS.this, "Successfully Cancel", Toast.LENGTH_SHORT).show();



                                                }
                                                else {
                                                    Toast.makeText(AppoimentVIewS.this, "Unsuccessfully", Toast.LENGTH_SHORT).show();


                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });




                                    }
                                }).setNegativeButton("No", null);
                                   AlertDialog alert = builder.create();
                                   alert.show();


                    }
                });


                homebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(AppoimentVIewS.this,ServiceHome2.class);
                        startActivity(intent);


                    }
                });






    }


}