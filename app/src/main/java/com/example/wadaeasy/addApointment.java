package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addApointment extends AppCompatActivity {

    EditText txtname,txtContact,txtEmail,txtProvno,txtloc,txtDate,txtTime,txtRem;
    Button btnConfirm,btnCancel,show;
    DatabaseReference dbref,dbref2;
    Appointment ap1;
    confirmAppointment ca1;





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
        Intent newIntent=getIntent();
        String n1= newIntent.getStringExtra("Extra_message1");

        txtProvno.setText(n1);

        btnConfirm=findViewById(R.id.ap_confirm);



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ap1=new Appointment();
                ca1=new confirmAppointment();
                dbref= FirebaseDatabase.getInstance().getReference().child("Appointment");
                String uid;
                dbref2=FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
                try{
                    if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter a Name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtContact.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter a valid contact",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Email",Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtloc.getText().toString())) {
                        Toast.makeText(getBaseContext(),"please Enter Location",Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Date",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtTime.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Time",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtRem.getText().toString()))
                        Toast.makeText(getBaseContext(),"please Enter Remark",Toast.LENGTH_SHORT).show();
                    else{
                        ap1.setName(txtname.getText().toString().trim());
                        ap1.setContact(Integer.parseInt(txtContact.getText().toString().trim()));
                        ap1.setEmail(txtEmail.getText().toString().trim());
                        ap1.setLocation(txtloc.getText().toString().trim());
                        ap1.setDate(txtDate.getText().toString().trim());
                        ap1.setProvider_no(txtProvno.getText().toString().trim());
                        ap1.setTime(txtTime.getText().toString().trim());
                        ap1.setRemark(txtRem.getText().toString().trim());
                        ca1.setUid(txtProvno.getText().toString().trim());

                        dbref.push().setValue(ap1);
                        dbref2.push().setValue(ca1);

                        Toast.makeText(getBaseContext(),"Your Appointment has been confirmed",Toast.LENGTH_LONG).show();
                        ClearControls();
                        Intent aboutScreen = new Intent(getBaseContext(),view_appoint.class);
                        startActivity(aboutScreen);
                    }


                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"please enter valid format",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }


            }
        });







            }



            public void Cancel(View view){
        ClearControls();
        Intent intent=new Intent(this,view_appoint.class);
        startActivity(intent);
    }

    /*public void Dashboard(View view){
        Intent intent=new Intent(this,Dashboard.class);
        startActivity(intent);
    }*/

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