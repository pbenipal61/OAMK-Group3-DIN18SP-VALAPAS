package com.group3.valapas.UserPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.Adapters.CompanyAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserFavorites extends AppCompatActivity implements IReturnCompanySearchResultsCallback
{
    private Button mostRecentButton;
    private Button priceButton;
    private Button mostUsedButton;


    private CompanyAdapter companyAdapter;
    private ListView companiesListView;

    // the data set of the search results
    private ArrayList<String> companyNames = new ArrayList<>();
    private ArrayList<String> companyCategories = new ArrayList<>(); // the first category from the list
    private ArrayList<String> companyDescriptions = new ArrayList<>();
    private ArrayList<String> companyImages = new ArrayList<>(); // the first image from the list
    private ArrayList<String> companyIds = new ArrayList<>();

    private Context context;

    private Set<String> favoriteCompanies = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_favorites);

        context = this;

        mostRecentButton = findViewById(R.id.user_favorites_most_recent_tab);
        priceButton = findViewById(R.id.user_favorites_price_tab);
        mostUsedButton = findViewById(R.id.user_favorites_most_used_tab);

        companiesListView = findViewById(R.id.companiesListView);
        companyAdapter = new CompanyAdapter(this, companyNames, companyCategories, companyDescriptions, companyImages);
        companiesListView.setAdapter(companyAdapter);

        companiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(context, UserCompanyView.class);
                intent.putExtra("CompanyName", companyNames.get(position));
                intent.putExtra("CompanyId", companyIds.get(position));
                startActivity(intent);
            }
        });

        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        favoriteCompanies = sh.getStringSet("favoriteCompanies", new HashSet<String>());

        ApiHandler.searchByCompanyIds(this, (HashSet)favoriteCompanies, this);
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
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    public void selectMostRecent(View v)
    {
        mostRecentButton.setBackgroundColor(getResources().getColor(R.color.SelectedGold));
        priceButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
        mostUsedButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
    }

    public void selectPrice(View v)
    {
        mostRecentButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
        priceButton.setBackgroundColor(getResources().getColor(R.color.SelectedGold));
        mostUsedButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
    }

    public void selectMostUsed(View v)
    {
        mostRecentButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
        priceButton.setBackgroundColor(getResources().getColor(R.color.LightGold));
        mostUsedButton.setBackgroundColor(getResources().getColor(R.color.SelectedGold));
    }

    @Override
    public void returnSearchResults(ArrayList<Company> returnedCompanies)
    {
        // Resetting the lists
        companyNames.clear();
        companyCategories.clear();
        companyDescriptions.clear();
        companyImages.clear();
        companyIds.clear();

        Log.d("AAA", "callback reached");
        for (Company c : returnedCompanies)
        {
            Log.d("AAA", "For1: ");
            companyNames.add(c.getName());
            Log.d("AAA", "For2: ");
            companyCategories.add("sulea");
            Log.d("AAA", "For3: ");
            companyDescriptions.add(c.getDescription());
            companyImages.add("imagine");
            companyIds.add(c.getId());
        }


        //Log.d("AAA", "Name: " + companyNames.get(0));

        UserFavorites.this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {

                companyAdapter.notifyDataSetChanged();
            }
        });
    }
}
