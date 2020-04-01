package com.raulbrumar.valapas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<CompaniesList> {

    private static final String TAG = "CustomAdapter";

    private Context mContext;
    int mResource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CompaniesList> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String city = getItem(position).getCity();

        CompaniesList companiesList = new CompaniesList(name, city);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);

        tvName.setText(name);
        tvCity.setText(city);

        return convertView;
    }
}
