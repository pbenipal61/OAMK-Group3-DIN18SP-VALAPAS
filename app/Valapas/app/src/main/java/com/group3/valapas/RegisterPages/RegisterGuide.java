package com.group3.valapas.RegisterPages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.group3.valapas.R;

public class RegisterGuide extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_guide);
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
