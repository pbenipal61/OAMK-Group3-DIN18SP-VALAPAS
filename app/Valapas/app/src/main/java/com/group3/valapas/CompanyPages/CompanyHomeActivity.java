package com.group3.valapas.CompanyPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.R;

public class CompanyHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);
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

    public void selectCreateOffering(View view)
    {
        Intent intent = new Intent(this, CompanyCreateOffering.class);
        startActivity(intent);
    }
}
