package com.example.drugone1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Item_Home extends Fragment {

    View v;
    private List<Med> lstMeds;
    Button addbtn;
    TextView medname,meddetails;

    DatabaseReference databaseDetails;

    public Item_Home() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.dialog_med,container,false);

        databaseDetails= FirebaseDatabase.getInstance().getReference("Order Details");

        medname=(TextView) v.findViewById(R.id.dialog_name_med);
        meddetails=(TextView) v.findViewById(R.id.dialog_details_med);
//        addbtn=(Button) v.findViewById(R.id.dialog_add);
//
//        addbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addDetails();
//                Toast.makeText(getActivity(),"Inside add button",Toast.LENGTH_LONG).show();
//            }
//        });

        return v;
    }

    private void addDetails(){
        String medicine_name=medname.getText().toString().trim();
        String medicine_details=meddetails.getText().toString().trim();

        if (!TextUtils.isEmpty(medicine_name)){

            String id=databaseDetails.push().getKey();
            Medi_Det detail= new Medi_Det(id,medicine_name,medicine_details);

            databaseDetails.child(id).setValue(detail);

            Toast.makeText(getActivity(),"Added to the cart",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
