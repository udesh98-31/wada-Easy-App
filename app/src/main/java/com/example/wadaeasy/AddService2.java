package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddService2 extends AppCompatActivity {

String name;
String serviceType;
String area1;
String area2;
String e_time;
String s_time;
String charge;
String qul;
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
        setContentView(R.layout.activity_add_service2);
        this.setTitle("වැඩ Easy - Add Service");

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        Intent i = getIntent();
        e_time = i.getStringExtra("ETIME");
        name = i.getStringExtra("NAME");
        serviceType = i.getStringExtra("SPINNER1");
        area1 = i.getStringExtra("AREA1");
        area2 = i.getStringExtra("AREA2");
        s_time = i.getStringExtra("STIME");
        charge = i.getStringExtra("CHARGE");
        qul = i.getStringExtra("QUALIFICATION");


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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(com.example.wadaeasy.AddService2.this, ServiceHome2.class);
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

                        ser.setName(name.trim());
                        ser.setService(serviceType.trim());
                        ser.setStime(s_time.trim());
                        ser.setEtime(e_time.trim());
                        ser.setQualification(qul.trim());
                        ser.setArea1(area1.trim());
                        ser.setArea2(area2.trim());
                        ser.setCharge(Integer.parseInt(charge.trim()));
                        ser.setInfomation(infomation.getText().toString().trim());
                        ser.setPhone1(Integer.parseInt(phone1.getText().toString().trim()));
                        ser.setPhone2(Integer.parseInt(phone2.getText().toString().trim()));
                        ser.setUid(uid.trim());

                        if (day1.isChecked()) {
                            ser.setDay1(day1.getText().toString().trim());
                        }
                        else{
                            ser.setDay1(test.trim());
                        }
                        if (day2.isChecked()) {
                            ser.setDay2(day2.getText().toString().trim());
                        }
                        else{
                            ser.setDay2(test.trim());
                        }
                        if (day3.isChecked()) {
                            ser.setDay3(day3.getText().toString().trim());
                        }
                        else {
                            ser.setDay3(test.trim());
                        }
                        if (day4.isChecked()) {
                            ser.setDay4(day4.getText().toString().trim());
                        }
                        else{
                            ser.setDay4(test.trim());
                        }
                        if (day5.isChecked()) {
                            ser.setDay5(day5.getText().toString().trim());
                        }
                        else {
                            ser.setDay5(test.trim());
                        }
                        if (day6.isChecked()) {
                            ser.setDay6(day6.getText().toString().trim());
                        }
                        else {
                            ser.setDay6(test.trim());
                        }
                        if (day7.isChecked()) {
                            ser.setDay7(day7.getText().toString().trim());
                        }
                        else {
                            ser.setDay7(test.trim());
                        }


                        dbref.child(String.valueOf(uid)).setValue(ser);

                        Toast.makeText(getApplicationContext(), "Service Added Successfully !", Toast.LENGTH_SHORT).show();
                        clear();
                        startActivity(intent);
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void clear(){
        day1.setChecked(false);
        day2.setChecked(false);
        day3.setChecked(false);
        day4.setChecked(false);
        day5.setChecked(false);
        day6.setChecked(false);
        day7.setChecked(false);
        phone1.setText("");
        phone2.setText("");
        infomation.setText("");
    }
}