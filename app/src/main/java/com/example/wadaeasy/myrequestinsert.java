package com.example.wadaeasy;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class myrequestinsert extends AppCompatActivity {

    //Add a date picker
    TextView tvDate;
    DatePickerDialog.OnDateSetListener setListener;

    //Initialize variable
    EditText txtname, txtlocation, txtcategory, txtdate, txtphone, txtservicetype;
    Button btnconfirm, btncancle;
    DatabaseReference dbreff;
    Client client;

    long maxid = 0;
    String req_no;


    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequestinsert);
        this.setTitle("වැඩ Easy -Request Service");


        //Assign Variables
        txtname = findViewById(R.id.cl_Name);
        txtlocation = findViewById(R.id.cl_Location);
        txtcategory =findViewById(R.id.s_Category);
        txtdate =findViewById(R.id.cl_date);
        txtphone = findViewById(R.id.client_Phone);
        txtservicetype = findViewById(R.id.cl_service);




        btncancle =findViewById(R.id.rs_cancle);
        btnconfirm =findViewById(R.id.edit);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for name
        awesomeValidation.addValidation(this,R.id.cl_Name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //Add Validation for mobile number
        awesomeValidation.addValidation(this,R.id.client_Phone,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        tvDate = findViewById(R.id.date);
        txtdate = findViewById(R.id.cl_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myrequestinsert.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/"+month+"/"+year;
                tvDate.setText(date);
            }
        };

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        myrequestinsert.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        txtdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        client = new Client();
        dbreff = FirebaseDatabase.getInstance().getReference().child("Client");
        dbreff.addValueEventListener(new ValueEventListener() {
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




        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //On success
                if(awesomeValidation.validate()){
                    Toast.makeText(getBaseContext(),"Form Validated Successfully..",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Validate Failed.",Toast.LENGTH_SHORT).show();
                }

                try{
                    if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid name.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtlocation.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid location.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtcategory.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid category.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtdate.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid date.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtphone.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid phone number.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtservicetype.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid service type.",Toast.LENGTH_SHORT).show();
                    else{
                        client.setName(txtname.getText().toString().trim());
                        client.setLocation(txtlocation.getText().toString().trim());
                        client.setCategory(txtcategory.getText().toString().trim());
                        client.setDate(txtdate.getText().toString().trim());
                        client.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                        client.setServiceType(txtservicetype.getText().toString().trim());

                        dbreff.child(String.valueOf(maxid + 1)).setValue(client);

                        Toast.makeText(getBaseContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show();


                        req_no=String.valueOf(maxid+1);

                        Intent intent = new Intent(v.getContext(),Request_confirm.class);
                        intent.putExtra("number",req_no);
                        v.getContext().startActivity(intent);







                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid format",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }

        });


    }


        public void onCancle(View view){
            ClearControls();
            Intent intent = new Intent(this,MyRequest.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_SHORT).show();
        }
        public void ClearControls(){

        txtname.setText(" ");
        txtlocation.setText(" ");
        txtcategory.setText(" ");
        txtdate.setText(" ");
        txtphone.setText(" ");
        txtservicetype.setText(" ");
        }
}