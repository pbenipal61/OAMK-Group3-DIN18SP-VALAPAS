package com.group3.valapas.BrowsePages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group3.valapas.R;
import com.group3.valapas.RegisterPages.RegisterUser;

public class BrowseFavoritesPage extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_favorites_page);
    }

    public void selectUser(View v)
    {
        Intent i = new Intent (this, RegisterUser.class);
        startActivity(i);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, BrowseCategoryTab.class);
        startActivity(i);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, BrowseProfilePage.class);
        startActivity(i);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, BrowseBookingsPage.class);
        startActivity(i);
    }

}
