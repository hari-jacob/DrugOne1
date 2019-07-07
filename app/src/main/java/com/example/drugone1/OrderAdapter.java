package com.example.drugone1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    Context context;
    ArrayList<Orders> orders;

    public  OrderAdapter(Context c,ArrayList<Orders> p){
        context=c;
        orders=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myorder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(orders.get(position).getMed_name());
            holder.detail.setText(orders.get(position).getMed_detail());
//            Picasso.get().load(orders.get(position).getMed_pic()).into(holder.orderpic);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,detail;
        ImageView orderpic;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.order_name_med);
            detail=(TextView)itemView.findViewById(R.id.order_details_med);
            orderpic=(ImageView) itemView.findViewById(R.id.order_img_med);
        }
    }


}
