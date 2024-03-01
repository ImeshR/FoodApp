package com.example.foodapp.Activity.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.Activity.Domain.FoodDomain;
import com.example.foodapp.Activity.Interface.ChangeNumberItemsListner;

import java.util.ArrayList;

public class ManageCart {
    private Context context;
    private TinyDB tinydb;

    public ManageCart(Context context) {
        this.context = context;
        this.tinydb = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){

        ArrayList<FoodDomain> listFood = getlistCart();

        boolean existAlready = false;
        int n =  0;
        for(int i = 0; i < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){

                    existAlready = true;
                    n = i;
                    break;
            }
        }
        if(existAlready){

            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);

        }
        tinydb.putListObject("CardList", listFood);
        Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show();


    }
    public ArrayList<FoodDomain> getlistCart(){

        return  tinydb.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListner changeNumberItemsListner){

        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinydb.putListObject("CartList", listfood);
        changeNumberItemsListner.Change();
    }
    public void minNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListner changeNumberItemsListner){
        if(listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        }else{
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinydb.putListObject("CartList", listfood);
        changeNumberItemsListner.Change();

    }

    public  Double getTotalFee(){
        ArrayList<FoodDomain> listfood = getlistCart();
        double fee = 0;
        for(int i = 0; i < listfood.size(); i++){

            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return  fee;
    }


}
