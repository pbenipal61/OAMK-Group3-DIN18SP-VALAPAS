package com.raulbrumar.valapas.Models;

import android.util.Base64;

import org.json.JSONObject;

public class CompanyBuilder
{
    private String id = "";
    private String name = "";
    private String email = "";
    private String password = "";
    private String description = "";
    private String[] images = new String[0];
    private String address = "";
    private String postalCode = "";
    private String location = "";
    private String city = "";
    private String country = "";
    private String[] categories = new String[0];
    private int[][] openingHours = new int[0][0];
    private String[] priceRange = new String[0];

    public CompanyBuilder() {}

    public Company buildCompany()
    {
        return new Company(id, email, password, name, description, images, address, postalCode, location, city, country, categories, openingHours, priceRange);
    }

    public Company buildCompany(String token)
    {
        String decodedPayload;
        Company company;
        try
        {
            String payload = token.split("\\.")[1];

            byte[] decodedBytes = Base64.decode(payload, Base64.URL_SAFE);
            decodedPayload = new String(decodedBytes, "UTF-8");
            JSONObject dataJSON = new JSONObject(decodedPayload);

            JSONObject userJSON = dataJSON.getJSONObject("company"); // MAKE SURE YOU UPDATE THIS AFTER SEEING EXACTLY WHAT THE API GIVES INSIDE THE TOKEN FOR COMPANIES

            company = new CompanyBuilder().buildCompany();
            /*
            company = firstName(userJSON.getString("firstName"))
                    .id(userJSON.getString("_id"))
                    .lastName(userJSON.getString("lastName"))
                    .isAdult(userJSON.getBoolean("isAdult"))
                    .email(userJSON.getString("email"))
                    .password(userJSON.getString("password"))
                    .city(userJSON.getString("city"))
                    .buildCompany();
        */
        }
        catch (Exception e)
        {
            e.printStackTrace();

            company = buildCompany();
        }

        return company;
    }

    public CompanyBuilder id (String id)
    {
        this.id = id;
        return this;
    }

    public CompanyBuilder name (String name)
    {
        this.name = name;
        return this;
    }

    public CompanyBuilder email(String email)
    {
        this.email = email;
        return this;
    }

    public CompanyBuilder password(String password)
    {
        this.password = password;
        return this;
    }

    public CompanyBuilder description (String description)
    {
        this.description = description;
        return this;
    }

    public CompanyBuilder images (String[] images)
    {
        this.images = images;
        return this;
    }

    public CompanyBuilder address (String address)
    {
        this.address = address;
        return this;
    }

    public CompanyBuilder postalCode (String postalCode)
    {
        this.postalCode = postalCode;
        return this;
    }

    public CompanyBuilder location(String location)
    {
        this.location = location;
        return this;
    }

    public CompanyBuilder city (String city)
    {
        this.city = city;
        return this;
    }

    public CompanyBuilder country (String country)
    {
        this.country = country;
        return this;
    }

    public CompanyBuilder categories (String[] categories)
    {
        this.categories = categories;
        return this;
    }

    public CompanyBuilder openingHours (int[][] openingHours)
    {
        this.openingHours = openingHours;
        return this;
    }

    public CompanyBuilder priceRange (String[] priceRange)
    {
        this.priceRange = priceRange;
        return this;
    }
}
