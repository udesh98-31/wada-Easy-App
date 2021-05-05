package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateRequest extends AppCompatActivity {

    EditText txtname, txtlocation, txtcategory, txtdate, txtphone, txtservicetype;
    Button Edit;
    DatabaseReference dbreff;
    Client client;

    Intent aboutScreen= getIntent();
    String a1= aboutScreen.getStringExtra("numbers");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequestinsert);
        this.setTitle("වැඩ Easy -Request Service");

        client = new Client();

        Intent intent=getIntent();
        String number1=intent.getStringExtra("number");

        txtname = findViewById(R.id.cl_Name);
        txtlocation = findViewById(R.id.cl_loc);
        txtcategory =findViewById(R.id.s_Category);
        txtdate =findViewById(R.id.cl_udate);
        txtphone = findViewById(R.id.cl_uphone);
        txtservicetype = findViewById(R.id.cl_service);

        Edit=findViewById(R.id.edit);

        dbreff = FirebaseDatabase.getInstance().getReference().child("Client").child(a1);
        dbreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String category = snapshot.child("category").getValue().toString();
                String phone = snapshot.child("phone").getValue().toString();
                String serviceType = snapshot.child("serviceType").getValue().toString();


                //only retriving these values not updating we need to push it back else record gets altered
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
                dbreff=FirebaseDatabase.getInstance().getReference().child("Client");
                dbreff.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild(a1)) {

                            try {

                                client.setName(txtname.getText().toString().trim());
                                client.setLocation(txtlocation.getText().toString().trim());
                                client.setCategory(txtcategory.getText().toString().trim());
                                client.setDate(txtdate.getText().toString().trim());
                                client.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                                client.setServiceType(txtservicetype.getText().toString().trim());


                                dbreff.child(a1).setValue(client);
                                ClearControls();
                                Toast.makeText(getBaseContext(), "Your Appointment has been Updated", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(v.getContext(), UpdateConfirmation.class);
                                intent.putExtra("number", a1);
                                v.getContext().startActivity(intent);

                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    public void onCancle(View view){

        Intent intent = new Intent(this,MyRequest.class);
        startActivity(intent);
        Toast.makeText(getBaseContext(),  "You just click the button",Toast.LENGTH_SHORT).show();
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
