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

public class BrowseMap extends Fragment
{
    private Button btnBrowsePage;
    private Button btnBrowseCategoryTabs;
    private Button btnBrowsePriceTabs;
    private Button btnBrowsePopularTabs;
    private Button btnOpenMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup browsing, @Nullable final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.browse_map_view, browsing, false);

        btnBrowsePage = (Button) view.findViewById(R.id.browse_page_btn);
        btnBrowseCategoryTabs = (Button) view.findViewById(R.id.user_browse_category);
        btnBrowsePriceTabs = (Button) view.findViewById(R.id.user_browse_price);
        btnBrowsePopularTabs = (Button) view.findViewById(R.id.user_browse_popular);
        btnOpenMap = (Button) view.findViewById(R.id.open_map_btn);

        btnBrowsePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity)getActivity()).setViewPager(0);
            }
        });

        btnBrowseCategoryTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity)getActivity()).setViewPager(1);
            }
        });

        btnBrowsePriceTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity)getActivity()).setViewPager(2);
            }
        });

        btnBrowsePopularTabs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity)getActivity()).setViewPager(3);
            }
        });

        btnOpenMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((BrowseActivity)getActivity()).setViewPager(4);
            }
        });

        return view;
    }
}