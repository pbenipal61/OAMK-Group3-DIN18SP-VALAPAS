package com.group3.valapas.RegisterPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.R;

public class RegisterCompanyActivity extends AppCompatActivity implements IReturnCompanyCallback
{
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText countryEditText;
    private EditText cityEditText;
    private EditText locationEditText;
    private EditText addressEditText;
    private EditText descriptionEditText;

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        countryEditText = findViewById(R.id.country);
        cityEditText = findViewById(R.id.city);
        locationEditText = findViewById(R.id.location);
        addressEditText = findViewById(R.id.address);
        descriptionEditText = findViewById(R.id.description);

        checkBox = findViewById(R.id.checkBox);
    }

    public void onRegisterClick(View view)
    {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String country = countryEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        if (!password.equals(confirmPassword))
        {
            Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT).show();
        }
        else if (!checkBox.isChecked())
        {
            Toast.makeText(this, "You must agree to the Terms and Conditions first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Company company = new CompanyBuilder().email(email).password(password).country(country).city(city).location(location).address(address).description(description).buildCompany();
            ApiHandler.registerCompany(this, company, this);
        }
    }

    @Override
    public void returnCompany(Company company)
    {
        Toast.makeText(this, "Company account created successfully!", Toast.LENGTH_SHORT).show();
    }
}
