package com.group3.valapas.UserPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.BookingsAdapter;
import com.group3.valapas.R;

import java.util.ArrayList;

public class UserBookings extends AppCompatActivity
{
    private Button historyButton;
    private Button currentButton;

    private ArrayList<String> companyNames = new ArrayList<>();
    private ArrayList<String> offeringNames = new ArrayList<String>();
    private ArrayList<String> offeringDescriptions = new ArrayList<String>();
    private ArrayList<String> offeringPrices = new ArrayList<String>();
    private ArrayList<String> dates = new ArrayList<>();

    private ListView bookingsListView;
    private BookingsAdapter bookingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bookings);

        historyButton = findViewById(R.id.user_bookings_history_tab);
        currentButton = findViewById(R.id.user_bookings_current_tab);

        bookingsListView = findViewById(R.id.user_bookings_results);
        bookingsAdapter = new BookingsAdapter(this, companyNames, offeringNames, offeringDescriptions, offeringPrices, dates);
        bookingsListView.setAdapter(bookingsAdapter);

    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, UserProfile.class);
        startActivity(i);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
    }

    public void selectMostRecent(View v)
    {
        // sort by date
    }

    public void selectCurrent(View v)
    {
        // sort by active reservation
    }


}
