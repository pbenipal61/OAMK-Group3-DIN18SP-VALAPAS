package com.group3.valapas.RegisterPages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.group3.valapas.R;

public class Register extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void selectUser(View v)
    {
        Intent i = new Intent (this, RegisterUser.class);
        startActivity(i);
    }

    public void selectCompany(View v)
    {
        Intent i = new Intent (this, RegisterCompany.class);
        startActivity(i);
    }
}
