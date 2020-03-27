package com.raulbrumar.valapas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.raulbrumar.valapas.ApiHandler.ApiHandler;
import com.raulbrumar.valapas.Models.User;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("John", "Doe", true, "john_doe@email.com", "123", "Oulu");

        ApiHandler.registerUser(this, user);
    }
}
