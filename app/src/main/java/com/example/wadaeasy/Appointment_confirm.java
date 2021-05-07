package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Appointment_confirm extends AppCompatActivity {

    TextView number;
    Button preview;
    Button manage;
    Button delete;
    DatabaseReference delref;
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
        delete=findViewById(R.id.delete);

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Appointment_confirm.this,view_appoint.class);
                intents.putExtra("numbers",number1);
                startActivity(intents);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(Appointment_confirm.this,Edit_appoin.class);
                intents.putExtra("numbers",number1);
                startActivity(intents);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delref= FirebaseDatabase.getInstance().getReference().child("Appointment");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(number1)){
                            delref=FirebaseDatabase.getInstance().getReference().child("Appointment").child(number1);
                            delref.removeValue();
                            Intent intent2 = new Intent(Appointment_confirm.this,ClientDashboard.class);

                            startActivity(intent2);
                            Toast.makeText(getBaseContext(), "Your Appointment has been Deleted", Toast.LENGTH_LONG).show();
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
        });





    }
}