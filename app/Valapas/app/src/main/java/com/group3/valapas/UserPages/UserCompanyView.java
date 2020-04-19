package com.group3.valapas.UserPages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<String> offeringIds = new ArrayList<>();

    private Company company;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_company_view);

        context = this;

        Intent intent = getIntent();

        String name = intent.getStringExtra("CompanyName");

        ApiHandler.searchByCompanyName(this, name, this);

        companyName = findViewById(R.id.offeringName);
        companyLocation = findViewById(R.id.companyLocation);
        companyDescription = findViewById(R.id.companyDescription);
        companyImage = findViewById(R.id.companyImage);

        offeringListView = findViewById(R.id.offeringsListView);
        offeringAdapter = new OfferingAdapter(this, offeringNames, offeringDescriptions, offeringPrices);
        offeringListView.setAdapter(offeringAdapter);

        offeringListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(context, UserMakeReservation.class);
                intent.putExtra("CompanyName", companyName.getText().toString());
                intent.putExtra("CompanyLocation", companyLocation.getText().toString());
                intent.putExtra("OfferingName", offeringNames.get(position));
                intent.putExtra("OfferingDescription", offeringDescriptions.get(position));
                intent.putExtra("OfferingPrice", offeringPrices.get(position));
                intent.putExtra("OfferingId", offeringIds.get(position));

                startActivity(intent);
            }
        });
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

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, UserProfile.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    //@Override
    public void returnOfferings(ArrayList<Offering> returnedOfferings)
    {
        for (Offering offering : returnedOfferings)
        {
            offeringNames.add(offering.getOfferingType());
            offeringDescriptions.add("sulea");
            offeringPrices.add(getPrice(offering));
            offeringIds.add(offering.getId());
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
