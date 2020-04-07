package com.raulbrumar.valapas.Models;

import java.text.SimpleDateFormat;

public class ReservationBuilder
{
    private String id = "";
    private User customer = null;
    private String date = "";
    private Offering offering = null;
    private int quantity = 0;

    public ReservationBuilder() {}

    public Reservation buildReservation()
    {
        return new Reservation(id, customer, date, offering, quantity);
    }

    public ReservationBuilder id(String id)
    {
        this.id = id;
        return this;
    }

    public ReservationBuilder customer(User customer)
    {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder offering(Offering offering)
    {
        this.offering = offering;
        return this;
    }

    public ReservationBuilder quantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }
}
