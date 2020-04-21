package com.group3.valapas.UserPages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.R;

public class UserProfile extends AppCompatActivity implements IReturnUserCallback {
    private EditText firstNameEditText;
    private EditText lastnameEditText;
    private EditText emailEditText;
    private EditText cityEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        firstNameEditText = findViewById(R.id.firstName);
        lastnameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        cityEditText = findViewById(R.id.city);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    public void updateInfoClicked(View view)
    {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastnameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!password.equals(confirmPassword))
        {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show();
        }
        else
        {
            User userForGettingId = new UserBuilder().buildUser(ApiHandler.getBearerToken());

            User user = new UserBuilder().id(userForGettingId.getId()).firstName(firstName).lastName(lastName).email(email).city(city).password(password).isAdult(userForGettingId.getAdult()).buildUser();
            ApiHandler.editUser(this, user, this);
        }
    }

    @Override
    public void returnUser(User user)
    {
        Toast.makeText(this, "User updated successfully!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, UserBrowse.class);
        startActivity(intent);
    }
}
