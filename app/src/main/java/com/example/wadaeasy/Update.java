package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Update extends AppCompatActivity {
    String name;
    String area1;
    String area2;
    String e_time;
    String s_time;
    String charge;
    String qul;
    String day1,day2,day3,day4,day5,day6,day7;
    String ph1,ph2;
    String more;

    Button Next;
    EditText Name;
    EditText Area1;
    EditText Area2;
    EditText Charge;
    EditText Qualification;
    TextView stime;
    TextView etime;
    int hr,min;
    Button main,home,ap,fd;
    boolean isopen;

    ImageView homebtnd,backbtnd,appoinmentbtd,addbtnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //navigation bar
        homebtnd = findViewById(R.id.homebtd);
        backbtnd = findViewById(R.id.backbtd);
        appoinmentbtd = findViewById(R.id.appoimentbtd);
        addbtnd =findViewById(R.id.addserd);




        Intent i = getIntent();
        name = i.getStringExtra("NAME");
        area1 = i.getStringExtra("AREA1");
        area2 = i.getStringExtra("AREA2");
        qul = i.getStringExtra("QUALIFICATION");
        charge = i.getStringExtra("CHARGE");
        ph1 = i.getStringExtra("PHONE1");
        ph2 = i.getStringExtra("PHONE2");
        s_time = i.getStringExtra("STIME");
        e_time = i.getStringExtra("ETIME");
        day1 = i.getStringExtra("DAY1");
        day2 = i.getStringExtra("DAY2");
        day3 = i.getStringExtra("DAY3");
        day4 = i.getStringExtra("DAY4");
        day5 = i.getStringExtra("DAY5");
        day6 = i.getStringExtra("DAY6");
        day7 = i.getStringExtra("DAY7");
        more = i.getStringExtra("INFO");

        Next =findViewById(R.id.next);
        etime=findViewById(R.id.end);
        stime=findViewById(R.id.start);
        Name = findViewById(R.id.Name);
        Area1 = findViewById(R.id.Area1);
        Area2 = findViewById(R.id.Area2);
        Charge = findViewById(R.id.Charge);
        Qualification = findViewById(R.id.Qulification);

        Name.setText(name);
        Area1.setText(area1);
        Area2.setText(area2);
        etime.setText(e_time);
        stime.setText(s_time);
        Charge.setText(charge);
        Qualification.setText(qul);

        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Update.this, new TimePickerDialog.OnTimeSetListener() {
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

        stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Update.this, new TimePickerDialog.OnTimeSetListener() {
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

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(TextUtils.isEmpty(Name.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Name!",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(Area1.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Area",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(Charge.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Charges!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(Update.this, Update2.class);
                        intent.putExtra("NAME", Name.getText().toString());
                        intent.putExtra("AREA1", Area1.getText().toString());
                        intent.putExtra("AREA2", Area2.getText().toString());
                        intent.putExtra("QUALIFICATION", Qualification.getText().toString());
                        intent.putExtra("CHARGE", Charge.getText().toString());
                        intent.putExtra("STIME", stime.getText().toString());
                        intent.putExtra("ETIME", etime.getText().toString());
                        intent.putExtra("d1", day1);
                        intent.putExtra("d2", day2);
                        intent.putExtra("d3", day3);
                        intent.putExtra("d4", day4);
                        intent.putExtra("d5", day5);
                        intent.putExtra("d6", day6);
                        intent.putExtra("d7", day7);
                        intent.putExtra("p1", ph1);
                        intent.putExtra("p2", ph2);
                        intent.putExtra("MORE", more);
                        intent.putExtra("PHONE1",ph1);
                        intent.putExtra("PHONE2",ph2);
                        startActivity(intent);
                    }
                }catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"Invalid!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        homebtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Update.this,ServiceHome2.class);
                startActivity(intent);


            }
        });


        appoinmentbtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Update.this,AppoimentVIewS.class);
                startActivity(intent);


            }
        });
        backbtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Update.this,ServiceHome2.class);
                startActivity(intent);


            }
        });
        addbtnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Update.this,ServiceView.class);
                startActivity(intent);


            }
        });

    }
}