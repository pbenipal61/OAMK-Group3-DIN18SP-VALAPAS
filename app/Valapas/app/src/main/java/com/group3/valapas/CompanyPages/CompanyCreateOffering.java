package com.group3.valapas.CompanyPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnOfferingCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.Models.Offering;
import com.group3.valapas.Models.OfferingBuilder;
import com.group3.valapas.R;

public class CompanyCreateOffering extends AppCompatActivity implements IReturnOfferingCallback {

    private TextView countText;

    private TextView offeringName;
    private TextView offeringDescription;
    private TextView offeringTags;
    private TextView offeringPrice;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_create_offering);

        offeringName = findViewById(R.id.offeringName);
        offeringDescription = findViewById(R.id.createOfferingDescription);
        offeringPrice = findViewById(R.id.createOfferingPrice);
        offeringTags = findViewById(R.id.createOfferingTags);
        countText = findViewById(R.id.count);

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

    public void createOffering(View v)
    {
        Company company = new CompanyBuilder().buildCompany(ApiHandler.getBearerToken());

        Offering offering = new OfferingBuilder()
                .company(company.getId())
                .offeringType(offeringName.getText().toString())
                .description(offeringDescription.getText().toString())
                .quantity(count)
                .tags(offeringTags.getText().toString())
                .price(Integer.parseInt(offeringPrice.getText().toString()))
                .buildOffering();

        ApiHandler.createOffering(this, offering, this);

    }

    @Override
    public void returnOffering(Offering offering) {
        Log.d("AAA", "Offering created: ");
        Toast.makeText(this, "Offering created", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        startActivity(intent);
    }
}
