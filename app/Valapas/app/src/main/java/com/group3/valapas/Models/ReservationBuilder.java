package com.group3.valapas.Models;

public class ReservationBuilder
{
    private String id = "";
    private String customerId = "";
    private String date = "";
    private String offeringId = "";
    private int quantity = 0;

    public ReservationBuilder() {}

    public Reservation buildReservation()
    {
        return new Reservation(id, customerId, date, offeringId, quantity);
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
