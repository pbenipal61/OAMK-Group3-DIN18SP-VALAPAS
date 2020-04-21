package com.group3.valapas.RegisterPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.MainActivity;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.R;

public class RegisterCompany extends AppCompatActivity implements IReturnCompanyCallback
{
    private EditText companyNameEditText;
    private EditText addressEditText;
    private EditText postalCodeEditText;
    private EditText cityEditText;
    private EditText countryEditText;
    private EditText descriptionEditText;

    private EditText openingHours1EditText;
    private EditText openingHours2EditText;
    private EditText openingHours3EditText;
    private EditText openingHours4EditText;

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_company);

        companyNameEditText = findViewById(R.id.name);
        addressEditText = findViewById(R.id.address);
        postalCodeEditText = findViewById(R.id.postalCode);
        cityEditText = findViewById(R.id.city);
        countryEditText = findViewById(R.id.country);
        descriptionEditText = findViewById(R.id.description);

        openingHours1EditText = findViewById(R.id.openingHours1);
        openingHours2EditText = findViewById(R.id.openingHours2);
        openingHours3EditText = findViewById(R.id.openingHours3);
        openingHours4EditText = findViewById(R.id.openingHours4);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);

        checkBox = findViewById(R.id.checkBox);
    }

    public void onRegisterClick(View view)
    {
        String name = companyNameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String postalCode = postalCodeEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String country = countryEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        String openingHours1 = openingHours1EditText.getText().toString();
        String openingHours2 = openingHours2EditText.getText().toString();
        String openingHours3 = openingHours3EditText.getText().toString();
        String openingHours4 = openingHours4EditText.getText().toString();

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

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
            Company company = new CompanyBuilder().email(email).password(password).country(country).city(city).location("").address(address).description(description).name(name).postalCode(postalCode).buildCompany();
            ApiHandler.registerCompany(this, company, this);
        }
    }

    @Override
    public void returnCompany(Company company)
    {
        Toast.makeText(this, "Company account created successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
