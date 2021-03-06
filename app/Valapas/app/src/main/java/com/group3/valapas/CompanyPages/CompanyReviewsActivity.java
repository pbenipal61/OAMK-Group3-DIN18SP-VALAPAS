package com.group3.valapas.CompanyPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group3.valapas.R;

public class CompanyReviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_reviews);
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
}
