package com.group3.valapas.Models;

public class Reservation
{
    private String id;
    private String customerId;
    private String companyId;
    private String date;
    private String offeringId;
    private int quantity;

    public Reservation(String id, String customerId, String companyId, String date, String offeringId, int quantity)
    {
        this.id = id;
        this.customerId = customerId;
        this.companyId = companyId;
        this.date = date;
        this.offeringId = offeringId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getCustomer()
    {
        return customerId;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public String getDate()
    {
        return date;
    }

    public String getOffering()
    {
        return offeringId;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
