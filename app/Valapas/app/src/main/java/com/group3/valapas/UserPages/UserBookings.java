package com.group3.valapas.UserPages;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.BookingsAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IDeletedReservation;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnReservationsFromSearchCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Reservation;
import com.group3.valapas.Models.ReservationBuilder;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserBookings extends AppCompatActivity implements IReturnReservationsFromSearchCallback, IDeletedReservation
{
    private Button historyButton;
    private Button currentButton;

    private ArrayList<String> companyNames = new ArrayList<>();
    private ArrayList<String> offeringNames = new ArrayList<String>();
    private ArrayList<String> offeringDescriptions = new ArrayList<String>();
    private ArrayList<String> offeringPrices = new ArrayList<String>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> reservationIds = new ArrayList<>();

    private ListView bookingsListView;
    private BookingsAdapter bookingsAdapter;

    private User user;
    private String today;

    private Context context;

    private IDeletedReservation callback;

    private boolean current;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bookings);

        context = this;
        callback = this;

        historyButton = findViewById(R.id.user_bookings_history_tab);
        currentButton = findViewById(R.id.user_bookings_current_tab);

        bookingsListView = findViewById(R.id.user_bookings_results);
        bookingsAdapter = new BookingsAdapter(this, companyNames, offeringNames, offeringDescriptions, offeringPrices, dates);
        bookingsListView.setAdapter(bookingsAdapter);

        bookingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete reservation");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Reservation reservation = new ReservationBuilder().id(reservationIds.get(position)).buildReservation();
                        // Do nothing but close the dialog
                        ApiHandler.deleteReservation(context, reservation, callback);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        user = new UserBuilder().buildUser(ApiHandler.getBearerToken());

        today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        selectCurrent(null);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, UserProfile.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void selectHistory(View v)
    {
        current = false;
        ApiHandler.searchReservationsByUserBeforeDate(this, user, today, this);
        historyButton.setBackgroundColor(getResources().getColor(R.color.SelectedGold));
        currentButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
    }

    public void selectCurrent(View v)
    {
        current = true;
        ApiHandler.searchReservationsByUserFromDate(this, user, today, this);
        historyButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
        currentButton.setBackgroundColor(getResources().getColor(R.color.SelectedGold));
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
        reservationIds.clear();

        for(Reservation reservation : reservations)
        {
            companyNames.add(reservation.getCompanyName());
            offeringNames.add(reservation.getOfferingName());
            offeringDescriptions.add(reservation.getOfferingDescription());
            offeringPrices.add(reservation.getPrice() + " EUR");
            dates.add(getDate(reservation.getDate()));
            reservationIds.add(reservation.getId());
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

    @Override
    public void deletedReservation() {
        Log.d("AAA", "deletedReservation");

        Toast.makeText(this, "Reservation deleted", Toast.LENGTH_SHORT).show();

        if (current)
        {
            selectCurrent(null);
        }
        else
        {
            selectHistory(null);
        }
    }
}
