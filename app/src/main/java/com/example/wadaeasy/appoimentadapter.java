package com.example.wadaeasy;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class appoimentadapter extends ArrayAdapter<Appointment> {

    public appoimentadapter(@NonNull Context context, int resource, Context context1, List<Appointment> appointments) {
        super(context, resource);
        this.context = context1;
        this.appointments = appointments;
    }

    private Context context;
    List<Appointment> appointments;




}
