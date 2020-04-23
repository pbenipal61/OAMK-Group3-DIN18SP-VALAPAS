package com.group3.valapas.Models;

public class Company
{
    private String id;
    private String email;
    private String password;
    private String name;
    private String description;
    private String[] images;
    private String address;
    private String postalCode;
    private String location;
    private String city;
    private String country;
    private String categories;
    private String openingHours;
    private String[] priceRange;
    private String[] tags;

    public Company(String id, String email, String password, String name, String description, String[] images, String address, String postalCode, String location, String city, String country, String categories, String openingHours, String[] priceRange, String[] tags)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.images = images;
        this.address = address;
        this.postalCode = postalCode;
        this.location = location;
        this.city = city;
        this.country = country;
        this.categories = categories;
        this.openingHours = openingHours;
        this.priceRange = priceRange;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImages() {
        return images;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCategories() {
        return categories;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String[] getPriceRange() {
        return priceRange;
    }

    public String[] getTags() {
        return this.tags;
}

    public String toString()
    {
        return "Name: " + name;
    }
}
