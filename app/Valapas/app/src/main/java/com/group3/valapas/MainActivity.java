package com.group3.valapas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group3.valapas.BrowsePages.BrowsePage;
import com.group3.valapas.CompanyPages.CompanyInformation;
import com.group3.valapas.CompanyPages.CompanyProfile;
import com.group3.valapas.RegisterPages.Register;
import com.group3.valapas.UserPages.UserBrowse;
import com.group3.valapas.R;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectLoginAsUser(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectLoginAsCompany(View v)
    {
        Intent i = new Intent (this, CompanyInformation.class);
        startActivity(i);
    }

    public void selectRegister(View v)
    {
        Intent i = new Intent (this, Register.class);
        startActivity(i);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, BrowsePage.class);
        startActivity(i);
    }

}