package com.group3.valapas.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reservation implements Comparable<Reservation>
{
    private String id;
    private String customerId;
    private String companyId;
    private String date;
    private String offeringId;
    private int quantity;
    private String companyName;
    private String offeringName;
    private String offeringDescription;
    private int price;
    private String customerName;

    public String getCompanyName() {
        return companyName;
    }

    public String getOfferingname() {
        return offeringName;
    }

    public String getOfferingDescription() {
        return offeringDescription;
    }

    public int getPrice() {
        return price;
    }


    public Reservation(String id, String customerId, String companyId, String date, String offeringId, int quantity, String companyName, String offeringName, String offeringDescription, int price, String customerName)
    {
        this.id = id;
        this.customerId = customerId;
        this.companyId = companyId;
        this.date = date;
        this.offeringId = offeringId;
        this.quantity = quantity;
        this.companyName = companyName;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
        this.price = price;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
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


    @Override
    public int compareTo(Reservation o){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(this.date).compareTo(sdf.parse(o.date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return "Reservation: Company: " + companyName + ". Offering name: " + offeringName + ". Offering description: " + offeringDescription + ". Price: " + price;
    }
}
