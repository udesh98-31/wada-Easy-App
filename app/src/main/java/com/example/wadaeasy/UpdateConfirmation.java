package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class UpdateConfirmation extends AppCompatActivity {

    TextView number;
    Button preview;
    Button manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirm);


        Intent intent=getIntent();
        String number1=intent.getStringExtra("number");

        number=findViewById(R.id.rcid);
        number.setText(number1);

        preview=findViewById(R.id.view);
        manage=findViewById(R.id.edit);

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RetriveRequestDetails.class);
                intent.putExtra("numbers",number1);
                v.getContext().startActivity(intent);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),updaterequest.class);
                intent.putExtra("numbers",number1);
                v.getContext().startActivity(intent);
            }
        });



    }

}