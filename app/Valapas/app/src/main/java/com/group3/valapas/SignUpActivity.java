package com.group3.valapas;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.group3.valapas.SignUpFragments.SignUpCompany;
import com.group3.valapas.SignUpFragments.SignUpGuide;
import com.group3.valapas.SignUpFragments.SignUpUser;
import com.group3.valapas.ValapasStatePagerAdapter;

public class SignUpActivity extends AppCompatActivity
{
    private ValapasStatePagerAdapter mValapasStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mValapasStatePagerAdapter = new ValapasStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.signingup);

        setUpViewPager(mViewPager);
    }

    public void setUpViewPager(ViewPager viewPager)
    {
        ValapasStatePagerAdapter adapter = new ValapasStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SignUpGuide(), "SignUpGuide");
        adapter.addFragment(new SignUpUser(), "SignUpUser");
        adapter.addFragment(new SignUpCompany(), "SignUpCompany");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
