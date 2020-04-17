package com.group3.valapas.UserPages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.group3.valapas.Adapters.CategoriesAdapter;
import com.group3.valapas.R;

import java.util.ArrayList;
import java.util.Arrays;

public class UserBrowse extends AppCompatActivity
{
    private RecyclerView categoriesView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter categoriesAdapter;

    private EditText searchText;

    // the array of categories (might have a class later)
    private ArrayList<String> categories = new ArrayList<>();

    private Context context;

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
    }

    public void selectProfile(View v)
    {
        Intent i = new Intent (this, UserProfile.class);
        startActivity(i);
    }

    public void selectFavorites(View v)
    {
        Intent i = new Intent (this, UserFavorites.class);
        startActivity(i);
    }

    public void selectBookings(View v)
    {
        Intent i = new Intent (this, UserBookings.class);
        startActivity(i);
    }

    private void performSearch()
    {
        searchText.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }

}
