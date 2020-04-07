package com.raulbrumar.valapas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedUser;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.raulbrumar.valapas.ApiHandler.ApiHandler;
import com.raulbrumar.valapas.Models.User;
import com.raulbrumar.valapas.Models.UserBuilder;

public class MainActivity extends AppCompatActivity implements IReturnUserCallback, IDeletedUser
{

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* for creating a new user (registering)
        user = new UserBuilder().firstName("Johnny").lastName("Gat").city("New York").email("johnnyyyyy.gat@mail.com").password("123").isAdult(true).buildUser();
        ApiHandler.registerUser(this, user, this);
        */

        /*
        // for loging in and getting the bearer token
        user = new UserBuilder().email("johnnyyyyy.gat@mail.com").password("123").buildUser();
        ApiHandler.loginUser(this, user, this);
        */

        /*
        user = new UserBuilder().id("5e8c747636a591752f2725f4").firstName("Hatz").lastName("Johnule").city("Bucuresti").email("hatz@yahoo.com").password("12345").isAdult(false).buildUser();
        ApiHandler.editUser(this, user, this);
        */

        user = new UserBuilder().id("5e8c747636a591752f2725f4").firstName("Hatz").lastName("Johnule").city("Bucuresti").email("hatz@yahoo.com").password("12345").isAdult(false).buildUser();
        ApiHandler.editUser(this, user, this);
    }


    // Interface Methods
    @Override
    public void returnUser(final User user)
    {
        this.user = user;

        Log.d("AAA", "returnUser: " + user.toString());
    }

    @Override
    public void deletedDone()
    {
        Log.d("AAA", "deleted successfully");
    }
}
