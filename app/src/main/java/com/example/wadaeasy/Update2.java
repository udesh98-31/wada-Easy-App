package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update2 extends AppCompatActivity {
    String name;
    String serviceType;
    String area1;
    String area2;
    String e_time;
    String s_time;
    String charge;
    String qul;
    String Day1,Day2,Day3,Day4,Day5,Day6,Day7;
    String more;
    String ph1;
    String ph2;
    EditText phone1;
    EditText phone2;
    CheckBox day1,day2,day3,day4,day5,day6,day7;
    EditText infomation;
    DatabaseReference dbref;
    Service ser;
    Button submit;
    FirebaseUser user;
    String uid;
    String test="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);

        this.setTitle("වැඩ Easy - Update Service");

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel("Notification","Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        Intent i = getIntent();
        e_time = i.getStringExtra("ETIME");
        name = i.getStringExtra("NAME");
        area1 = i.getStringExtra("AREA1");
        area2 = i.getStringExtra("AREA2");
        s_time = i.getStringExtra("STIME");
        charge = i.getStringExtra("CHARGE");
        qul = i.getStringExtra("QUALIFICATION");
        Day1 = i.getStringExtra("d1");
        Day2 = i.getStringExtra("d2");
        Day3 = i.getStringExtra("d3");
        Day4 = i.getStringExtra("d4");
        Day5 = i.getStringExtra("d5");
        Day6 = i.getStringExtra("d6");
        Day7 = i.getStringExtra("d7");
        more = i.getStringExtra("MORE");
        ph1 = i.getStringExtra("PHONE1");
        ph2 = i.getStringExtra("PHONE2");


        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.Phone2);
        day1 = findViewById(R.id.Monday);
        day2 = findViewById(R.id.Tuesday);
        day3 = findViewById(R.id.Wensday);
        day4 = findViewById(R.id.Thursday);
        day5 = findViewById(R.id.Friday);
        day6 = findViewById(R.id.Satuday);
        day7 = findViewById(R.id.Sunday);
        submit = findViewById(R.id.btnSubmit);
        infomation = findViewById(R.id.info);
        ser = new Service();
        dbref = FirebaseDatabase.getInstance().getReference().child("Service");

        phone1.setText(ph1);
        phone2.setText(ph2);
        infomation.setText(more);

        if(Day1.equals("Monday")){
            day1.setChecked(true);
        }
        if(Day2.equals("Tuesday")){
            day2.setChecked(true);
        }
        if(Day3.equals("Wensday")){
            day3.setChecked(true);
        }
        if(Day4.equals("Thrusday")){
            day4.setChecked(true);
        }
        if(Day5.equals("Friday")){
            day5.setChecked(true);
        }
        if(Day6.equals("Satuday")){
            day6.setChecked(true);
        }
        if(Day7.equals("Sunday")){
            day7.setChecked(true);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(com.example.wadaeasy.Update2.this, ServiceHome2.class);
                try {
                    if (!day1.isChecked()) {
                        if (!day2.isChecked()) {
                            if (!day3.isChecked()) {
                                if (!day4.isChecked()) {
                                    if (!day5.isChecked()) {
                                        if (!day6.isChecked()) {
                                            if (!day7.isChecked()) {
                                                Toast.makeText(getApplicationContext(), "Please Enter Working Days!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } else if (TextUtils.isEmpty(phone1.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Phone Number 1!", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(phone2.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Phone Number 2!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        dbref.child(uid).child("name").setValue(name);
                        dbref.child(uid).child("area1").setValue(area1);
                        dbref.child(uid).child("area2").setValue(area2);
                        dbref.child(uid).child("stime").setValue(s_time);
                        dbref.child(uid).child("etime").setValue(e_time);
                        dbref.child(uid).child("charge").setValue(charge);
                        dbref.child(uid).child("qualification").setValue(qul);
                        dbref.child(uid).child("infomation").setValue(infomation.getText().toString());
                        dbref.child(uid).child("phone1").setValue(phone1.getText().toString());
                        dbref.child(uid).child("phone2").setValue(phone2.getText().toString());

                        if (day1.isChecked()) {
                            dbref.child(uid).child("day1").setValue(day1.getText().toString());
                        }
                        if (day2.isChecked()) {
                            dbref.child(uid).child("day2").setValue(day2.getText().toString());
                        }
                        if (day3.isChecked()) {
                            dbref.child(uid).child("day3").setValue(day3.getText().toString());
                        }
                        if (day4.isChecked()) {
                            dbref.child(uid).child("day4").setValue(day4.getText().toString());
                        }
                        if (day5.isChecked()) {
                            dbref.child(uid).child("day5").setValue(day5.getText().toString());
                        }
                        if (day6.isChecked()) {
                            dbref.child(uid).child("day6").setValue(day6.getText().toString());
                        }
                        if (day7.isChecked()) {
                            dbref.child(uid).child("day7").setValue(day7.getText().toString());
                        }

                        Toast.makeText(getApplicationContext(), "Service Update Successfully !", Toast.LENGTH_SHORT).show();

                        startActivity(intent);

                        String message ="Your Service is Updated!";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Update2.this,"Notification");
                                builder.setSmallIcon(R.drawable.update_24);
                                builder.setContentTitle("Service Update");
                                builder.setContentText(message);
                                builder.setAutoCancel(true);

                        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(Update2.this);
                        managerCompat.notify(1,builder.build());

                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}