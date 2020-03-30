package com.raulbrumar.valapas.Activities.Companies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raulbrumar.valapas.R;

public class CompanyBookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_bookings);
    }

    public void homeClicked(View view)
    {
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        startActivity(intent);
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
}
