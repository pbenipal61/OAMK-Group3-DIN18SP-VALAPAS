package com.raulbrumar.valapas.Activities.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.Activities.LoginActivity;
import com.raulbrumar.valapas.Activities.RegisterTypeActivity;
import com.raulbrumar.valapas.R;

public class CustomerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
    }

    public void registerClicked(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
