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

public class wantedlistview extends AppCompatActivity {
    ListView listviewwanted;
    DatabaseReference refdb;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ImageView backbtnSR,myserviceSR,appoimentSR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantedlistview);

        listviewwanted=(ListView) findViewById(R.id.wantedlistV);
        backbtnSR=findViewById(R.id.srbackbt);
        myserviceSR=findViewById(R.id.srmyservice);
        appoimentSR=findViewById(R.id.Appoimentsr);



        refdb= FirebaseDatabase.getInstance().getReference().child("Client");
        refdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String location1=snapshot.child("location").getValue().toString();
                String phone1=snapshot.child("phone").getValue().toString();
                String name1=snapshot.child("name").getValue().toString();
                String RequestService=snapshot.child("serviceType").getValue().toString();

                arrayList.add("Location:"+location1+"\n"+"Name:"+name1+"\n"+"Contact:"+phone1+"\n"+RequestService);
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
        backbtnSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(wantedlistview.this,ServiceHome2.class);
                startActivity(intent);
            }
        });

        myserviceSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(wantedlistview.this,ServiceView.class);
                startActivity(intent);
            }
        });
        appoimentSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(wantedlistview.this,AppoimentVIewS.class);
                startActivity(intent);
            }
        });

    }
}