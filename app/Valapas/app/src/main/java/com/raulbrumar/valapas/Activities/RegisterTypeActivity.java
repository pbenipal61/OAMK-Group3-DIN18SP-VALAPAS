package com.raulbrumar.valapas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.Activities.Companies.CompanyRegisterActivity;
import com.raulbrumar.valapas.Activities.Customers.CustomerRegisterActivity;
import com.raulbrumar.valapas.R;

public class RegisterTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);
    }

    public void registerAsUserClicked(View view)
    {
        Intent intent = new Intent(this, CustomerRegisterActivity.class);
        startActivity(intent);
    }

    public void registerAsCompanyClicked(View view)
    {
        Intent intent = new Intent(this, CompanyRegisterActivity.class);
        startActivity(intent);
    }

    public void backPressed(View view)
    {
        finish();
    }
}
