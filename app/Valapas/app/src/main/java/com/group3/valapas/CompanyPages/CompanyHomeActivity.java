package com.group3.valapas.CompanyPages;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.OfferingAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IDeletedOffering;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnOfferingsFromSearchCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.Models.Offering;
import com.group3.valapas.Models.OfferingBuilder;
import com.group3.valapas.R;

import java.util.ArrayList;

public class CompanyHomeActivity extends AppCompatActivity implements IReturnOfferingsFromSearchCallback, IDeletedOffering
{

    private TextView companyNameTextView;
    private TextView companyDetailsTextView;

    // the data set of the search offerings
    private ArrayList<String> offeringNames = new ArrayList<>();
    private ArrayList<String> offeringDescriptions = new ArrayList<>();
    private ArrayList<String> offeringPrices = new ArrayList<>();
    private ArrayList<String> offeringIds = new ArrayList<>();

    private OfferingAdapter offeringAdapter;
    private ListView offeringListView;

    Company company;
    Context context;
    IDeletedOffering callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_home);

        context = this;
        company = new CompanyBuilder().buildCompany(ApiHandler.getBearerToken());
        callback = this;

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

        offeringListView = findViewById(R.id.offeringsListView);
        offeringAdapter = new OfferingAdapter(this, offeringNames, offeringDescriptions, offeringPrices);
        offeringListView.setAdapter(offeringAdapter);

        offeringListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete reservation");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Offering offering = new OfferingBuilder().id(offeringIds.get(position)).buildOffering();
                        // Do nothing but close the dialog
                        ApiHandler.deleteOffering(context, offering, callback);
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

        ApiHandler.searchOfferingsByCompany(this, company, this);
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

    @Override
    public void returnOfferings(ArrayList<Offering> returnedOfferings)
    {
        Log.d("AAA", "returnOfferings: Offerings callback reached");
        // Resetting the lists
        offeringNames.clear();
        offeringDescriptions.clear();
        offeringPrices.clear();
        offeringIds.clear();

        for (Offering offering : returnedOfferings)
        {
            offeringNames.add(offering.getOfferingType());
            offeringDescriptions.add(offering.getDescription());
            offeringPrices.add(getPrice(offering));
            offeringIds.add(offering.getId());
        }

        CompanyHomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                offeringAdapter.notifyDataSetChanged();
            }
        });
    }

    private String getPrice(Offering offering)
    {
        return offering.getPrice() + " EUR";
    }

    @Override
    public void deletedOffering() {
        Log.d("AAA", "deletedOffering");

        Toast.makeText(this, "Reservation deleted", Toast.LENGTH_SHORT).show();

        ApiHandler.searchOfferingsByCompany(this, company, this);
    }
}
