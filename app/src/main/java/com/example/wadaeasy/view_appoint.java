package com.example.wadaeasy;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class view_appoint extends AppCompatActivity {

    DatabaseReference dbref;
    ListView appointmentList;
    ArrayList<Appointment> details= new ArrayList<Appointment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appoint);
        this.setTitle("වැඩ Easy -View");


        dbref = FirebaseDatabase.getInstance().getReference().child("-MZ8QnrdHMdQNKR_kS7Y");
        ArrayAdapter<Appointment> newArray= new ArrayAdapter<Appointment>(this, android.R.layout.simple_list_item_1, details);
        appointmentList.setAdapter(newArray);


        dbref.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String name=snapshot.getValue(String.class);
                        int Contact=snapshot.getValue(Integer.class);
                        String email=snapshot.getValue(String.class);
                        String providor=snapshot.getValue(String.class);
                   Appointment ap1=new Appointment(name,Contact,email,providor);
                   details.add(ap1);
                   newArray.notifyDataSetChanged();

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

    }
}