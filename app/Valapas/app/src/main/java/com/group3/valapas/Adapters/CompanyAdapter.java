package com.group3.valapas.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group3.valapas.R;

import java.util.ArrayList;

public class CompanyAdapter extends ArrayAdapter<String>
{
    Context context;
    ArrayList<String> rCompanyNames = new ArrayList<String>();
    ArrayList<String> rCategories = new ArrayList<String>();
    ArrayList<String> rCompanyDescriptions = new ArrayList<String>();
    ArrayList<String> rImages = new ArrayList<String>();

    public CompanyAdapter(Context context, ArrayList<String> companyNames, ArrayList<String> categories, ArrayList<String> companyDescriptions, ArrayList<String> images)
    {
        super(context, R.layout.adapter_company, R.id.companyName, companyNames);
        this.context = context;
        this.rCompanyNames = companyNames;
        this.rCategories = categories;
        this.rCompanyDescriptions = companyDescriptions;
        this.rImages = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Log.d("AAA", "Updating view: ");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.adapter_company, parent, false);
        TextView companyName = row.findViewById(R.id.companyName);
        TextView companyCategory = row.findViewById(R.id.offeringDescription);
        TextView companyDescription = row.findViewById(R.id.offeringPrice);
        ImageView companyImage = row.findViewById(R.id.companyImage);

        companyName.setText(rCompanyNames.get(position));
        companyCategory.setText(rCategories.get(position));
        companyDescription.setText(rCompanyDescriptions.get(position));
        //Picasso.get().load(rImages.get(position)).into(companyImage);

        /*if (position % 2 == 1)
            row.setBackgroundColor(Color.rgb(235, 235, 235));
        */
        return row;
    }

}
