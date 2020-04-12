package com.raulbrumar.valapas.ApiHandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedUser;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.raulbrumar.valapas.Models.Company;
import com.raulbrumar.valapas.Models.CompanyBuilder;
import com.raulbrumar.valapas.Models.User;
import com.raulbrumar.valapas.Models.UserBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Handles every api route
public class ApiHandler
{
    public static final String apiUrl = "http://ec2-54-93-188-113.eu-central-1.compute.amazonaws.com:3000";

    private static String bearerToken;


    public static void readBearerToken()
    {
        // reading jwt token from shared prefs
    }

    // requests for users
    public static void registerUser(final Context context, User user, final IReturnUserCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/users/register";

        // Making the JSON
        JSONObject js = new JSONObject();
        try {

            js.put("firstName", user.getFirstName());
            js.put("lastName", user.getLastName());
            js.put("isAdult", user.getAdult());
            js.put("email", user.getEmail());
            js.put("password", user.getPassword());
            js.put("city", user.getCity());

        }catch (Exception e) {
            e.printStackTrace();
        }
        // Log.d("AAA", js.toString());

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, js,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response reached: " + response.toString());

                        User newUser;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject userJSON = dataJSON.getJSONObject("user");

                            newUser = new UserBuilder().firstName(userJSON.getString("firstName"))
                                    .id(userJSON.getString("_id"))
                                    .lastName(userJSON.getString("lastName"))
                                    .isAdult(userJSON.getBoolean("isAdult"))
                                    .email(userJSON.getString("email"))
                                    .password(userJSON.getString("password"))
                                    .city(userJSON.getString("city"))
                                    .buildUser();

                            callback.returnUser(newUser);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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

    public static void loginUser(final Context context, User user,final IReturnUserCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/users/login";

        // Making the JSON
        JSONObject js = new JSONObject();
        try {

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

                        // Creating the user
                        User newUser;
                        try
                        {
                            String token = response.getString("token");

                            newUser = new UserBuilder().buildUser(token);

                            bearerToken = token;

                            callback.returnUser(newUser);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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

    public static void editUser(final Context context, User user, final IReturnUserCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/users/" + user.getId();

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("firstName", user.getFirstName());
            js.put("lastName", user.getLastName());
            js.put("isAdult", user.getAdult());
            js.put("email", user.getEmail());
            js.put("password", user.getPassword());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Log.d("AAA", js.toString());

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, js,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        User newUser;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject userJSON = dataJSON.getJSONObject("user");

                            newUser = new UserBuilder().firstName(userJSON.getString("firstName"))
                                    .id(userJSON.getString("_id"))
                                    .lastName(userJSON.getString("lastName"))
                                    .isAdult(userJSON.getBoolean("isAdult"))
                                    .email(userJSON.getString("email"))
                                    .password(userJSON.getString("password"))
                                    .city(userJSON.getString("city"))
                                    .buildUser();

                            callback.returnUser(newUser);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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
                headers.put("Authorization", "Bearer " + bearerToken);
                return headers;
            }
        };

        requestQueue.add(putRequest);
    }

    public static void deleteUser(final Context context, User user, final IDeletedUser callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/users/" + user.getId();

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("firstName", user.getFirstName());
            js.put("lastName", user.getLastName());
            js.put("isAdult", user.getAdult());
            js.put("email", user.getEmail());
            js.put("password", user.getPassword());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Log.d("AAA", js.toString());

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, js,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        User newUser;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject userJSON = dataJSON.getJSONObject("user");

                            newUser = new UserBuilder().firstName(userJSON.getString("firstName"))
                                    .id(userJSON.getString("_id"))
                                    .lastName(userJSON.getString("lastName"))
                                    .isAdult(userJSON.getBoolean("isAdult"))
                                    .email(userJSON.getString("email"))
                                    .password(userJSON.getString("password"))
                                    .city(userJSON.getString("city"))
                                    .buildUser();

                            callback.deletedDone();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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
                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlzQWR1bHQiOnRydWUsImNpdHkiOiJOZXcgWW9yayIsIl9pZCI6IjVlOGM3NDc2MzZhNTkxNzUyZjI3MjVmNCIsImZpcnN0TmFtZSI6IkpvaG5ueSIsImxhc3ROYW1lIjoiR2F0IiwiZW1haWwiOiJqb2hubnl5eXl5LmdhdEBtYWlsLmNvbSIsInBhc3N3b3JkIjoiJDJiJDEwJDVZY2dWbndralZCa1R6VXY3cWp3RE9wWXk4TldYNUJUeHhHWHJzckpFMjB4Nkt0dFFWWGVtIiwiX192IjowfSwidmFsaWRpdHkiOiIzMGQiLCJ0aW1lc3RhbXAiOjE1ODYyNjUwODAwMDYsImlhdCI6MTU4NjI2NTA4MCwiZXhwIjoxNTg4ODU3MDgwfQ.cUfdGbo9l3lr1eNrylXOcJVLV40vqavcuXIHp8JBtTc");
                return headers;
            }
        };
        requestQueue.add(putRequest);
    }

    // requests for companies

    public static void registerCompany(final Context context, Company company, final IReturnCompanyCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/companies";

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("email", company.getEmail());
            js.put("password", company.getPassword());
            js.put("companyName", company.getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Log.d("AAA", js.toString());

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST, url, js,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        Company newCompany;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject companyJSON = dataJSON.getJSONObject("company");

                            newCompany = new CompanyBuilder().id(companyJSON.getString("_id"))
                                    .email(companyJSON.getString("email"))
                                    .name(companyJSON.getString("name"))
                                    .password(companyJSON.getString("password"))
                                    .buildCompany();

                            callback.returnCompany(newCompany);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
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
                headers.put("Authorization", "Bearer " + bearerToken);
                return headers;
            }
        };

        requestQueue.add(putRequest);
    }
}
