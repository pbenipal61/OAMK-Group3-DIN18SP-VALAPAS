package com.group3.valapas.UserFragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.group3.valapas.R;
import com.group3.valapas.UserActivity;

public class UserBookings extends Fragment
{
    private Button btnBookingsPage;
    private Button btnBookingsCurrent;
    private Button btnBookingsMostRecent;
    private Button btnBookingsMostUsed;
    private Button btnBrowsePage;
    private Button btnBrowseCategory;
    private Button btnBrowsePrice;
    private Button btnBrowsePopular;
    private Button btnFavoritesPage;
    private Button btnFavoritesMostRecent;
    private Button btnFavoritesMostUsed;
    private Button btnFavoritesPrice;
    private Button btnProfilePage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup browsing, @Nullable final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.user_bookings_tab, browsing, false);

        btnBookingsPage = (Button) view.findViewById(R.id.user_bookings_page);
        btnBookingsCurrent = (Button) view.findViewById(R.id.user_bookings_current);
        btnBookingsMostRecent = (Button) view.findViewById(R.id.user_bookings_recent);
        btnBookingsMostUsed = (Button) view.findViewById(R.id.user_bookings_used);
        btnBrowsePage = (Button) view.findViewById(R.id.user_browse_page);
        btnBrowseCategory = (Button) view.findViewById(R.id.user_browse_category);
        btnBrowsePrice = (Button) view.findViewById(R.id.user_browse_price);
        btnBrowsePopular = (Button) view.findViewById(R.id.user_browse_popular);
        btnFavoritesPage = (Button) view.findViewById(R.id.user_favorites_page);
        btnFavoritesMostRecent = (Button) view.findViewById(R.id.user_favorites_recent);
        btnFavoritesMostUsed = (Button) view.findViewById(R.id.user_favorites_used);
        btnFavoritesPrice = (Button) view.findViewById(R.id.user_favorites_price);
        btnProfilePage = (Button) view.findViewById(R.id.user_profile_page);

        btnBookingsPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(0);
            }
        });

        btnBookingsCurrent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(1);
            }
        });

        btnBookingsMostRecent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(2);
            }
        });

        btnBookingsMostUsed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(3);
            }
        });

        btnBrowsePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(4);
            }
        });

        btnBrowseCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(5);
            }
        });

        btnBrowsePopular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(6);
            }
        });

        btnBrowsePrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(7);
            }
        });

        btnFavoritesPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(8);
            }
        });

        btnFavoritesMostRecent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(9);
            }
        });

        btnFavoritesMostUsed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(10);
            }
        });

        btnFavoritesPrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(11);
            }
        });

        btnProfilePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((UserActivity)getActivity()).setViewPager(12);
            }
        });

        return view;
    }
}
