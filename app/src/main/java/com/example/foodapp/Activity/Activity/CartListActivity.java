package com.example.foodapp.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activity.Adapter.CartListAdapter;
import com.example.foodapp.Activity.Helper.ManageCart;
import com.example.foodapp.Activity.Interface.ChangeNumberItemsListner;
import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private  RecyclerView recyclerViewlist;
    private ManageCart manageCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;

    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        manageCart  =  new ManageCart(this);
        initView();
        initlist();
        CalculateCart();
        bottomNavigation();
    }

    private  void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn =  findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    private void initView(){

        recyclerViewlist=findViewById(R.id.recyclerView);
        totalFeeTxt= findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView4);
        recyclerViewlist=findViewById(R.id.cartView);


    }
    private void initlist(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter=new CartListAdapter(manageCart.getlistCart(), this,  new ChangeNumberItemsListner() {
            @Override
            public void Change() {
                CalculateCart();
            }
        });

        recyclerViewlist.setAdapter(adapter);
        if(manageCart.getlistCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private  void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((manageCart.getTotalFee()* percentTax) * 100) /100;
        double total = Math.round((manageCart.getTotalFee() + tax + delivery) * 100) /100;
        double itemTotal = Math.round(manageCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$"+ itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

}