package com.group3.valapas.Activities.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.group3.valapas.R;

public class CustomerFavoritesActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_favorites_tab);
    }

    public void customerSelectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowseTab.class);
        startActivity(i);
    }

    public void customerSelectMyProfile(View v)
    {
        Intent i = new Intent (this, CustomerProfileActivity.class);
        startActivity(i);
    }

    public void customerSelectFavorites(View v)
    {
        Intent i = new Intent (this, CustomerFavoritesActivity.class);
        startActivity(i);
    }

    public void customerSelectBookings(View v)
    {
        Intent i = new Intent (this, CustomerBookingsActivity.class);
        startActivity(i);
    }
}