package com.group3.valapas.UserPages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
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

import java.util.Calendar;

public class UserMakeReservation extends AppCompatActivity implements IReturnReservationCallback, DatePickerDialog.OnDateSetListener
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

        dateText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDatePickedDialog();
            }
        });

        companyName.setText(companyNameString);
        companyLocation.setText(companyLocationString);
        offeringName.setText(offeringNameString);
        offeringDescription.setText(offeringDescriptionString);
        offeringPrice.setText(offeringPriceString);

    }

    private void showDatePickedDialog()
    {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
        dialog.show();
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
        if (count == 0)
        {
            Toast.makeText(this, "Please enter count.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dateText.getText().toString().equals("Select Date"))
        {
            Toast.makeText(this, "Please enter a date.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new UserBuilder().buildUser(ApiHandler.getBearerToken());

        Reservation reservation = new ReservationBuilder()
                .customer(user.getId())
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month+=1;
        String date = month < 10 ? "0" + month + "/" : month + "/";
        date += dayOfMonth < 10 ? "0" + dayOfMonth + "/" : dayOfMonth + "/";
        date += year;
        dateText.setText(date);
    }
}
