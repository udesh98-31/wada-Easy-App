package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateConfirmation extends AppCompatActivity {

    Button btncancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_confirmation);

    }

    public void btncancle(View view){

        Intent intent1 = new Intent(this, myrequestinsert.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }
}