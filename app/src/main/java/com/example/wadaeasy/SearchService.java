package com.example.wadaeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchService extends AppCompatActivity {
    ListView listView;
    FirebaseListAdapter adapter;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        listView = (ListView) findViewById(R.id.listData);
        Query query = FirebaseDatabase.getInstance().getReference().child("Service");
        FirebaseListOptions<ServiceListItems> options = new FirebaseListOptions.Builder<ServiceListItems>()
                .setLayout(R.layout.row)
                .setLifecycleOwner(com.example.wadaeasy.SearchService.this)
                .setQuery(query, ServiceListItems.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView service, name, area1, area2, uid;
                Button view;

                service = v.findViewById(R.id.service);
                name = v.findViewById(R.id.providerName);
                area1 = v.findViewById(R.id.area1);
                area2 = v.findViewById(R.id.area2);
                uid = v.findViewById(R.id.id);
                view = v.findViewById(R.id.viewBtn);

                ServiceListItems serviceListItems = (ServiceListItems) model;

                service.setText(serviceListItems.getService().toString());
                name.setText(serviceListItems.getName().toString());
                area1.setText(serviceListItems.getArea1().toString());
                area2.setText(serviceListItems.getArea2().toString());
                uid.setText(serviceListItems.getUid().toString());

                String UserID = (String) uid.getText();

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SearchService.this,ServiceView_Client.class);
                        intent.putExtra("UID",UserID);
                        startActivity(intent);

                    }
                });

            }
        };
        listView.setAdapter(adapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

