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

public class Feedback extends AppCompatActivity {

    EditText namedate, emaildata, messagedat;
    Button submit;
    DatabaseReference dbreff;
    FeedbackDetails feedbackDetails;
    long maxid = 0;
    String req_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        this.setTitle("Easy වැඩ-Feedback ");

        namedate = findViewById(R.id.cl_Name);
        emaildata = findViewById(R.id.email);
        messagedat = findViewById(R.id.feedback);

        submit = findViewById(R.id.button15);

        feedbackDetails = new FeedbackDetails();
        dbreff = FirebaseDatabase.getInstance().getReference().child("FeedbackDetails");
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




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    if(TextUtils.isEmpty(namedate.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid name.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(emaildata.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid email.",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(messagedat.getText().toString()))
                        Toast.makeText(getBaseContext(),"Please enter a valid feedback",Toast.LENGTH_SHORT).show();

                    else{
                        feedbackDetails.setName(namedate.getText().toString().trim());
                        feedbackDetails.setEmail(emaildata.getText().toString().trim());
                        feedbackDetails.setMessage(messagedat.getText().toString().trim());

                        dbreff.child(String.valueOf(maxid + 1)).setValue(feedbackDetails);

                        Toast.makeText(getBaseContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show();


                        req_no=String.valueOf(maxid+1);

                        //Intent intent = new Intent(v.getContext(),feedbackretrive.class);
                        //intent.putExtra("number",req_no);
                        //v.getContext().startActivity(intent);



                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Please enter a valid format",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }

        });


    }



    public void ClearControls(){

        namedate.setText(" ");
        emaildata.setText(" ");
        messagedat.setText(" ");

    }




    }
