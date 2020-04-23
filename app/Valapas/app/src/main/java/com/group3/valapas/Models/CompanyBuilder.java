package com.group3.valapas.Models;

import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

public class CompanyBuilder
{
    private String id = "";
    private String name = "";
    private String email = "";
    private String password = "";
    private String description = "";
    private String[] images = null;
    private String address = "";
    private String postalCode = "";
    private String location = "";
    private String city = "";
    private String country = "";
    private String categories = "";
    private String openingHours = "";
    private String[] priceRange = null;
    private String[] tags = null;

    public CompanyBuilder() {}

    public Company buildCompany()
    {
        return new Company(id, email, password, name, description, images, address, postalCode, location, city, country, categories, openingHours, priceRange, tags);
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

            JSONObject companyJSON = dataJSON.getJSONObject("company"); // MAKE SURE YOU UPDATE THIS AFTER SEEING EXACTLY WHAT THE API GIVES INSIDE THE TOKEN FOR COMPANIES

            JSONArray imagesJSON = companyJSON.getJSONArray("images");
            String[] imagesArray = new String[imagesJSON.length()];
            for (int k = 0; k < imagesJSON.length(); k++) {
                imagesArray[k] = imagesJSON.getString(k);
            }

            /*
            JSONArray categoriesJSON = companyJSON.getJSONArray("categories");
            String[] categoriesArray = new String[categoriesJSON.length()];
            for (int k = 0; k < categoriesJSON.length(); k++) {
                categoriesArray[k] = categoriesJSON.getString(k);
            }
            */

            /*
            JSONArray openingHoursJSON = companyJSON.getJSONArray("openingHours");
            int[][] openingHoursArray = new int[openingHoursJSON.length()][2];
            for (int k = 0; k < openingHoursJSON.length(); k++) {
                JSONArray openingHoursDayJSON = openingHoursJSON.getJSONArray(k);

                openingHoursArray[k][0] = Integer.parseInt(openingHoursDayJSON.getString(0));
                openingHoursArray[k][1] = Integer.parseInt(openingHoursDayJSON.getString(1));
            }
            */

            JSONArray priceRangeJSON = companyJSON.getJSONArray("priceRange");
            JSONArray tagsJSON = companyJSON.getJSONArray("tags");
            String[] priceRangeArray = new String[priceRangeJSON.length()];
            for (int k = 0; k < priceRangeJSON.length(); k++) {
                priceRangeArray[k] = priceRangeJSON.getString(k);
            }

            String[] tagsArray = new String[tagsJSON.length()];
            for (int k = 0; k < tagsJSON.length(); k++) {
                tagsArray[k] = tagsJSON.getString(k);
            }
            company = new CompanyBuilder()
                    // location
                    .images(imagesArray)
                    .city(companyJSON.getString("city"))
                    .country(companyJSON.getString("country"))
                    .categories(companyJSON.getString("categories"))
                    .openingHours(companyJSON.getString("openingHours"))
                    .priceRange(priceRangeArray)
                    .id(companyJSON.getString("_id"))
                    .name(companyJSON.getString("name"))
                    .email(companyJSON.getString("email"))
                    .password(companyJSON.getString("password"))
                    .description(companyJSON.getString("description"))
                    .address(companyJSON.getString("address"))
                    .postalCode(companyJSON.getString("postalCode"))
                    .tags(tagsArray)
                    .buildCompany();

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

    public CompanyBuilder categories (String categories)
    {
        this.categories = categories;
        return this;
    }

    public CompanyBuilder openingHours (String openingHours)
    {
        this.openingHours = openingHours;
        return this;
    }

    public CompanyBuilder priceRange (String[] priceRange)
    {
        this.priceRange = priceRange;
        return this;
    }

    public CompanyBuilder tags (String[] tags){
        this.tags = tags;
        return this;
    }
}
