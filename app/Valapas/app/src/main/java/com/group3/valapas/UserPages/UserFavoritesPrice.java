package com.group3.valapas.UserPages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.group3.valapas.R;

public class UserFavoritesPrice extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_favorites_price);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    public void selectMostRecent(View v)
    {
        Intent i = new Intent (this, UserFavoritesMostRecent.class);
        startActivity(i);
    }

    public void selectMostUsed(View v)
    {
        Intent i = new Intent (this, UserFavoritesMostUsed.class);
        startActivity(i);
    }
}
