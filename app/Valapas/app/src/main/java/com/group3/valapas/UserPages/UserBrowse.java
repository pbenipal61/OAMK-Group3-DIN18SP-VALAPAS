package com.group3.valapas.UserPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.group3.valapas.R;

public class UserBrowse extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bookings_most_used);
    }

    public void selectCategory(View v)
    {
        Intent i = new Intent (this, UserBrowseCategory.class);
        startActivity(i);
    }

    public void selectPrice(View v)
    {
        Intent i = new Intent (this, UserBrowsePrice.class);
        startActivity(i);
    }

    public void selectPopular(View v)
    {
        Intent i = new Intent (this, UserBrowsePopular.class);
        startActivity(i);
    }
}
