package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updaterequest extends AppCompatActivity {
    EditText txtname, txtlocation, txtcategory, txtdate, txtphone, txtservicetype;
    TextView pr_no1;
    Button Edit;
    DatabaseReference dbreff;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterequest);

        client = new Client();

        Intent aboutScreen= getIntent();
        String a1=aboutScreen.getStringExtra("numbers");

        txtname = findViewById(R.id.cl_Name);
        txtlocation = findViewById(R.id.cl_loc);
        txtcategory =findViewById(R.id.s_Category);
        txtdate =findViewById(R.id.cl_udate);
        txtphone = findViewById(R.id.cl_uphone);
        txtservicetype = findViewById(R.id.feedback);

        pr_no1=findViewById(R.id.edtreq_no);

        Edit=findViewById(R.id.edit);

        dbreff = FirebaseDatabase.getInstance().getReference().child("Client").child(a1);
        dbreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String category = snapshot.child("category").getValue().toString();
                String phone = snapshot.child("phone").getValue().toString();
                String serviceType = snapshot.child("serviceType").getValue().toString();

                //only retriving these values not updating we need to push it back else record gets
                String name = snapshot.child("name").getValue().toString();
                String location = snapshot.child("location").getValue().toString();
                String date = snapshot.child("date").getValue().toString();

                txtname.setText(name);
                txtlocation.setText(location);
                txtcategory.setText(category);
                txtdate.setText(date);
                txtphone.setText(phone);
                txtservicetype.setText(serviceType);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Alert
                AlertDialog.Builder builder = new AlertDialog.Builder(updaterequest.this);
                builder.setMessage("Do You Want To Upate Request ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dbreff = FirebaseDatabase.getInstance().getReference().child("Client");
                                dbreff.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if(snapshot.hasChild(a1)){

                                            try{

                                                client.setName(txtname.getText().toString().trim());
                                                client.setLocation(txtlocation.getText().toString().trim());
                                                client.setCategory(txtcategory.getText().toString().trim());
                                                client.setDate(txtdate.getText().toString().trim());
                                                client.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                                                client.setServiceType(txtservicetype.getText().toString().trim());




                                                dbreff.child(a1).setValue(client);
                                                ClearControls();
                                                Toast.makeText(getBaseContext(), "Your Request has been Updated", Toast.LENGTH_LONG).show();

                                            }
                                            catch (NumberFormatException e) {
                                                Toast.makeText(getBaseContext(), "Enter Valid Format", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(getBaseContext(), "No Source to update", Toast.LENGTH_LONG).show();
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




    public void Cancel(View view){
        Intent intent=new Intent(this, Request_confirm.class);
        //intent.putExtra("number",a1);
        startActivity(intent);
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











