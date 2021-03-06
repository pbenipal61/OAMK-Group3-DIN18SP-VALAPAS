package com.group3.valapas.UserPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.group3.valapas.Adapters.OfferingAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnOfferingsFromSearchCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.Offering;
import com.group3.valapas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserCompanyView extends AppCompatActivity implements IReturnCompanySearchResultsCallback, IReturnOfferingsFromSearchCallback
{
    private ImageButton favoriteButton;

    private TextView companyName;
    private TextView companyLocation;
    private ImageView companyImage;
    private TextView companyDescription;

    private OfferingAdapter offeringAdapter;
    private ListView offeringListView;

    // the data set of the search offerings
    private ArrayList<String> offeringNames = new ArrayList<>();
    private ArrayList<String> offeringPrices = new ArrayList<>();
    private ArrayList<String> offeringIds = new ArrayList<>();
    private ArrayList<String> offeringDescriptions = new ArrayList<>();

    private Company company;

    private Context context;

    private boolean isFavorite;

    private Set<String> favoriteCompanies = new HashSet<>();
    private String companyId;

    private static final String TAG = "UserCompanyView";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_company_view);
        Log.d(TAG, "onCreate: here");
        context = this;

        Intent intent = getIntent();

        String name = intent.getStringExtra("CompanyName");
        companyId = intent.getStringExtra("CompanyId");

        Log.d(TAG, "onCreate: "+ companyName + " " + companyId);
        ApiHandler.searchByCompanyName(this, name, this, "name");

        favoriteButton = findViewById(R.id.favoriteButton);
        companyName = findViewById(R.id.offeringName);
        companyName.setText(name);
        companyLocation = findViewById(R.id.companyLocation);
        companyDescription = findViewById(R.id.company_description);
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

        // reading if song is favorite
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        favoriteCompanies = sh.getStringSet("favoriteCompanies", new HashSet<String>());

        isFavorite = false;
        favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_gray_24dp));

        for (String s : favoriteCompanies)
        {
            if (s.equals(companyId))
            {
                isFavorite = true;
                favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_red_24dp));
                break;
            }
        }
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, UserBrowseCategory.class);
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

    public void favoriteButton(View v)
    {
        Log.d("AAA", "favoriteButton: clicked");
        if (isFavorite)
        {
            isFavorite = false;
            favoriteCompanies.remove(companyId);
            favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_gray_24dp));
            Log.d("AAA", "isFavorite: " + isFavorite);
        }
        else
        {
            isFavorite = true;
            favoriteCompanies.add(companyId);
            favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_red_24dp));
            Log.d("AAA", "isFavorite: " + isFavorite);
        }

        // update the sharedPrefs
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putStringSet("favoriteCompanies", favoriteCompanies);
        myEdit.commit();
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
                    if (company.getImages().length != 0)
                        Picasso.get().load(company.getImages()[0]).into(companyImage);
                }
            });

            Log.d("AAA", "calling search offerings");
            ApiHandler.searchOfferingsByCompany(this, company, this);
        }
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

        UserCompanyView.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                offeringAdapter.notifyDataSetChanged();
            }
        });
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
