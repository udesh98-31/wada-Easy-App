package com.example.wadaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Request_confirm extends AppCompatActivity {

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
                Intent intent = new Intent(v.getContext(),UpdateRequest.class);
                intent.putExtra("numbers",number1);
                v.getContext().startActivity(intent);
            }
        });



    }

    public void onCancle(View view){

        Intent intent = new Intent(this,MyRequest.class);
        startActivity(intent);
        Toast.makeText(getBaseContext(),  "You just click the button",Toast.LENGTH_SHORT).show();
    }
}