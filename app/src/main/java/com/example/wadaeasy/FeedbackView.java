package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FeedbackView extends AppCompatActivity {

    ListView listviewfeedback;
    DatabaseReference refdb;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ImageView backbtnFD,myserviceFD,appoimentFD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedbackview);

        listviewfeedback=(ListView) findViewById(R.id.Feedbacklistview);
        backbtnFD=findViewById(R.id.FDback);
        myserviceFD=findViewById(R.id.FDmyservice);
        appoimentFD=findViewById(R.id.FDappoiment);

        refdb= FirebaseDatabase.getInstance().getReference().child("FeedbackDetails");
        refdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String Email=snapshot.child("email").getValue().toString();
                String msg=snapshot.child("message").getValue().toString();
                String name=snapshot.child("name").getValue().toString();

                arrayList.add(name+ "  " + "  " + "  " + "  " +Email+"\n"+"\n"+msg);
                arrayAdapter = new ArrayAdapter<String>(FeedbackView.this, android.R.layout.simple_list_item_1,arrayList);
                listviewfeedback.setAdapter(arrayAdapter);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //nav button
        backbtnFD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(FeedbackView.this,ServiceHome2.class);
                startActivity(intent);
            }
        });

        myserviceFD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(FeedbackView.this,ServiceView.class);
                startActivity(intent);
            }
        });
        appoimentFD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(FeedbackView.this,AppoimentVIewS.class);
                startActivity(intent);
            }
        });

    }
}