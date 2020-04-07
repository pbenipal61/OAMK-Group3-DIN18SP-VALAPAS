package com.group3.valapas.BrowseFragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.group3.valapas.BrowseActivity;
import com.group3.valapas.R;

public class BrowsePage extends Fragment
{
    private Button btnBrowsePage;
    private Button btnBrowseCategoryTabs;
    private Button btnBrowsePriceTabs;
    private Button btnBrowsePopularTabs;
    private Button btnProfilePage;
    private Button btnFavoritesPage;
    private Button btnBookingsPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup browsing, @Nullable final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.browse, browsing, false);

        btnBrowsePage = (Button) view.findViewById(R.id.no_user_browse_page);
        btnProfilePage = (Button) view.findViewById(R.id.no_user_profile_page);
        btnFavoritesPage = (Button) view.findViewById(R.id.no_user_favorites_page);
        btnBookingsPage = (Button) view.findViewById(R.id.no_user_bookings_page);
        btnBrowseCategoryTabs = (Button) view.findViewById(R.id.no_user_browse_category);
        btnBrowsePriceTabs = (Button) view.findViewById(R.id.no_user_browse_price);
        btnBrowsePopularTabs = (Button) view.findViewById(R.id.no_user_browse_popular);

        btnBrowsePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(0);
            }
        });

        btnProfilePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(1);
            }
        });

        btnFavoritesPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(2);
            }
        });

        btnBookingsPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(3);
            }
        });

        btnBrowseCategoryTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(4);
            }
        });

        btnBrowsePriceTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(5);
            }
        });

        btnBrowsePopularTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity) getActivity()).setViewPager(6);
            }
        });

        return view;
    }
}