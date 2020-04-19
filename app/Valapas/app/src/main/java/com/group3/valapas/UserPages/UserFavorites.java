package com.group3.valapas.UserPages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.group3.valapas.Adapters.CompanyAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.Models.Company;
import com.group3.valapas.R;

import java.util.ArrayList;

public class UserFavorites extends AppCompatActivity implements IReturnCompanySearchResultsCallback
{
    private CompanyAdapter companyAdapter;
    private ListView companiesListView;

    // the data set of the search results
    private ArrayList<String> companyNames = new ArrayList<>();
    private ArrayList<String> companyCategories = new ArrayList<>(); // the first category from the list
    private ArrayList<String> companyDescriptions = new ArrayList<>();
    private ArrayList<String> companyImages = new ArrayList<>(); // the first image from the list
    private ArrayList<String> companyIds = new ArrayList<>();

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_favorites);

        context = this;

        companiesListView = findViewById(R.id.companiesListView);
        companyAdapter = new CompanyAdapter(this, companyNames, companyCategories, companyDescriptions, companyImages);
        companiesListView.setAdapter(companyAdapter);

        companiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(context, UserCompanyView.class);
                intent.putExtra("CompanyName", companyNames.get(position));
                startActivity(intent);
            }
        });
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

    @Override
    public void returnSearchResults(ArrayList<Company> returnedCompanies)
    {
        // Reseting the lists
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

        UserFavorites.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) companiesListView.getLayoutParams();
                lp.height = 600 * companyNames.size();
                companiesListView.setLayoutParams(lp);

                companyAdapter.notifyDataSetChanged();
            }
        });
    }
}
