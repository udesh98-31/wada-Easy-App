package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class wantedlistview extends AppCompatActivity {
    ListView listviewwanted;
    DatabaseReference refdb;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantedlistview);

        listviewwanted=(ListView) findViewById(R.id.wantedlistV);

        refdb= FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
        refdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String location1=snapshot.child("location").getValue().toString();
                String Date1=snapshot.child("date").getValue().toString();
                String time1=snapshot.child("time").getValue().toString();
                String contact1=snapshot.child("contact").getValue().toString();
                String remark1=snapshot.child("remark").getValue().toString();
                String name1=snapshot.child("name").getValue().toString();
                //String proid1=snapshot.child("uid").getValue().toString();

                arrayList.add("Location:"+location1+"\n"+"Date:"+Date1+"\n"+"Time:"+time1+"\n"+"Contact number:"+contact1+"\n"+"Customer:"+name1+"\n"+"Remark:"+remark1+"\n"+ "\n"+"CLICK HIRE TO CONFIRM");
                arrayAdapter = new ArrayAdapter<String>(wantedlistview.this, android.R.layout.simple_list_item_1,arrayList);
                listviewwanted.setAdapter(arrayAdapter);


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