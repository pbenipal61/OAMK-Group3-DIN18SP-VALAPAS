package com.group3.valapas.CompanyPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.R;

import org.w3c.dom.Text;

public class CompanyHomeActivity extends AppCompatActivity {

    private TextView companyNameTextView;
    private TextView companyDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);

        Company company = new CompanyBuilder().buildCompany(ApiHandler.getBearerToken());

        companyNameTextView = findViewById(R.id.name);
        companyDetailsTextView = findViewById(R.id.details);


        String details = "";
        details += "Address: " + company.getAddress() + "\n";
        details += "Postal code: " + company.getPostalCode() + "\n";
        details += "City: " + company.getCity() + "\n";
        details += "Country: " + company.getCountry() + "\n";
        details += "Description: " + company.getDescription() + "\n";
        details += "Categories: " + "sdfsddsf"/*categoriesString*/ + "\n";
        details += "Email: " + company.getEmail() + "\n";

        companyNameTextView.setText(company.getName());
        companyDetailsTextView.setText(details);
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
