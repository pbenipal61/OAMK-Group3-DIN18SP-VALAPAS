package com.group3.valapas.BrowsePages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group3.valapas.R;

public class BrowsePopularTab extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_popular_tab);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, BrowsePage.class);
        startActivity(i);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, BrowseProfilePage.class);
        startActivity(i);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, BrowseFavoritesPage.class);
        startActivity(i);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, BrowseBookingsPage.class);
        startActivity(i);
    }

    public void selectCategory(View v)
    {
        Intent i = new Intent (this, BrowseCategoryTab.class);
        startActivity(i);
    }

    public void selectPrice(View v)
    {
        Intent i = new Intent (this, BrowsePriceTab.class);
        startActivity(i);
    }
}
