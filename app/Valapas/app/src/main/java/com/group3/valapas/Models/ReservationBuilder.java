package com.group3.valapas.Models;

public class ReservationBuilder
{
    private String id = "";
    private String customerId = "";
    private String companyId = "";
    private String date = "";
    private String offeringId = "";
    private int quantity = 0;
    private String companyName = "";
    private String offeringName = "";
    private String offeringDescription = "";
    private int price = 0;
    private String customerName = "";

    public ReservationBuilder() {}

    public Reservation buildReservation()
    {
        return new Reservation(id, customerId, companyId, date, offeringId, quantity, companyName, offeringName, offeringDescription, price, customerName);
    }

    public ReservationBuilder companyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public ReservationBuilder customerName(String customerName)
    {
        this.customerName = customerName;
        return this;
    }

    public ReservationBuilder offeringName(String offeringName)
    {
        this.offeringName = offeringName;
        return this;
    }

    public ReservationBuilder offeringDescription(String offeringDescription)
    {
        this.offeringDescription = offeringDescription;
        return this;
    }

    public ReservationBuilder price(int price)
    {
        this.price = price;
        return this;
    }

    public ReservationBuilder date(String date)
    {
        this.date = date;
        return this;
    }

    public ReservationBuilder id(String id)
    {
        this.id = id;
        return this;
    }

    public ReservationBuilder company(String id)
    {
        this.companyId = id;
        return this;
    }

    public ReservationBuilder customer(String customerId)
    {
        this.customerId = customerId;
        return this;
    }

    public ReservationBuilder offering(String offeringId)
    {
        this.offeringId = offeringId;
        return this;
    }

    public ReservationBuilder quantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }
}
