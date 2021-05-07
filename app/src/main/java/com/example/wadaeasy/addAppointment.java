package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class addAppointment extends AppCompatActivity {

    EditText txtname,txtContact,txtEmail,txtProvno,txtloc,txtDate,txtTime,txtRem;
    Button btnConfirm,btnCancel,show;
    DatabaseReference dbref,dbref2;
    Appointment ap1;
    confirmAppointment ca1;

    long maxid=0;
    String ap_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        this.setTitle("වැඩ Easy -Add Appointment");

        txtname=findViewById(R.id.client_name);
        txtContact=findViewById(R.id.client_Phone);
        txtEmail=findViewById(R.id.client_email);
        txtProvno=findViewById(R.id.prov_no);
        txtloc=findViewById(R.id.location);
        txtDate=findViewById(R.id.App_Date);
        txtTime=findViewById(R.id.ap_Time);
        txtRem=findViewById(R.id.remark);

        //get the providor number from his profile
        //Intent newIntent=getIntent();
        //String n1= newIntent.getStringExtra("Extra_message1");

        //txtProvno.setText(n1);

        ap1= new Appointment();
        ca1=new confirmAppointment();

        btnConfirm=findViewById(R.id.ap_confirm);
        btnCancel=findViewById(R.id.ap_cancel);
        dbref= FirebaseDatabase.getInstance().getReference().child("Appointment");
        dbref2=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    //check if the views are null
                    if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter a Name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtContact.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter a valid contact",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Email",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtloc.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Location",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Date",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtTime.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Time",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtRem.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Remark",Toast.LENGTH_SHORT).show();
                    else{

                        //insert into appointment table
                        ap1.setName(txtname.getText().toString().trim());
                        ap1.setContact(Integer.parseInt(txtContact.getText().toString().trim()));
                        ap1.setEmail(txtEmail.getText().toString().trim());
                        ap1.setLocation(txtloc.getText().toString().trim());
                        ap1.setDate(txtDate.getText().toString().trim());
                        ap1.setProvider_no(txtProvno.getText().toString().trim());
                        ap1.setTime(txtTime.getText().toString().trim());
                        ap1.setRemark(txtRem.getText().toString().trim());
                        ap1.setStatus("Pending");

                        //insert into confirm appointment table
                        ca1.setUid(txtProvno.getText().toString().trim());
                        ca1.setName(txtname.getText().toString().trim());
                        ca1.setContact(Integer.parseInt(txtContact.getText().toString().trim()));
                        ca1.setEmail(txtEmail.getText().toString().trim());
                        ca1.setLocation(txtloc.getText().toString().trim());
                        ca1.setDate(txtDate.getText().toString().trim());
                        ca1.setStatus("Pending");
                        ca1.setAppointNo(Long.parseLong(String.valueOf(maxid+1)));
                        ca1.setTime(txtTime.getText().toString().trim());
                        ca1.setRemark(txtRem.getText().toString().trim());


                        dbref.child(String.valueOf(maxid+1)).setValue(ap1);
                        //service provider id
                        dbref2.child(String.valueOf("hwWcQO0BD9ftY4cVeOI5lrYnzaq1")).setValue(ca1);

                        Toast.makeText(getBaseContext(),"Your Appointment has been confirmed",Toast.LENGTH_LONG).show();

                        ap_no=String.valueOf(maxid+1);

                        Intent intent = new Intent(v.getContext(),Appointment_confirm.class);
                        intent.putExtra("number",ap_no);
                        v.getContext().startActivity(intent);
                    }


                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"please enter valid format",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearControls();
                Intent intentback=new Intent(addAppointment.this,ClientDashboard.class);
                startActivity(intentback);
            }
        });








            }

            public void ClearControls(){
        txtname.setText(" ");
        txtContact.setText(" ");
        txtDate.setText(" ");
        txtEmail.setText(" ");
        txtloc.setText(" ");
        txtProvno.setText(" ");
        txtRem.setText(" ");
        txtTime.setText(" ");
    }
}