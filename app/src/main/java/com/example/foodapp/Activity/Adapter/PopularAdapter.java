package com.example.foodapp.Activity.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Activity.Activity.ShowDetailActivity;
import com.example.foodapp.Activity.Domain.FoodDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<FoodDomain> popularfood;

    public PopularAdapter(ArrayList<FoodDomain> popularFood) {
        this.popularfood = popularFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View infalte = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular , parent, false);
        return new ViewHolder(infalte);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(popularfood.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularfood.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularfood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext() , ShowDetailActivity.class);
                intent.putExtra("object", popularfood.get(position));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularfood.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById((R.id.fee));
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);


        }
    }

}
