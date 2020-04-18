package com.group3.valapas.UserPages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.OfferingAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.Offering;
import com.group3.valapas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserCompanyView extends AppCompatActivity implements IReturnCompanySearchResultsCallback
{

    private TextView companyName;
    private TextView companyLocation;
    private ImageView companyImage;
    private TextView companyDescription;

    private OfferingAdapter offeringAdapter;
    private ListView offeringListView;

    // the data set of the search offerings
    private ArrayList<String> offeringNames = new ArrayList<>();
    private ArrayList<String> offeringDescriptions = new ArrayList<>();
    private ArrayList<String> offeringPrices = new ArrayList<>();

    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_company_view);

        Intent intent = getIntent();

        String name = intent.getStringExtra("CompanyName");

        ApiHandler.searchByCompanyName(this, name, this);

        companyName = findViewById(R.id.companyName);
        companyLocation = findViewById(R.id.companyLocation);
        companyDescription = findViewById(R.id.companyDescription);
        companyImage = findViewById(R.id.companyImage);

        offeringListView = findViewById(R.id.offeringsListView);
        offeringAdapter = new OfferingAdapter(this, offeringNames, offeringDescriptions, offeringPrices);
        offeringListView.setAdapter(offeringAdapter);
    }


    @Override
    public void returnSearchResults(ArrayList<Company> returnedCompanies)
    {
        if (returnedCompanies.size() != 0)
        {
            company = returnedCompanies.get(0);

            // Loading the data
            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    companyName.setText(company.getName());
                    companyLocation.setText(getLocation(company));
                    companyDescription.setText(company.getDescription());
                    Picasso.get().load(company.getImages()[0]).into(companyImage);
                }
            });

            // ApiHandler.getOfferings()
        }
    }

    //@Override
    public void returnOfferings(ArrayList<Offering> returnedOfferings)
    {
        for (Offering offering : returnedOfferings)
        {
            offeringNames.add(offering.getOfferingType());
            offeringDescriptions.add("sulea");
            offeringPrices.add(getPrice(offering));
        }
    }


    private String getLocation(Company company)
    {
        return company.getAddress() + ", " + company.getCity() + " " + company.getPostalCode();
    }

    private String getPrice(Offering offering)
    {
        return offering.getPrice() + " EUR";
    }
}
