package com.raulbrumar.valapas.ApiHandler;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.raulbrumar.valapas.Models.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Handles every api route
public class ApiHandler
{
    public static final String apiUrl = "http://ec2-54-93-188-113.eu-central-1.compute.amazonaws.com:3000";

    // Endpoints

    // Users
    public static void registerUser(Context context, User user)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/users/register";

        // Making the JSON
        JSONObject js = new JSONObject();
        try {

            js.put("firstName", user.getFirstName());
            js.put("lastName", user.getLastName());
            //js.put("isAdult", user.getAdult());
            js.put("email", user.getEmail());
            js.put("password", user.getPassword());

        }catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("AAA", js.toString());

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, js,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        error.printStackTrace();
                        Log.d("AAA", "Error: " + error
                                + "\nStatus Code " + error.networkResponse.statusCode
                                + "\nCause " + error.getCause()
                                + "\nmessage" + error.getMessage());

                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requestQueue.add(postRequest);
    }

    public static void loginUser()
    {

    }


    public static void editUser()
    {

    }

    public static void deleteUser()
    {

    }

    public static void getAllUsers()
    {

    }
}
