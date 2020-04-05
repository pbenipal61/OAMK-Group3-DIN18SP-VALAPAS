package com.raulbrumar.valapas.Models;

import android.util.Base64;

import org.json.JSONObject;

public class UserBuilder
{
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private Boolean isAdult = true;
    private String email = "";
    private String password = "";
    private String city = "Oulu";

    public UserBuilder() {}

    public User buildUser()
    {
        return new User(id, firstName, lastName, isAdult, email, password, city);
    }

    public User buildUser(String token)
    {
        String decodedPayload;
        User user;
        try
        {
            String payload = token.split("\\.")[1];

            byte[] decodedBytes = Base64.decode(payload, Base64.URL_SAFE);
            decodedPayload = new String(decodedBytes, "UTF-8");
            JSONObject userJSON = new JSONObject(decodedPayload);

            user = firstName(userJSON.getString("firstName"))
                    .id(userJSON.getString("_id"))
                    .lastName(userJSON.getString("lastName"))
                    .isAdult(userJSON.getBoolean("isAdult"))
                    .email(userJSON.getString("email"))
                    .password(userJSON.getString("password"))
                    .city(userJSON.getString("city"))
                    .buildUser();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            user = buildUser();
        }

        return user;
    }

    public UserBuilder id (String id)
    {
        this.id = id;
        return this;
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
