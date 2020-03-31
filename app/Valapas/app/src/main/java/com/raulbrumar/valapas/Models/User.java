package com.raulbrumar.valapas.Models;

import java.util.HashMap;

// User model
public class User
{
    private String id;
    private String firstName;
    private String lastName;
    private Boolean isAdult;
    private String email;
    private String password;
    private String city;

    private String bearerToken;

    public User(String id, String firstName, String lastName, Boolean isAdult, String email, String password, String city)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdult = isAdult;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public void setBearerToken(String token) { this.bearerToken = token; }

    public String getBearerToken() { return bearerToken; }

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

    public String getId() { return id; }

    @Override
    public String toString()
    {
        return "FirstName: " + firstName + ", LastName " + lastName
                + ", Email: " + email + ", Password: " + password
                + ", isAdult: " + isAdult + ", City: " + city;
    }

}
