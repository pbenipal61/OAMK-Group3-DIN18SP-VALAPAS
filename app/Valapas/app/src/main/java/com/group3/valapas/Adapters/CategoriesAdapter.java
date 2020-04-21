package com.group3.valapas.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.valapas.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>
{

    private ArrayList<String> rCategories;
    private ArrayList<String> rImages; // not used right

    private OnItemClickListener onItemClickListener;

    private int itemIndex = -1;

    public CategoriesAdapter(ArrayList<String> categories, OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
        this.rCategories = categories;
    }

    public CategoriesAdapter(ArrayList<String> categories, ArrayList<String> images)
    {
        this.rCategories = categories;
        this.rImages = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(rCategories.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIndex = position;
                notifyDataSetChanged();
            }
        });
        if(itemIndex == position)
        {
            holder.layout.setBackgroundResource(R.drawable.red_border_button_round);
        }
        else
        {
            holder.layout.setBackgroundResource(R.drawable.white_button_round);
        }
    }

    @Override
    public int getItemCount()
    {
        return rCategories.size();
    }

    public void resetIndex()
    {
        itemIndex = -1;
        notifyDataSetChanged();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView title;
        public ImageView image;

        public LinearLayout layout;

        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            this.onItemClickListener = onItemClickListener;

            title = itemView.findViewById(R.id.offeringDescription);
            image = itemView.findViewById(R.id.imageView);
            layout = itemView.findViewById(R.id.categoryLayout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());


        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

}
