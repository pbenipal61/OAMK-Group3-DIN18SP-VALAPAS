package com.raulbrumar.valapas.Models;

public class OfferingBuilder
{

    private Company company = new Company();
    private String id = "";
    private String offeringType = "";
    private String description = "";
    private String[] images = new String[0];
    private int quantity = 0;
    private String tags = "";
    private String price = "0 EUR";
    private String deposit = "0 EUR";
    private String[] discounts = new String[0];

    public OfferingBuilder() {}

    public Offering buildOffering()
    {
        return new Offering(id, company, offeringType, description, images, quantity, tags, price, deposit, discounts);
    }


    public OfferingBuilder id (String id)
    {
        this.id = id;
        return this;
    }

    public OfferingBuilder company(Company company)
    {
        this.company = company;
        return this;
    }

    public OfferingBuilder offeringType(String offeringType)
    {
        this.offeringType = offeringType;
        return this;
    }

    public OfferingBuilder description(String description)
    {
        this.description = description;
        return this;
    }

    public OfferingBuilder images(String[] images)
    {
        this.images = images;
        return this;
    }

    public OfferingBuilder quantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OfferingBuilder tags(String tags)
    {
        this.tags = tags;
        return this;
    }

    public OfferingBuilder deposit(String deposit)
    {
        this.deposit = deposit;
        return this;
    }

    public OfferingBuilder discounts(String[] discounts)
    {
        this.discounts = discounts;
        return this;
    }
}
