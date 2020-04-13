package com.group3.valapas.CompanyPages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.group3.valapas.R;

public class CompanyProfile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_profile);
    }

    public void selectCustomerView(View v)
    {
        Intent i = new Intent (this, CompanyCustomerView.class);
        startActivity(i);
    }

    public void selectCompanyInformation(View v)
    {
        Intent i = new Intent (this, CompanyInformation.class);
        startActivity(i);
    }

}
