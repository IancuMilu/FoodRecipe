package com.example.foodrecipe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    ImageView imageView;
    DBHandler db;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        db = new DBHandler(this);
        Cursor cursor = db.getName();
        String[] categories = new String[cursor.getCount()];
        String[] names = new String[cursor.getCount()];

        if(cursor.moveToFirst()) {
            int i = 0;
            do {
                categories[i] = cursor.getString(cursor.getColumnIndex(DBHandler.CATEGORY_COL));
                names[i] = cursor.getString(cursor.getColumnIndex(DBHandler.NAME_COL));
                i++;
            } while (cursor.moveToNext());
        }

        int counter = 0;
        for(int i = 0; i < cursor.getCount(); ++i)
        {
            if(getIntent().getStringExtra("message").equals(categories[i]))
                counter++;
        }

        String[] text = new String[counter];

        if(cursor.moveToFirst()){
            int i = 0;
            int j = 0;
            do{
                if(getIntent().getStringExtra("message").equals(categories[i])) {
                    text[j] = names[i];
                    j++;
                }
                i++;
            }while(cursor.moveToNext());
        }

        viewPager2 = findViewById(R.id.soupVP);
        viewPagerItemArrayList = new ArrayList<>();

        for(int i = 0; i < text.length; ++i){
            ViewPagerItem viewPagerItem = new ViewPagerItem(text[i],imageView);
            viewPagerItemArrayList.add(viewPagerItem);
        }

        TextView textView = findViewById(R.id.food_name);
        textView.setText(getIntent().getStringExtra("message"));

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        viewPager2.setAdapter(vpAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);



    }

    public void recipeActivity(View v)
    {
        Intent i = new Intent(this, RecipeActivity.class);
        startActivity(i);

    }
}