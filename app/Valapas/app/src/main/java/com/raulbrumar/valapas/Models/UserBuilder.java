package com.raulbrumar.valapas.Models;

public class UserBuilder
{
    private String firstName;
    private String lastName;
    private Boolean isAdult = true;
    private String email;
    private String password = "";
    private String city = "Oulu";

    public UserBuilder() {}

    public User buildUser()
    {
        return new User(firstName, lastName, isAdult, email, password, city);
    }

    public UserBuilder firstName (String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName (String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder isAdult (boolean isAdult)
    {
        this.isAdult = isAdult;
        return this;
    }

    public UserBuilder email (String email)
    {
        this.email = email;
        return this;
    }

    public UserBuilder password (String password)
    {
        this.password = password;
        return this;
    }

    public UserBuilder city (String city)
    {
        this.city = city;
        return this;
    }


}
