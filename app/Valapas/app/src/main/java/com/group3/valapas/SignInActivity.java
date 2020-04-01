package com.group3.valapas;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.group3.valapas.ValapasStatePagerAdapter;
import com.group3.valapas.SignUpFragments.SignUpGuide;

public class SignInActivity extends AppCompatActivity
{
    private ValapasStatePagerAdapter mValapasStatePagerAdapter;
    private ViewPager mViewPager;

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
        adapter.addFragment(new SignUpGuide(), "SignUpGuide");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }
}