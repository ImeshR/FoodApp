package com.example.foodapp.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activity.Domain.FoodDomain;
import com.example.foodapp.Activity.Helper.ManageCart;
import com.example.foodapp.Activity.Interface.ChangeNumberItemsListner;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private final ArrayList<FoodDomain> foodDomains;
    private final ManageCart manageCart;
    private final ChangeNumberItemsListner changeNumberItemsListner;

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListner changeNumberItemsListner) {
        this.foodDomains = foodDomains;
        this.manageCart = new ManageCart(context);
        this.changeNumberItemsListner = changeNumberItemsListner;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {

            holder.title.setText(foodDomains.get(position).getTitle());
            holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
            holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()) * 100)/100));
            holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

            int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                    "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

//        holder.plusLItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manageCart.plusNumberFood(foodDomains, position, new ChangeNumberItemsListner() {
//                    @Override
//                    public void Change() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListner.Change();
//                    }
//                });
//            }
//        });
//
//        holder.minItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                manageCart.minNumberFood(foodDomains, position, new ChangeNumberItemsListner() {
//                    @Override
//                    public void Change() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListner.Change();
//                    }
//                });
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusLItem, minItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem =  itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusLItem = itemView.findViewById(R.id.plusCartBtn);
            minItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.num);

        }
    }
}
