package com.group3.valapas.UserPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.group3.valapas.Adapters.CategoriesAdapter;
import com.group3.valapas.Adapters.CompanyAdapter;
import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanySearchResultsCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.CompanyPages.CompanyCustomerView;
import com.group3.valapas.Dialogs.ISortSelected;
import com.group3.valapas.Dialogs.SortDialog;
import com.group3.valapas.Models.Company;
import com.group3.valapas.R;

import java.util.ArrayList;
import java.util.Arrays;

public class UserBrowse extends AppCompatActivity implements ISortSelected, IReturnCompanySearchResultsCallback
{
    private RecyclerView categoriesView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter categoriesAdapter;

    private EditText searchText;

    private CompanyAdapter companyAdapter;
    private ListView companiesListView;

    // the array of categories (might have a class later)
    private ArrayList<String> categories = new ArrayList<>();

    // the data set of the search results
    private ArrayList<String> companyNames = new ArrayList<>();
    private ArrayList<String> companyCategories = new ArrayList<>(); // the first category from the list
    private ArrayList<String> companyDescriptions = new ArrayList<>();
    private ArrayList<String> companyImages = new ArrayList<>(); // the first image from the list
    private ArrayList<String> companyIds = new ArrayList<>();

    private Context context;

    private String sort;
    private int sortIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_browse);

        context = this;

        categories = new ArrayList<>(Arrays.asList("Restaurants", "Fast Food", "Sports", "Leisure", "Entertainment", "VR"));

        categoriesView = findViewById(R.id.categoriesView);
        categoriesView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoriesView.setLayoutManager(layoutManager);
        categoriesAdapter = new CategoriesAdapter(categories);
        categoriesView.setAdapter(categoriesAdapter);

        searchText = findViewById(R.id.searchText);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        companiesListView = findViewById(R.id.companiesListView);
        companyAdapter = new CompanyAdapter(this, companyNames, companyCategories, companyDescriptions, companyImages);
        companiesListView.setAdapter(companyAdapter);

        companiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(context, CompanyCustomerView.class);
                intent.putExtra("CompanyId", companyIds.get(position));
                startActivity(intent);
            }
        });

        Button sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSort();
            }
        });
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

    private void changeSort()
    {
        Log.d("AAA", "Button CLicked\n");
        SortDialog sortDialog = new SortDialog(sortIndex, this);
        sortDialog.show(getSupportFragmentManager(), "tag");
    }

    private void performSearch()
    {
        searchText.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchText.getWindowToken(), 0);

        ApiHandler.searchByCompanyName(this, searchText.getText().toString(), this);
    }


    @Override
    public void sortSelected(String selection, int index) {
        sort = selection;
        sortIndex = index;
    }

    @Override
    public void returnSearchResults(ArrayList<Company> returnedCompanies) {

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

        UserBrowse.this.runOnUiThread(new Runnable() {
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
