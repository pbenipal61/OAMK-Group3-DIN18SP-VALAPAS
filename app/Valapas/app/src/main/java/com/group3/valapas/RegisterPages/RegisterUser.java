package com.group3.valapas.RegisterPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.MainActivity;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.R;

public class RegisterUser extends AppCompatActivity implements IReturnUserCallback
{
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText cityEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private CheckBox checkBox1;
    private CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        cityEditText = findViewById(R.id.city);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
    }

    public void onRegisterClick(View view)
    {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!password.equals(confirmPassword))
        {
            Toast.makeText(this, "Passwords must match", Toast.LENGTH_SHORT).show();
        }
        else if (!checkBox1.isChecked())
        {
            Toast.makeText(this, "You must to the Terms and Conditions first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            User user = new UserBuilder().firstName(firstName).lastName(lastName).email(email).city(city).password(password).isAdult(checkBox2.isChecked()).buildUser();
            ApiHandler.registerUser(this, user, this);
        }
    }

    @Override
    public void returnUser(User user)
    {
        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
