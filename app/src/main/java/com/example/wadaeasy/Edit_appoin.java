package com.example.wadaeasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_appoin extends AppCompatActivity {


    EditText name,contact,location,remark,date ,time,email;
    TextView pr_no1,status1;
    Button Edit,back;
    DatabaseReference dbref,dbref2;
    Appointment ap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appoin);
        this.setTitle("වැඩ Easy -Edit Appointment");
        ap = new Appointment();


        Intent aboutScreen1 = getIntent();
        String a1 = aboutScreen1.getStringExtra("numbers");


        name = findViewById(R.id.edtname);
        contact = findViewById(R.id.editphone);
        remark = findViewById(R.id.edtRem);
        location = findViewById(R.id.edtLoc);
        date = findViewById(R.id.editdate);
        time = findViewById(R.id.edtTime);
        email = findViewById(R.id.edtEmail);
        status1 = findViewById(R.id.edtstatus);
        pr_no1 = findViewById(R.id.edtpr_no);


        Edit = findViewById(R.id.edit_btn);
        back = findViewById(R.id.cancel_editt);


        dbref = FirebaseDatabase.getInstance().getReference().child("Appointment").child(a1);
        dbref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.child("name").getValue().toString();
                String contact1 = snapshot.child("contact").getValue().toString();
                String location1 = snapshot.child("location").getValue().toString();
                String date1 = snapshot.child("date").getValue().toString();
                String time1 = snapshot.child("time").getValue().toString();
                String remark1 = snapshot.child("remark").getValue().toString();
                String statusA = snapshot.child("status").getValue().toString();
                //only retriving these values not updating we need to push it back else record gets altered
                String email1 = snapshot.child("email").getValue().toString();

                String prov_no1 = snapshot.child("provider_no").getValue().toString();

                name.setText(name1);
                contact.setText(contact1);
                email.setText(email1);
                location.setText(location1);
                date.setText(date1);
                time.setText(time1);
                remark.setText(remark1);
                status1.setText(statusA);
                pr_no1.setText(prov_no1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Edit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //Alert
                AlertDialog.Builder builder = new AlertDialog.Builder(Edit_appoin.this);
                builder.setMessage("Do You Want To Update Appointment ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                dbref2 = FirebaseDatabase.getInstance().getReference().child("Appointment");
                                dbref2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if (snapshot.hasChild(a1)) {
                                            try {
                                                ap.setName(name.getText().toString().trim());
                                                ap.setContact(Integer.parseInt(contact.getText().toString().trim()));
                                                ap.setLocation(location.getText().toString().trim());
                                                ap.setDate(date.getText().toString().trim());
                                                ap.setTime(time.getText().toString().trim());
                                                ap.setRemark(remark.getText().toString().trim());
                                                ap.setEmail(email.getText().toString().trim());
                                                ap.setStatus(status1.getText().toString().trim());
                                                ap.setProvider_no(pr_no1.getText().toString().trim());


                                                dbref2.child(a1).setValue(ap);
                                                ClearControls();
                                                Toast.makeText(getBaseContext(), "Your Appointment has been Updated", Toast.LENGTH_LONG).show();


                                            } catch (NumberFormatException e) {
                                                Toast.makeText(getBaseContext(), "Enter Valid Format", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(getBaseContext(), "No Source to update", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                builder.setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClearControls();
                Intent intentCancel = new Intent(Edit_appoin.this, Appointment_confirm.class);
                intentCancel.putExtra("number", a1);
                startActivity(intentCancel);


            }
        });

    }









    public void ClearControls(){
        name.setText(" ");
        contact.setText(" ");
        date.setText(" ");
        email.setText(" ");
        location.setText(" ");
        pr_no1.setText(" ");
        remark.setText(" ");
        time.setText(" ");
    }
}