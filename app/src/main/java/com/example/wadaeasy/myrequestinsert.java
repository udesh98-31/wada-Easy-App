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


public class myrequestinsert extends AppCompatActivity {

    EditText txtname, txtlocation, txtcategory, txtdate, txtphone, txtservicetype;
    Button btnconfirm, btncancle;
    DatabaseReference dbreff;
    Client client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequestinsert);
        this.setTitle("වැඩ Easy -Request Service");



        txtname = findViewById(R.id.cl_Name);
        txtlocation = findViewById(R.id.cl_Location);
        txtcategory =findViewById(R.id.s_Category);
        txtdate =findViewById(R.id.cl_date);
        txtphone = findViewById(R.id.client_Phone);
        txtservicetype = findViewById(R.id.cl_servicet);

        btncancle =findViewById(R.id.rs_cancle);
        btnconfirm =findViewById(R.id.rs_confirm);
        client = new Client();
        dbreff = FirebaseDatabase.getInstance().getReference().child("Client");



        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),RetriveRequestDetails.class);



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



                        dbreff.child(String.valueOf("1")).setValue(client);

                        v.getContext().startActivity(intent);

                        Toast.makeText(getBaseContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                        ClearControls();


                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid format",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }

        });


    }

      /*  public void onConfirm(View view){

            Intent intent = new Intent(this,RetriveRequestDetails.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),  "You just click the button",Toast.LENGTH_SHORT).show();
        }*/
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