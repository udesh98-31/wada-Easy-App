package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Appointment_confirm extends AppCompatActivity {

    TextView number;
    Button preview;
    Button manage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirm);

        Intent intent=getIntent();
        String number1=intent.getStringExtra("number");

        number=findViewById(R.id.ap_no);
        number.setText(number1);

        preview=findViewById(R.id.view);
        manage=findViewById(R.id.manage);

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), view_appoint.class);
                intent.putExtra("numbers",number1);
                v.getContext().startService(intent);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(),Edit_appoin.class);
                intent2.putExtra("numbers",number1);
                v.getContext().startService(intent2);
            }
        });





    }
}