package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_request);
        this.setTitle("වැඩ Easy -Request Service");
    }

    public void back(View view){

        Intent intent1 = new Intent(this, myrequestinsert.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }

    public void deleteConf(View view){

        Intent intent1 = new Intent(this,DeleteConfirmation.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_LONG).show();
    }
}