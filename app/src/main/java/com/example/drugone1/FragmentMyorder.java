package com.example.drugone1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMyorder extends Fragment {

    View v;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    //private or ?
    DatabaseReference reference;
    RecyclerView myrecyclerview;
    ArrayList<Orders> list;
    OrderAdapter adapter;

    public FragmentMyorder() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.myorder_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        myrecyclerview = (RecyclerView) v.findViewById(R.id.myorder_recyclerview);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<Orders>();

        if (currentUser == null) {
            Toast.makeText(getActivity(), "Please SignIn ", Toast.LENGTH_LONG).show();
        } else {
        String nam1 = currentUser.getUid().toString();
        reference = FirebaseDatabase.getInstance().getReference().child("Order Details").child(nam1);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Orders>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Orders p = dataSnapshot1.getValue(Orders.class);
                    list.add(p);
                }
                adapter = new OrderAdapter(getContext(), list);
                myrecyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), "Something Wrong", Toast.LENGTH_LONG).show();
            }
        });
                }
        return v;
    }
}
