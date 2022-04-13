package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodrecipe.R;

public class MainActivity extends AppCompatActivity {

    private DBUsers dbUsers;
    private Button signIn;
    private EditText userName, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgFb = (ImageView)findViewById(R.id.fb);
        ImageView imgInsta = (ImageView)findViewById(R.id.insta);
        ImageView imgTwit = (ImageView)findViewById(R.id.twit);

        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgTwit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.twitter.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        signIn = findViewById(R.id.signInBtn);
        dbUsers = new DBUsers(MainActivity.this);
        Cursor cursor = dbUsers.getName();


        String[] userN = new String[cursor.getCount()];
        String[] userP = new String[cursor.getCount()];

        userName = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);

        Intent i = new Intent(this, FirstActivity.class);

        signIn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {

                String userString = userName.getText().toString();
                String passwordString = userPassword.getText().toString();

                if (userString.isEmpty() && passwordString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cursor.moveToFirst()) {
                    int i = 0;
                    do {
                        userN[i] = cursor.getString(cursor.getColumnIndex(DBUsers.NAME_COL));
                        i++;
                    } while (cursor.moveToNext());
                }

                if(cursor.moveToFirst()) {
                    int i = 0;
                    do {
                        try {
                            userP[i] = AESCrypt.decrypt(cursor.getString(cursor.getColumnIndex(DBUsers.PASSWORD_COL)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i++;
                    } while (cursor.moveToNext());
                }

                boolean testUser = false;
                for(int i = 0; i < cursor.getCount(); ++i)
                {
                    if(userString.equals(userN[i]) && passwordString.equals(userP[i]))
                        testUser = true;
                }

                if(testUser) {
                    Toast.makeText(MainActivity.this, "Verified User", Toast.LENGTH_SHORT).show();
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

                else
                    Toast.makeText(MainActivity.this, "Username or password are wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void regActivity(View v)
    {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }




}