package com.example.drugone1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugone1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentAccount2 extends Fragment {

    View v;
    private  Button signOut;
    private TextView name;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    public int flag=0;
    public FragmentAccount2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.account_fragment2,container,false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        signOut = (Button)v.findViewById(R.id.acc2b1);
        name=(TextView) v.findViewById(R.id.acc2t2);

        if(currentUser == null){
            Toast.makeText(getActivity(),"invalid",Toast.LENGTH_LONG).show();
        }
        else{

            name.setText(currentUser.getEmail());
        }
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getActivity(),MainActivity.class);
                loginActivity.putExtra("intvarname",flag);
                startActivity(loginActivity);
                getActivity().finish();
            }
        });

        return v;
    }
}
