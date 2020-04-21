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

public class OfferingAdapter extends ArrayAdapter<String>
{
    Context context;
    ArrayList<String> rOfferingNames = new ArrayList<String>();
    ArrayList<String> rOfferingDescriptions = new ArrayList<String>();
    ArrayList<String> rOfferingPrices = new ArrayList<String>();

    public OfferingAdapter(Context context, ArrayList<String> offeringNames, ArrayList<String> offeringDescriptions, ArrayList<String> offeringPrices) {
        super(context, R.layout.adapter_company, R.id.offeringName, offeringNames);
        this.context = context;
        this.rOfferingNames = offeringNames;
        this.rOfferingDescriptions = offeringDescriptions;
        this.rOfferingPrices = offeringPrices;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("AAA", "Updating view: ");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.adapter_offering, parent, false);
        TextView offeringName = row.findViewById(R.id.offeringName);
        TextView offeringDescription = row.findViewById(R.id.offeringDescription);
        TextView offeringPrice = row.findViewById(R.id.offeringPrice);

        offeringName.setText(rOfferingNames.get(position));
        offeringDescription.setText(rOfferingDescriptions.get(position));
        offeringPrice.setText(rOfferingPrices.get(position));

        return row;
    }
}
