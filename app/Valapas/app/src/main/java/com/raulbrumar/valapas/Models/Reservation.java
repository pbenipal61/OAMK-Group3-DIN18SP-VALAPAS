package com.raulbrumar.valapas.Models;

public class Reservation
{
    private String id;
    private User customer;
    private String date;
    private Offering offering;
    private int quantity;

    public Reservation(String id, User customer, String date, Offering offering, int quantity)
    {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.offering = offering;
        this.quantity = quantity;
    }

    public User getCustomer()
    {
        return customer;
    }

    public String getDate()
    {
        return date;
    }

    public Offering getOffering()
    {
        return offering;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
