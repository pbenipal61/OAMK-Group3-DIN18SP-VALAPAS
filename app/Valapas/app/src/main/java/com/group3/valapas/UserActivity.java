package com.group3.valapas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.group3.valapas.UserFragments.UserBookings;
import com.group3.valapas.UserFragments.UserBookingsCurrent;
import com.group3.valapas.UserFragments.UserBookingsMostRecent;
import com.group3.valapas.UserFragments.UserBookingsMostUsed;
import com.group3.valapas.UserFragments.UserBrowse;
import com.group3.valapas.UserFragments.UserBrowseCategory;
import com.group3.valapas.UserFragments.UserBrowsePopular;
import com.group3.valapas.UserFragments.UserBrowsePrice;
import com.group3.valapas.UserFragments.UserFavorites;
import com.group3.valapas.UserFragments.UserFavoritesMostRecent;
import com.group3.valapas.UserFragments.UserFavoritesMostUsed;
import com.group3.valapas.UserFragments.UserFavoritesPrice;
import com.group3.valapas.UserFragments.UserOpenMap;
import com.group3.valapas.UserFragments.UserProfile;
import com.group3.valapas.ValapasStatePagerAdapter;

public class UserActivity extends AppCompatActivity
{
    public ValapasStatePagerAdapter mValapasStatePagerAdapter;
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        mValapasStatePagerAdapter = new ValapasStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.browsing);

        setUpViewPager(mViewPager);
    }

    public void setUpViewPager(ViewPager viewPager)
    {
        ValapasStatePagerAdapter adapter = new ValapasStatePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new UserBookings(), "UserBookings");
        adapter.addFragment(new UserBookingsCurrent(), "UserBookingsCurrent");
        adapter.addFragment(new UserBookingsMostRecent(), "UserBookingsMostRecent");
        adapter.addFragment(new UserBookingsMostUsed(), "UserBookingsMostUsed");
        adapter.addFragment(new UserBrowse(), "UserBrowse");
        adapter.addFragment(new UserBrowseCategory(), "UserBrowseCategory");
        adapter.addFragment(new UserBrowsePopular(), "UserBrowsePopular");
        adapter.addFragment(new UserBrowsePrice(), "UserBrowsePrice");
        adapter.addFragment(new UserFavorites(), "UserFavorites");
        adapter.addFragment(new UserFavoritesMostRecent(), "UserFavoritesMostRecent");
        adapter.addFragment(new UserFavoritesMostUsed(), "UserFavoritesMostUsed");
        adapter.addFragment(new UserFavoritesPrice(), "UserFavoritesPrice");
        adapter.addFragment(new UserOpenMap(), "UserOpenMap");
        adapter.addFragment(new UserProfile(), "UserProfile");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
