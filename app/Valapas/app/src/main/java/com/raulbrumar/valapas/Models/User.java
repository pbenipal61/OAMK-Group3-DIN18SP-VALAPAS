package com.raulbrumar.valapas.Models;

import java.util.HashMap;

// User model as the one used by the database
public class User
{
    private String firstName;
    private String lastName;
    private Boolean isAdult;
    private String email;
    private String password;
    private String city;

    public User(String firstName, String lastName, Boolean isAdult, String email, String password, String city)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdult = isAdult;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Boolean getAdult()
    {
        return isAdult;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getCity()
    {
        return city;
    }
}
