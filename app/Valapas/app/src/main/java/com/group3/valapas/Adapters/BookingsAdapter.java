package com.group3.valapas.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group3.valapas.R;

import java.util.ArrayList;

public class BookingsAdapter extends ArrayAdapter<String>
{
    private Context context;

    private ArrayList<String> rCompanyNames = new ArrayList<>();
    private ArrayList<String> rOfferingNames = new ArrayList<String>();
    private ArrayList<String> rOfferingDescriptions = new ArrayList<String>();
    private ArrayList<String> rOfferingPrices = new ArrayList<String>();
    private ArrayList<String> rDates = new ArrayList<>();

    public BookingsAdapter(Context context, ArrayList<String> companyNames, ArrayList<String> offeringNames, ArrayList<String> offeringDescriptions, ArrayList<String> offeringPrices, ArrayList<String> dates) {
        super(context, R.layout.adapter_reservation, R.id.name, companyNames);
        this.context = context;
        this.rCompanyNames = companyNames;
        this.rOfferingNames = offeringNames;
        this.rOfferingDescriptions = offeringDescriptions;
        this.rOfferingPrices = offeringPrices;
        this.rDates = dates;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("AAA", "Updating view: ");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.adapter_reservation, parent, false);

        TextView companyName = row.findViewById(R.id.name);
        TextView offeringName = row.findViewById(R.id.offeringName);
        TextView offeringDescription = row.findViewById(R.id.offeringDescription);
        TextView offeringPrice = row.findViewById(R.id.offeringPrice);
        TextView date = row.findViewById(R.id.reservationDate);

        companyName.setText(rCompanyNames.get(position));
        offeringName.setText(rOfferingNames.get(position));
        offeringDescription.setText(rOfferingDescriptions.get(position));
        offeringPrice.setText(rOfferingPrices.get(position));
        date.setText(rDates.get(position));

        return row;
    }
}
