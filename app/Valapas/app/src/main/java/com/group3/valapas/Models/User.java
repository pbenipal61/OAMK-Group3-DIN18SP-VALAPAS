package com.group3.valapas.Models;

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
