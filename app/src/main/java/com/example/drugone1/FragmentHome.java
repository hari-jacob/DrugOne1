package com.example.drugone1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Med> lstMeds;

    public FragmentHome() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.home_fragment,container,false);

        myrecyclerview=(RecyclerView)v.findViewById(R.id.home_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstMeds);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstMeds=new ArrayList<>();
        lstMeds.add(new Med("Anacin","Anacin helps in curing Fever",R.drawable.container));
        lstMeds.add(new Med("Amlong","Amlog helps to contoll BP",R.drawable.doubletab2));
        lstMeds.add(new Med("Cold-3 Kit","Cold-3 helps in treating Cold",R.drawable.singletab));
        lstMeds.add(new Med("Lantus 100","Lantus 100 helps to contol Insulin",R.drawable.container2));
        lstMeds.add(new Med("Calcimax Forte","Calcimax Forte prevents increase of Thyroid",R.drawable.doubletab));
        lstMeds.add(new Med("Paracetamol","P 500 helps in curing Fever",R.drawable.ear));
        lstMeds.add(new Med("Citrixin","Citrixin is Anti Histamine curing Cough",R.drawable.eyes));
        lstMeds.add(new Med("Amoxicillin","Amoxicillin is Antibiotic provides Immunity",R.drawable.nose));
        lstMeds.add(new Med("Emeset","Emeset is an Antimetic",R.drawable.strip));
        lstMeds.add(new Med("Zerodol sp","Zerodol sp acts as Pain Killer",R.drawable.dentist));
    }

}
