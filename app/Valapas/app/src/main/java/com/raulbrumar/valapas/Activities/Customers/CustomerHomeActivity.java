package com.raulbrumar.valapas.Activities.Customers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.raulbrumar.valapas.Activities.RegisterTypeActivity;
import com.raulbrumar.valapas.CompaniesList;
import com.raulbrumar.valapas.CustomAdapter;
import com.raulbrumar.valapas.R;

import java.util.ArrayList;

public class CustomerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        ListView mListView = (ListView) findViewById(R.id.resultsListView);

        CompaniesList testi = new CompaniesList("Perse", "Oulu");
        CompaniesList testi2 = new CompaniesList("Perse", "Oulu");
        CompaniesList testi3 = new CompaniesList("Perse", "Oulu");
        CompaniesList testi4 = new CompaniesList("Perse", "Oulu");

        ArrayList<CompaniesList> companiesList = new ArrayList<>();
        companiesList.add (testi);
        companiesList.add (testi2);
        companiesList.add (testi3);
        companiesList.add (testi4);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.activity_listing_component, companiesList);
        mListView.setAdapter(adapter);
    }

    public void myProfileClicked(View view)
    {
        Intent intent = new Intent(this, CustomerProfileActivity.class);
        startActivity(intent);
    }

    public void favoritesClicked(View view)
    {
        Intent intent = new Intent(this, CustomerFavoritesActivity.class);
        startActivity(intent);
    }

    public void bookingsClicked(View view)
    {
        Intent intent = new Intent(this, CustomerReservationsActivity.class);
        startActivity(intent);
    }
}
