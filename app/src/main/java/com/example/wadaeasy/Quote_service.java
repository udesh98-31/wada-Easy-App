package com.example.wadaeasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Quote_service extends AppCompatActivity {

    String chrage,hrs;
    Button Calculetebtn;
    EditText hrstext;
    int charge1,Results,hr;
   // quo q1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_service);

        //q1=new  quo();
        Calculetebtn=findViewById(R.id.Calculetebt);
        hrstext=(EditText) findViewById(R.id.hrstext);








        ;

        Calculetebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hrs=hrstext.getText().toString();
                hr=Integer.parseInt(hrs);
                int defaultValue = -1;
                Intent intent=getIntent();
                charge1=intent.getIntExtra("Int",defaultValue);

                Results=(charge1*hr);

                chrage=String.valueOf(Results);



                AlertDialog.Builder builder=new AlertDialog.Builder(Quote_service.this);
                builder.setMessage("Charge is Rs:"+chrage+".00")
                        .setPositiveButton("OK", null);
                          AlertDialog alert = builder.create();
                          alert.show();



            }
        });
    }
}