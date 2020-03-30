package com.raulbrumar.valapas.Activities.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.R;

public class CustomerProfileActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
    }

    public void homeClicked(View view)
    {
        Intent intent = new Intent(this, CustomerHomeActivity.class);
        startActivity(intent);
    }

    public void favoritesClicked(View view)
    {
        Intent intent = new Intent(this, CustomerFavoritesActivity.class);
        startActivity(intent);
    }

    public void bookingsClicked(View view)
    {
        Intent intent = new Intent(this, CustomerReservationsActivity.class);
        startActivity(intent);
    }
}
