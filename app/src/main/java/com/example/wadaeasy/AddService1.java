package com.example.wadaeasy;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddService1 extends AppCompatActivity{


Button Next;
EditText name;
EditText area1;
EditText area2;
EditText charge;
EditText Qualification;
Spinner Spinner1;
TextView stime;
TextView etime;
int hr,min;
Button main,home,ap,fd;
boolean isopen;
ImageView homebtnd,backbtnd,appoinmentbtd,addbtnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service1);
        this.setTitle("වැඩ Easy - Add Service");


        //drop down menu
        Spinner service = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(com.example.wadaeasy.AddService1.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.service));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service.setAdapter(myadapter);

        //Navigation bar

        homebtnd = findViewById(R.id.homebtd);
        backbtnd = findViewById(R.id.backbtd);
        appoinmentbtd = findViewById(R.id.appoimentbtd);
        addbtnd =findViewById(R.id.addserd);

        //inputs and submit
        Next =findViewById(R.id.next);
        etime=findViewById(R.id.end);
        stime=findViewById(R.id.start);
        name = findViewById(R.id.Name);
        area1 = findViewById(R.id.Area1);
        area2 = findViewById(R.id.Area2);
        charge = findViewById(R.id.Charge);
        Spinner1 = findViewById(R.id.spinner1);
        Qualification = findViewById(R.id.Qulification);



        //working time
        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        com.example.wadaeasy.AddService1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hr = hourOfDay;
                        min = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, hr, min);

                        etime.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                },12,0,false
                );
                timePickerDialog.updateTime(hr,min);
                timePickerDialog.show();
            }
        });

        //working time
        stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        com.example.wadaeasy.AddService1.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hr = hourOfDay;
                        min = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, hr, min);

                        stime.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                },12,0,false
                );
                timePickerDialog.updateTime(hr,min);
                timePickerDialog.show();
            }
        });
        //Data send for next page
        Next.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        try {
                            if(TextUtils.isEmpty(name.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"Please Enter Name!",Toast.LENGTH_SHORT).show();
                            }
                            else if(TextUtils.isEmpty(area1.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"Please Enter Area",Toast.LENGTH_SHORT).show();
                            }
                            else if(TextUtils.isEmpty(charge.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"Please Enter Charges!",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent = new Intent(com.example.wadaeasy.AddService1.this, AddService2.class);
                                intent.putExtra("NAME", name.getText().toString());
                                intent.putExtra("AREA1", area1.getText().toString());
                                intent.putExtra("AREA2", area2.getText().toString());
                                intent.putExtra("QUALIFICATION", Qualification.getText().toString());
                                intent.putExtra("CHARGE", charge.getText().toString());
                                intent.putExtra("SPINNER1", Spinner1.getSelectedItem().toString());
                                intent.putExtra("STIME", stime.getText().toString());
                                intent.putExtra("ETIME", etime.getText().toString());
                                startActivity(intent);
                                clear();
                            }
                        }catch (NumberFormatException e)
                        {
                            Toast.makeText(getApplicationContext(),"Invalid!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        
        //Navigation Bar
        homebtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AddService1.this,ServiceHome2.class);
                startActivity(intent);


            }
        });


        appoinmentbtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AddService1.this,AppoimentVIewS.class);
                startActivity(intent);


            }
        });
        backbtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AddService1.this,ServiceHome2.class);
                startActivity(intent);


            }
        });
        addbtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AddService1.this,ServiceView.class);
                startActivity(intent);


            }
        });


    }
    //Clear Controls
    private void  clear(){
        name.setText("");
        area1.setText("");
        area2.setText("");
        charge.setText("");
        Qualification.setText("");

    }

}



