package com.raulbrumar.valapas.Models;

public class Offering
{
    private String id;
    private Company company;
    private String offeringType;
    private String description;
    private String[] images;
    private int quantity;
    private String tags;
    private String price;
    private String deposit;
    private String[] discounts;

    public Offering(String id, Company company, String offeringType, String description, String[] images, int quantity, String tags, String price, String deposit, String[] discounts)
    {
        this.company = company;
        this.id = id;
        this.offeringType = offeringType;
        this.description = description;
        this.images = images;
        this.quantity = quantity;
        this.tags = tags;
        this.price = price;
        this.deposit = deposit;
        this.discounts = discounts;
    }

    public Company getCompany()
    {
        return company;
    }

    public String getId()
    {
        return id;
    }

    public String getOfferingType()
    {
        return offeringType;
    }

    public String getDescription()
    {
        return description;
    }

    public String[] getImages()
    {
        return images;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getTags()
    {
        return tags;
    }

    public String getPrice()
    {
        return price;
    }

    public String getDeposit()
    {
        return deposit;
    }

    public String[] getDiscounts()
    {
        return discounts;
    }

}
