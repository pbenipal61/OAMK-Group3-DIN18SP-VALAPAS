package com.group3.valapas.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.valapas.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    ArrayList<String> rCategories;
    ArrayList<String> rImages; // not used right now

    public CategoriesAdapter(ArrayList<String> categories)
    {
        this.rCategories = categories;
    }

    public CategoriesAdapter(ArrayList<String> categories, ArrayList<String> images)
    {
        this.rCategories = categories;
        this.rImages = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(rCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return rCategories.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        public TextView title;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.companyCategory);
            //image = itemView.findViewById(R.id.imageVIew);
        }
    }
}
