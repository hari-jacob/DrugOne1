package com.example.drugone1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mcontext;
    List<Med> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mcontext, List<Med> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v= LayoutInflater.from(mcontext).inflate(R.layout.item_home,viewGroup,false);
        final MyViewHolder vHolder=new MyViewHolder(v);



        //Dialog Initialization


        myDialog=new Dialog(mcontext);
        myDialog.setContentView(R.layout.dialog_med);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        vHolder.item_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                final FirebaseUser currentUser= mAuth.getCurrentUser();
                final DatabaseReference databaseDetails= FirebaseDatabase.getInstance().getReference("Order Details");


                final TextView medname=(TextView) myDialog.findViewById(R.id.dialog_name_med);
                final TextView meddetails=(TextView) myDialog.findViewById(R.id.dialog_details_med);
                String toastdetailname= medname.getText().toString().trim();
                Toast.makeText(mcontext,"Details "+toastdetailname,Toast.LENGTH_LONG).show();
                TextView dialog_name=(TextView) myDialog.findViewById(R.id.dialog_name_med);
                TextView dialog_details=(TextView) myDialog.findViewById(R.id.dialog_details_med);
//                TextView dialog_details2=(TextView) myDialog.findViewById(R.id.dialog_details_med2);
                ImageView dialog_img=(ImageView)myDialog.findViewById(R.id.dialog_img);
                Button dialog_add=(Button) myDialog.findViewById(R.id.dialog_add);
                dialog_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (currentUser == null) {
                            Toast.makeText(mcontext, "Please Sign In", Toast.LENGTH_LONG).show();
                        } else {
                        String nam1 = currentUser.getUid().toString();
                        String medicine_name = medname.getText().toString().trim();
                        String medicine_details = meddetails.getText().toString().trim();
//                        Toast.makeText(mcontext, "Inside add button", Toast.LENGTH_LONG).show();
                        if (!TextUtils.isEmpty(medicine_name)) {

//                            String id=databaseDetails.push().getKey();
                            String id = medicine_name;
                            Medi_Det detail = new Medi_Det(id, medicine_name, medicine_details);

                            databaseDetails.child(nam1).child(id).setValue(detail);

                            Toast.makeText(mcontext, "Added to the cart", Toast.LENGTH_LONG).show();
                                                                }
                                }
                    }
                });
                dialog_name.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_details.setText(mData.get(vHolder.getAdapterPosition()).getDetails());
//                dialog_details2.setText(mData.get(vHolder.getAdapterPosition()).getDetails2());
                dialog_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
                myDialog.show();
            }
        });

        return vHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_name.setText(mData.get(i).getName());
        myViewHolder.tv_details.setText(mData.get(i).getDetails());
        myViewHolder.img.setImageResource(mData.get(i).getPhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_home;
        private TextView tv_name;
        private TextView tv_details;
        private ImageView img;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_home=(LinearLayout) itemView.findViewById(R.id.home_item_id);
            tv_name=(TextView) itemView.findViewById(R.id.name_med);
            tv_details=(TextView) itemView.findViewById(R.id.details_med);
            img=(ImageView) itemView.findViewById(R.id.img_homemed);

        }
    }

}
