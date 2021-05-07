package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class ServiceView extends AppCompatActivity {
    Button main,home,ap,fd;
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
Button Update;
Button Delete;
ImageView imageView;
DatabaseReference imgdb;
DatabaseReference refdb;
boolean isopen;

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
        more = findViewById(R.id.moreInfo);


                refdb = FirebaseDatabase.getInstance().getReference().child("Service").child(uid);
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
                        String More = snapshot.child("infomation").getValue().toString();


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
                        more.setText(More);

                        imgdb =FirebaseDatabase.getInstance().getReference().child("ProfileImage").child(uid);
                        imageView=findViewById(R.id.imageView6);
                        imgdb.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String msg = snapshot.getValue(String.class);
                                Picasso.get().load(msg).into(imageView);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Update = findViewById(R.id.update);

                        Update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ServiceView.this, Update.class);
                                intent.putExtra("NAME", name.getText().toString());
                                intent.putExtra("AREA1", Area1.getText().toString());
                                intent.putExtra("AREA2", Area2.getText().toString());
                                intent.putExtra("QUALIFICATION", Qul.getText().toString());
                                intent.putExtra("CHARGE", ch.getText().toString());
                                intent.putExtra("STIME", Time1.getText().toString());
                                intent.putExtra("ETIME", Time2.getText().toString());
                                intent.putExtra("PHONE1", phone1.getText().toString());
                                intent.putExtra("PHONE2", phone2.getText().toString());
                                intent.putExtra("INFO", more.getText().toString());
                                intent.putExtra("DAY1", day1.getText().toString());
                                intent.putExtra("DAY2", day2.getText().toString());
                                intent.putExtra("DAY3", day3.getText().toString());
                                intent.putExtra("DAY4", day4.getText().toString());
                                intent.putExtra("DAY5", day5.getText().toString());
                                intent.putExtra("DAY6", day6.getText().toString());
                                intent.putExtra("DAY7", day7.getText().toString());

                                startActivity(intent);

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }


}