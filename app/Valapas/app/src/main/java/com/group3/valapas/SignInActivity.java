package com.group3.valapas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.group3.valapas.BrowseFragments.BrowseCategory;
import com.group3.valapas.BrowseFragments.BrowseMap;
import com.group3.valapas.BrowseFragments.BrowsePage;
import com.group3.valapas.BrowseFragments.BrowsePopular;
import com.group3.valapas.BrowseFragments.BrowsePrice;
import com.group3.valapas.ValapasStatePagerAdapter;

public class SignInActivity extends AppCompatActivity
{
    public ValapasStatePagerAdapter mValapasStatePagerAdapter;
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mValapasStatePagerAdapter = new ValapasStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.signingin);

        setUpViewPager(mViewPager);
    }

    public void setUpViewPager(ViewPager viewPager)
    {
        ValapasStatePagerAdapter adapter = new ValapasStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BrowsePage(), "BrowsePage");
        adapter.addFragment(new BrowseCategory(), "BrowseCategory");
        adapter.addFragment(new BrowsePrice(), "BrowsePrice");
        adapter.addFragment(new BrowsePopular(), "BrowsePopular");
        adapter.addFragment(new BrowseMap(), "BrowseMap");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
