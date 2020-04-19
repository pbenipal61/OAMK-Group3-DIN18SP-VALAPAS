package com.group3.valapas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.group3.valapas.ApiHandler.ApiCallbacks.IDeletedOffering;
import com.group3.valapas.ApiHandler.ApiCallbacks.IDeletedReservation;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnOfferingCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnOfferingsFromSearchCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnReservationCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.BrowsePages.BrowsePage;
import com.group3.valapas.CompanyPages.CompanyHomeActivity;
import com.group3.valapas.CompanyPages.CompanyInformation;
import com.group3.valapas.CompanyPages.CompanyProfile;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.Models.Offering;
import com.group3.valapas.Models.OfferingBuilder;
import com.group3.valapas.Models.Reservation;
import com.group3.valapas.Models.ReservationBuilder;
import com.group3.valapas.Models.User;
import com.group3.valapas.Models.UserBuilder;
import com.group3.valapas.RegisterPages.Register;
import com.group3.valapas.UserPages.UserBrowse;
import com.group3.valapas.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IReturnCompanyCallback, IReturnUserCallback, IReturnCompanySearchResultsCallback, IReturnOfferingCallback, IDeletedOffering, IReturnReservationCallback, IDeletedReservation, IReturnOfferingsFromSearchCallback {
    private EditText emailEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        if (ApiHandler.readBearerToken(this) == 0) // no logged in user or company found
        {
            Log.d("AAA", "No user of company logged in");
        }
        else if (ApiHandler.readBearerToken(this) == 1) // customer is already logged in
        {
            Log.d("AAA", "Customer is logged in");

            Intent i = new Intent (this, UserBrowse.class);
            startActivity(i);
        }
        else if (ApiHandler.readBearerToken(this) == -1) // company is already logged in
        {
            Log.d("AAA", "Company is logged in");

            Intent i = new Intent (this, CompanyHomeActivity.class);
            startActivity(i);
        }
    }

    public void selectLoginAsUser(View v)
    {
        /*
        User user = new UserBuilder().email(emailEditText.getText().toString()).password(passwordEditText.getText().toString()).buildUser();
        ApiHandler.loginUser(this, user, this);
        */

        // ApiHandler.searchByCompanyName(this, "Test company", this);
        // ApiHandler.searchByCompanyCategory(this, "Food", this);

        // Offering offering = new OfferingBuilder()
        //        .company("5e946541b8a68d1dd153398b")
        //        .offeringType("Table of 29")
        //        .description("Nice table near the window")
        //        .quantity(10)
        //        .tags("some tags")
        //        .price(100)
        //        .buildOffering();
        // ApiHandler.editOffering(this, offering, this);
        // ApiHandler.deleteOffering(this, offering, this);

        // Reservation reservation = new ReservationBuilder().id("5e9c3a90a688254bdde6f8ee").buildReservation();
        // ApiHandler.createReservation(this, reservation, this);
        // ApiHandler.deleteReservation(this, reservation, this);

        Company company = new CompanyBuilder().id("5e946541b8a68d1dd153398b").buildCompany();
        ApiHandler.searchOfferingsByCompany(this, company, this);
    }

    public void selectLoginAsCompany(View v)
    {
        Company company = new CompanyBuilder().email(emailEditText.getText().toString()).password(passwordEditText.getText().toString()).buildCompany();
        ApiHandler.loginCompany(this, company, this);
    }

    public void selectRegister(View v)
    {
        Intent i = new Intent (this, Register.class);
        startActivity(i);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    @Override
    public void returnCompany(Company company)
    {
        ApiHandler.writeBearerToken(this, true);

        Intent i = new Intent (this, CompanyHomeActivity.class);
        startActivity(i);
    }

    @Override
    public void returnUser(User user)
    {
        ApiHandler.writeBearerToken(this, false);

        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    @Override
    public void returnSearchResults(ArrayList<Company> returnedCompanies) {
        for(int i = 0; i < returnedCompanies.size(); i++)
        {
            Log.d("AAA", "Companies found by search: ");
            Log.d("AAA", returnedCompanies.get(i).toString());
        }
    }

    @Override
    public void returnOffering(Offering offering) {
        Log.d("AAA", "offering updated");
    }

    @Override
    public void deletedOffering() {
        Log.d("AAA", "offering deleted");
    }

    @Override
    public void returnReservation(Reservation reservation) {
        Log.d("AAA", "reservation created");
    }

    @Override
    public void deletedReservation() {
        Log.d("AAA", "reservation deleted");
    }

    @Override
    public void returnOfferings(ArrayList<Offering> offerings) {
        Log.d("AAA", "returnOfferings: " + offerings.get(0).getDescription() + " ::: " + offerings.get(1).getDescription());
    }
}