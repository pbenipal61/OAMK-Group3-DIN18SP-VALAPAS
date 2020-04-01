package com.group3.valapas;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.group3.valapas.Activities.Customers.UserBrowseTab;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectSignIn(View view)
    {
        Toast.makeText(this, "Signing In...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserBrowseTab.class);
        startActivity(intent);
    }

    public void selectSignUp(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void selectBrowse(View view)
    {
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }
}