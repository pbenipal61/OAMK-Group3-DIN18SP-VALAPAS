package com.group3.valapas.CompanyPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.group3.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.MainActivity;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.R;

import java.util.Arrays;

public class CompanyUpdateInfo extends AppCompatActivity implements IReturnCompanyCallback
{
    private EditText companyNameEditText;
    private EditText addressEditText;
    private EditText postalCodeEditText;
    private EditText cityEditText;
    private EditText countryEditText;
    private EditText descriptionEditText;
    private EditText tagsET;

    private EditText openingHours1EditText;
    private EditText openingHours2EditText;
    private EditText openingHours3EditText;
    private EditText openingHours4EditText;

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    // private CheckBox checkBox; // needs to be ticked

    // checkboxes for categories
    private CheckBox restaurantCheckBox;
    private CheckBox venueCheckBox;
    private CheckBox entertainmentCheckBox;
    private CheckBox barCheckBox;
    private CheckBox modernCheckBox;
    private CheckBox indoorCheckBox;
    private CheckBox finnishCheckBox;
    private CheckBox traditionalCheckBox;
    private CheckBox outdoorCheckBox;
    private CheckBox internationalCheckBox;

    private static final String TAG = "CompanyUpdateInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_update_info);

        companyNameEditText = findViewById(R.id.name);
        addressEditText = findViewById(R.id.address);
        postalCodeEditText = findViewById(R.id.postalCode);
        cityEditText = findViewById(R.id.city);
        countryEditText = findViewById(R.id.country);
        descriptionEditText = findViewById(R.id.description);
        tagsET = (EditText) findViewById(R.id.tags);

        openingHours1EditText = findViewById(R.id.openingHours1);
        openingHours2EditText = findViewById(R.id.openingHours2);
        openingHours3EditText = findViewById(R.id.openingHours3);
        openingHours4EditText = findViewById(R.id.openingHours4);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);

        // checkBox = findViewById(R.id.checkBox);

        restaurantCheckBox = findViewById(R.id.Restaurant);
        venueCheckBox = findViewById(R.id.Venue);
        entertainmentCheckBox = findViewById(R.id.Entertainment);
        barCheckBox = findViewById(R.id.Bar);
        modernCheckBox = findViewById(R.id.Modern);
        indoorCheckBox = findViewById(R.id.Indoor);
        finnishCheckBox = findViewById(R.id.Finnish);
        traditionalCheckBox = findViewById(R.id.Traditional);
        outdoorCheckBox = findViewById(R.id.Outdoor);
        internationalCheckBox = findViewById(R.id.International);

        Company company = new CompanyBuilder().buildCompany(ApiHandler.getBearerToken());
        companyNameEditText.setText(company.getName());
        addressEditText.setText(company.getAddress());
        postalCodeEditText.setText(company.getPostalCode());
        cityEditText.setText(company.getCity());
        countryEditText.setText(company.getCountry());
        descriptionEditText.setText(company.getDescription());

        emailEditText.setText(company.getEmail());

        if (company.getCategories().contains("Restaurant"))
        {
            restaurantCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Venue"))
        {
            venueCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Entertainment"))
        {
            entertainmentCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Bar"))
        {
            barCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Modern"))
        {
            modernCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Indoor"))
        {
            indoorCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Finnish"))
        {
            finnishCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Traditional"))
        {
            traditionalCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("Outdoor"))
        {
            outdoorCheckBox.setChecked(true);
        }
        if (company.getCategories().contains("International"))
        {
            internationalCheckBox.setChecked(true);
        }
    }

    public void onUpdateClick(View view)
    {
        try{
            String[] tags = tagsET.getText().toString().split(",");
            Log.d(TAG, "onUpdateClick: "+ Arrays.toString(tags));
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

            String categoriesString = "[";
            if (restaurantCheckBox.isChecked())
            {
                categoriesString += "Restaurant,";
            }
            if (venueCheckBox.isChecked())
            {
                categoriesString += "Venue,";
            }
            if (entertainmentCheckBox.isChecked())
            {
                categoriesString += "Entertainment,";
            }
            if (barCheckBox.isChecked())
            {
                categoriesString += "Bar,";
            }
            if (modernCheckBox.isChecked())
            {
                categoriesString += "Modern,";
            }
            if (indoorCheckBox.isChecked())
            {
                categoriesString += "Indoor,";
            }
            if (finnishCheckBox.isChecked())
            {
                categoriesString += "Finnish,";
            }
            if (traditionalCheckBox.isChecked())
            {
                categoriesString += "Traditional,";
            }
            if (outdoorCheckBox.isChecked())
            {
                categoriesString += "Outdoor,";
            }
            if (internationalCheckBox.isChecked())
            {
                categoriesString += "International,";
            }

            categoriesString = categoriesString.substring(0, categoriesString.length() - 1);
            if (!categoriesString.equals(""))
                categoriesString += "]";

            Log.d("AAA", "categoriesString: " + categoriesString);

            if (!password.equals(confirmPassword))
            {
                Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT).show();
            }
            // else if (!checkBox.isChecked())
            // {
            // Toast.makeText(this, "You must agree to the Terms and Conditions first", Toast.LENGTH_SHORT).show();
            // }
            else
            {
                String hours = "[" + openingHours1 + "," + openingHours2 + "],[" + openingHours3 + "," + openingHours4 + "]";

                Company companyForId = new CompanyBuilder().buildCompany(ApiHandler.getBearerToken());

                Company company = new CompanyBuilder()
                        .id(companyForId.getId())
                        .email(email).password(password)
                        .country(country)
                        .city(city)
                        .location("")
                        .address(address)
                        .description(description)
                        .name(name)
                        .postalCode(postalCode)
                        .openingHours(hours)
                        .categories(categoriesString)
                        .tags(tags)
                        .buildCompany();
                ApiHandler.editCompany(this, company, this);
            }

        }
        catch (Exception ex){
            Log.d(TAG, "onUpdateClick: " + ex.getMessage());
        }
    }

    @Override
    public void returnCompany(Company company)
    {
        Toast.makeText(this, "Company account created successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onHomeClick(View view)
    {
        Intent intent = new Intent(this, CompanyHomeActivity.class);
        startActivity(intent);
    }

    public void onUpdateInfoClick(View view)
    {
        Intent intent = new Intent(this, CompanyUpdateInfo.class);
        startActivity(intent);
    }

    public void onReviewsClick(View view)
    {
        Intent intent = new Intent(this, CompanyReviewsActivity.class);
        startActivity(intent);
    }

    public void onBookingsClick(View view)
    {
        Intent intent = new Intent(this, CompanyBookingsActivity.class);
        startActivity(intent);
    }

    public void onLogoutClick(View view)
    {
        ApiHandler.logOut(this);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
