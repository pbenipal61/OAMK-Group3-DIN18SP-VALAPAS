package com.raulbrumar.valapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.Activities.Companies.CompanyHomeActivity;
import com.raulbrumar.valapas.Activities.Customers.CustomerHomeActivity;
import com.raulbrumar.valapas.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void emulateNonLoggedInClicked(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void emulateLoggedInUserClicked(View view)
    {
        Intent intent = new Intent(this, CustomerHomeActivity.class);
        startActivity(intent);
    }

    public void emulateLoggedInCompanyClicked(View view)
    {
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        startActivity(intent);
    }
}
