package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void soupActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Soup");
        startActivity(i);
    }

    public void pizzaActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Pizza");
        startActivity(i);
    }

    public void pastaActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Pasta");
        startActivity(i);
    }

    public void steakActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Steak");
        startActivity(i);
    }

    public void stewActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Stew");
        startActivity(i);
    }

    public void chickenActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Chicken");
        startActivity(i);
    }

    public void veganActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Vegan");
        startActivity(i);
    }

    public void dessertActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Dessert");
        startActivity(i);
    }

    public void drinksActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Drinks");
        startActivity(i);
    }

    public void otherRecipesActivity(View v)
    {
        Intent i = new Intent(this, FoodActivity.class);
        i.putExtra("message", "Other Recipies");
        startActivity(i);
    }

    public void addRecipeActivity(View v)
    {
        Intent i = new Intent(this, AddRecipeActivity.class);
        startActivity(i);
    }



}