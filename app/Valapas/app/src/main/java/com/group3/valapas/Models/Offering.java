package com.group3.valapas.Models;

public class Offering
{
    private String id;
    private String companyId;
    private String offeringType;
    private String description;
    private String[] images;
    private int quantity;
    private String tags;
    private int price;
    private int deposit;
    private String[] discounts;

    public Offering(String id, String company, String offeringType, String description, String[] images, int quantity, String tags, int price, int deposit, String[] discounts)
    {
        this.companyId = companyId;
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

    public String getCompany()
    {
        return companyId;
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

    public int getPrice()
    {
        return price;
    }

    public int getDeposit()
    {
        return deposit;
    }

    public String[] getDiscounts()
    {
        return discounts;
    }

}
