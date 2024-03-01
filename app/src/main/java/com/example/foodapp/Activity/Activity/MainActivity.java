package com.example.foodapp.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.Activity.Adapter.CategoryAdapter;
import com.example.foodapp.Activity.Adapter.PopularAdapter;
import com.example.foodapp.Activity.Domain.CategoryDomain;
import com.example.foodapp.Activity.Domain.FoodDomain;
import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recycleViewCategoryList;
    private RecyclerView recycleViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycleViewCategory();
        recycleViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recycleViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycleViewCategoryList = findViewById(R.id.recyclerView);
        recycleViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Burger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drinks", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(category);
        recycleViewCategoryList.setAdapter(adapter);

    }

    private void recycleViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recycleViewPopularList = findViewById(R.id.recucleView2);
        recycleViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();

        foodlist.add(new FoodDomain("Pepporoni pizza", "pop_1", "slices pepperoni,mozzerella cheese,black papper,tomato sauce", 9.76));
        foodlist.add(new FoodDomain("Cheese Burger", "pop_2", "beef,Gouda cheese,special sauce,Lettuce, tomato", 8.75));
        foodlist.add(new FoodDomain("Vegitable pizza", "pop_3", "olive oil,Vegitable oil,pitted kalamata, cherry tomato, fressh orange, basil", 8.5));

        adapter2 = new PopularAdapter(foodlist);
        recycleViewPopularList.setAdapter(adapter2);

    }

}