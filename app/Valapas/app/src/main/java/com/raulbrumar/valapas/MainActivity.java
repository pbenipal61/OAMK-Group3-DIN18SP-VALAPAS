package com.raulbrumar.valapas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.raulbrumar.valapas.Activities.BrowsePage;
import com.raulbrumar.valapas.Activities.LoginActivity;
import com.raulbrumar.valapas.Activities.RegisterTypeActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectLogin(View v)
    {
        Intent i = new Intent (this, LoginActivity.class);
        startActivity(i);
    }

    public void selectSignup(View v)
    {
        Intent i = new Intent (this, RegisterTypeActivity.class);
        startActivity(i);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, BrowsePage.class);
        startActivity(i);
    }
}