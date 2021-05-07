package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Appoiment_list_activity extends AppCompatActivity {
    ListView listviewApmnt;
    DatabaseReference refdb;
    ArrayList<Appointment> arrayList=new ArrayList<Appointment>();
    ArrayAdapter<String>arrayAdapter;
    FirebaseUser user;
    String uid;
    Appointment apm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoiment_list_activity);
        apm=new Appointment();

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        refdb= FirebaseDatabase.getInstance().getReference().child("Confirm Appointment");
        listviewApmnt=findViewById(R.id.Apoimentlist);
        refdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String location1=snapshot.child("location").getValue().toString();
                String Date1=snapshot.child("date").getValue().toString();
                String time1=snapshot.child("time").getValue().toString();
                String contact1=snapshot.child("contact").getValue().toString();
                String remark1=snapshot.child("remark").getValue().toString();
                String name1=snapshot.child("name").getValue().toString();
                String proid1=snapshot.child("uid").getValue().toString();

                apm.setLocation(location1);
                apm.setDate(Date1);
                apm.setTime(time1);
               //apm.setContact(Integer.parseInt(contact1));
                apm.setRemark(remark1);
                apm.setName(name1);
                apm.setProvider_no(proid1);
                arrayList.add(apm);





                //arrayList.add("Location:"+location1+"\n"+"Date:"+Date1+"\n"+"Time:"+time1+"\n"+"Contact number:"+contact1+"\n"+"Customer:"+name1+"\n"+"Remark:"+remark1+"\n"+ "\n"+"CLICK HIRE TO CONFIRM");


                    //arrayAdapter = new ArrayAdapter<String>(Appoiment_list_activity.this,R.layout.appoimentlistview, arrayList.add(apm));
                    listviewApmnt.setAdapter(arrayAdapter);






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
