package com.raulbrumar.valapas.Activities.Companies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.Activities.Customers.CustomerFavoritesActivity;
import com.raulbrumar.valapas.Activities.Customers.CustomerHomeActivity;
import com.raulbrumar.valapas.Activities.Customers.CustomerProfileActivity;
import com.raulbrumar.valapas.Activities.Customers.CustomerReservationsActivity;
import com.raulbrumar.valapas.R;

public class CompanyHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);
    }

    public void updateInfoClicked(View view)
    {
        Intent intent = new Intent(this, CompanyUpdateInfo.class);
        startActivity(intent);
    }

    public void reviewsClicked(View view)
    {
        Intent intent = new Intent(this, CompanyReviewsActivity.class);
        startActivity(intent);
    }

    public void bookingsClicked(View view)
    {
        Intent intent = new Intent(this, CompanyBookingsActivity.class);
        startActivity(intent);
    }
}
