package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Request_confirm extends AppCompatActivity {

    TextView number;
    Button preview;
    Button manage;
    Button delete;
    DatabaseReference deldbref;

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
        delete=findViewById(R.id.delete);

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Request_confirm.this,RetriveRequestDetails.class);
                intent.putExtra("numbers",number1);
                startActivity(intent);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Request_confirm.this,updaterequest.class);
                intent.putExtra("numbers",number1);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //Alert
                AlertDialog.Builder builder = new AlertDialog.Builder(Request_confirm.this);
                builder.setMessage("Do You Want To Delete Your Request ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deldbref= FirebaseDatabase.getInstance().getReference().child("Client");
                                deldbref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChild(number1)){
                                            deldbref= FirebaseDatabase.getInstance().getReference().child("Client").child(number1);
                                            deldbref.removeValue();
                                            Toast.makeText(getBaseContext(), "Your Request has been Deleted", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(getBaseContext(), "No Source to delete", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            }

                        }).setNegativeButton("No", null);
                            AlertDialog alert = builder.create();
                            alert.show();

            }
        });

    }

    public void onCancle(View view){

        Intent intent = new Intent(this,MyRequest.class);
        startActivity(intent);
        Toast.makeText(getBaseContext(),  "You just click the button",Toast.LENGTH_SHORT).show();
    }
}