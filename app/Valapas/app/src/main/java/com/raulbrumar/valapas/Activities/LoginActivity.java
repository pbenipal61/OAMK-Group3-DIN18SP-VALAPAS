package com.raulbrumar.valapas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.Activities.Customers.CustomerHomeActivity;
import com.raulbrumar.valapas.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void registerClicked(View view)
    {
        Intent intent = new Intent(this, RegisterTypeActivity.class);
        startActivity(intent);
    }

    public void loginClicked(View view)
    {
        Intent intent = new Intent(this, CustomerHomeActivity.class);
        startActivity(intent);
    }
}
