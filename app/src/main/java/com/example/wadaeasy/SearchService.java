package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class SearchService extends AppCompatActivity {

    DatabaseReference dbref;
    Button t;
    ListView listdata;
    TextView sername,name,area1,area2;
    DataSnapshot serviceSnapshot;
    DataSnapshot s;
    String Name;
    Service ser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);
        listdata = findViewById(R.id.listData);
        dbref = FirebaseDatabase.getInstance().getReference("Service");
        ArrayList<String>listService=new ArrayList<>();
        ArrayList<String>id=new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listService);



        listdata.setAdapter(arrayAdapter);





        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serviceSnapshot=snapshot;
                if(snapshot.exists())
                {

                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            s = dataSnapshot;
                        Name = dataSnapshot.child("name").getValue(String.class);
                        ser = new Service(Name, dataSnapshot.child("service").getValue(String.class),dataSnapshot.child("area1").getValue(String.class), dataSnapshot.child("area2").getValue(String.class));
                        listService.add("Service Provider --   "+ser.getName()+"\n"+"Service --  "+ser.getService()+"\n"+ser.getArea1()+"\n"+ser.getArea2());

                    }

                        arrayAdapter.notifyDataSetChanged();

                }else{
                    Log.d("Service","No Data Available");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Name =  s.child("service").getValue(String.class);

                Intent intent = new Intent(SearchService.this,ServiceView_Client.class);
                intent.putExtra("ID", Name);
                startActivity(intent);
            }
        });

    }
}