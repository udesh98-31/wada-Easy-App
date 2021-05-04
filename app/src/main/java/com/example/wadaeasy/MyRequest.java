package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequestinsert);
        this.setTitle("වැඩ Easy -Request Service");
    }

    public void onPreview(View view){

        Intent intent = new Intent(this,RetriveRequestDetails.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }
    public void onUpdate(View view){

        Intent intent = new Intent(this,UpdateRequest.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }

    public void onDelete(View view){

        Intent intent = new Intent(this,DeleteRequest.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }

}