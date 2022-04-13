package com.example.foodrecipe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodrecipe.R;

public class RegistrationActivity extends AppCompatActivity {

    private DBUsers dbUsers;
    private Button registerBtn;
    private EditText txtName, txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbUsers = new DBUsers(RegistrationActivity.this);
        Cursor cursor = dbUsers.getName();

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameString = txtName.getText().toString();
                String emailString = txtEmail.getText().toString();
                String passwordString = txtPassword.getText().toString();
                String encryptedPassword = null;
                try {
                    encryptedPassword = AESCrypt.encrypt(passwordString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (nameString.isEmpty() && emailString.isEmpty() && passwordString.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbUsers.addNewUser(nameString, emailString, encryptedPassword);
                Toast.makeText(RegistrationActivity.this, "User has been added.", Toast.LENGTH_SHORT).show();
                txtName.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
            }
        });
    }

    public void registrationActivity(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}