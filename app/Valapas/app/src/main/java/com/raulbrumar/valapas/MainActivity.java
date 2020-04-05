package com.raulbrumar.valapas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.raulbrumar.valapas.ApiHandler.ApiHandler;
import com.raulbrumar.valapas.Models.User;

public class MainActivity extends AppCompatActivity implements IReturnUserCallback
{

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("John", "Doe", true, "john_doe@email.com", "123", "Oulu");

        ApiHandler.loginUser(this, user, this);

    }


    // Interface Methods
    @Override
    public void returnUser(User user)
    {
        this.user = user;

        Log.d("AAA", "returnUser: " + user.toString());
    }
}
