package com.group3.valapas.UserPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnReservationCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Reservation;
import com.group3.valapas.Models.ReservationBuilder;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.R;

public class UserMakeReservation extends AppCompatActivity implements IReturnReservationCallback
{

    private TextView countText;

    private TextView companyName;
    private TextView companyLocation;

    private TextView offeringName;
    private TextView offeringDescription;
    private TextView offeringPrice;

    private TextView dateText;

    private int count = 0;

    private String offeringId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_make_reservation);

        Intent intent = getIntent();

        String companyNameString = intent.getStringExtra("CompanyName");
        String companyLocationString = intent.getStringExtra("CompanyLocation");
        String offeringNameString = intent.getStringExtra("OfferingName");
        String offeringDescriptionString = intent.getStringExtra("OfferingDescription");
        String offeringPriceString = intent.getStringExtra("OfferingPrice");
        offeringId = intent.getStringExtra("OfferingId");

        countText = findViewById(R.id.count);
        companyName = findViewById(R.id.companyName);
        companyLocation = findViewById(R.id.companyLocation);
        offeringName = findViewById(R.id.offeringName);
        offeringDescription = findViewById(R.id.offeringDescription);
        offeringPrice = findViewById(R.id.offeringPrice);
        dateText = findViewById(R.id.date);

        companyName.setText(companyNameString);
        companyLocation.setText(companyLocationString);
        offeringName.setText(offeringNameString);
        offeringDescription.setText(offeringDescriptionString);
        offeringPrice.setText(offeringPriceString);

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

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    public void plusButton(View v)
    {
        count++;
        countText.setText("Count: " + count);
    }

    public void minusButton(View v)
    {
        count--;
        countText.setText("Count: " + count);
    }

    public void makeReservation(View v)
    {
        User user = new UserBuilder().buildUser(ApiHandler.getBearerToken());

        Reservation reservation = new ReservationBuilder()
                .id(user.getId())
                .date(dateText.getText().toString())
                .offering(offeringId)
                .quantity(count)
                .buildReservation();

        ApiHandler.createReservation(this, reservation, this);

    }

    @Override
    public void returnReservation(Reservation reservation)
    {
        Toast.makeText(this, "Reservation created.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserBrowse.class);
        startActivity(intent);
    }
}
