package com.group3.valapas.CompanyPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.BookingsAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnReservationsFromSearchCallback;
import com.group3.valapas.Models.Reservation;
import com.group3.valapas.Models.User;
import com.group3.valapas.R;

import java.util.ArrayList;

public class CompanyBookingsActivity extends AppCompatActivity implements IReturnReservationsFromSearchCallback {

    private Button historyButton;
    private Button currentButton;

    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> offeringNames = new ArrayList<String>();
    private ArrayList<String> offeringDescriptions = new ArrayList<String>();
    private ArrayList<String> offeringPrices = new ArrayList<String>();
    private ArrayList<String> dates = new ArrayList<>();

    private ListView bookingsListView;
    private BookingsAdapter bookingsAdapter;

    private User user;
    private String today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_bookings);

        historyButton = findViewById(R.id.user_bookings_history_tab);
        currentButton = findViewById(R.id.user_bookings_current_tab);

        bookingsListView = findViewById(R.id.user_bookings_results);
        bookingsAdapter = new BookingsAdapter(this, userNames, offeringNames, offeringDescriptions, offeringPrices, dates);
        bookingsListView.setAdapter(bookingsAdapter);

    }

    public void onHomeClick(View view)
    {
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        startActivity(intent);
    }

    public void onUpdateInfoClick(View view)
    {
        Intent intent = new Intent(this, CompanyUpdateInfo.class);
        startActivity(intent);
    }

    public void onReviewsClick(View view)
    {
        Intent intent = new Intent(this, CompanyReviewsActivity.class);
        startActivity(intent);
    }

    public void onBookingsClick(View view)
    {
        Intent intent = new Intent(this, CompanyBookingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void returnReservations(ArrayList<Reservation> reservations)
    {
        // clear the lists
        userNames.clear();
        offeringNames.clear();
        offeringDescriptions.clear();
        offeringPrices.clear();
        dates.clear();

        for(Reservation reservation : reservations)
        {
            userNames.add(reservation.getCustomerName());
            offeringNames.add(reservation.getOfferingName());
            offeringDescriptions.add(reservation.getOfferingDescription());
            offeringPrices.add(reservation.getPrice() + " EUR");
            dates.add(getDate(reservation.getDate()));
        }

        CompanyBookingsActivity.this.runOnUiThread( new Runnable() {
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
