package com.group3.valapas.UserPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.BookingsAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnReservationsFromSearchCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Reservation;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.R;

import java.util.ArrayList;

public class UserBookings extends AppCompatActivity implements IReturnReservationsFromSearchCallback
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

        User user = new UserBuilder().buildUser(ApiHandler.getBearerToken());

        ApiHandler.searchReservationsByUser(this, user, this);

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


    @Override
    public void returnReservations(ArrayList<Reservation> reservations)
    {
        // clear the lists
        companyNames.clear();
        offeringNames.clear();
        offeringDescriptions.clear();
        offeringPrices.clear();
        dates.clear();

        for(Reservation reservation : reservations)
        {
            companyNames.add(reservation.getCompanyId());
            offeringNames.add(reservation.getOffering());
            offeringDescriptions.add(reservation.getQuantity() + "");
            offeringPrices.add("10 EUR");
            dates.add(getDate(reservation.getDate()));
        }

        UserBookings.this.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                bookingsAdapter.notifyDataSetChanged();
            }
        });
    }

    private String getDate(String date)
    {
        String strings[] = date.split("T");
        return strings[0];
    }
}
