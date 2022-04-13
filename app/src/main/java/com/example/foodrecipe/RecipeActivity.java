package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {

    DBHandler db;
    TextView tv;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        db = new DBHandler(this);
        tv = findViewById(R.id.recipeName);
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
    }

}