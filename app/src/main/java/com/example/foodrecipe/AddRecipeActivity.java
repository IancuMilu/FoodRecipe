package com.example.foodrecipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private static final String[] paths = {"Soup", "Pizza", "Pasta", "Steak", "Stew", "Chicken", "Vegan", "Dessert", "Drinks", "Other Recipies"};

    private EditText recipeName, time, ingredients, description;
    private Button addRecipeBtn;
    private DBHandler dbHandler;

    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(AddRecipeActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button gallery = findViewById(R.id.addPicture);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        recipeName = findViewById(R.id.food_name);
        time = findViewById(R.id.timeTxt);
        ingredients = findViewById(R.id.addIngredients);
        description = findViewById(R.id.description);
        addRecipeBtn = findViewById(R.id.addRecipe);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddRecipeActivity.this);

        // below line is to add on click listener for our add course button.
        addRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String recipeNameString = recipeName.getText().toString();
                String category = spinner.getSelectedItem().toString();
                String timeString = time.getText().toString();
                String ingredientsString = ingredients.getText().toString();
                String descriptionString = description.getText().toString();

                // validating if the text fields are empty or not.
                if (recipeNameString.isEmpty() && timeString.isEmpty() && ingredientsString.isEmpty() && descriptionString.isEmpty()) {
                    Toast.makeText(AddRecipeActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewRecipe(recipeNameString, category, timeString, ingredientsString, descriptionString);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddRecipeActivity.this, "Recipe has been added.", Toast.LENGTH_SHORT).show();
                recipeName.setText("");
                time.setText("");
                ingredients.setText("");
                description.setText("");
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.displayImage);
            imageView.setImageURI(selectedImage);
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}