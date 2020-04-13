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
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedCompany;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedOffering;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedReservation;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IDeletedUser;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnCompanyCallback;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnOfferingCallback;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnReservationCallback;
import com.raulbrumar.valapas.ApiHandler.ApiCallbacks.IReturnUserCallback;
import com.raulbrumar.valapas.Models.Company;
import com.raulbrumar.valapas.Models.CompanyBuilder;
import com.raulbrumar.valapas.Models.Offering;
import com.raulbrumar.valapas.Models.OfferingBuilder;
import com.raulbrumar.valapas.Models.Reservation;
import com.raulbrumar.valapas.Models.ReservationBuilder;
import com.raulbrumar.valapas.Models.User;
import com.raulbrumar.valapas.Models.UserBuilder;

import org.json.JSONArray;
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

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        callback.deletedDone();
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

    // requests for companies

    public static void registerCompany(final Context context, Company company, final IReturnCompanyCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/companies";

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("name", company.getName());
            js.put("description", company.getDescription());
            js.put("address", company.getAddress());
            js.put("postalCode", company.getPostalCode());
            js.put("location", company.getLocation());
            js.put("city", company.getCity());
            js.put("country", company.getCountry());
            js.put("categories", company.getCategories());
            js.put("openingHours", company.getOpeningHours());
            js.put("priceRange", company.getPriceRange());
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

                            JSONArray categoriesArrayJSON = companyJSON.getJSONArray("categories");
                            String categoriesArray[] = new String[categoriesArrayJSON.length()];
                            for(int i = 0; i < categoriesArrayJSON.length(); i++)
                                categoriesArray[i] = categoriesArrayJSON.getString(i);

                            JSONArray openingHoursArrayJSON = companyJSON.getJSONArray("openingHours");
                            int openingHoursArray[][] = new int[openingHoursArrayJSON.length()][2];
                            for(int i = 0; i < openingHoursArrayJSON.length(); i++)
                            {
                                openingHoursArray[i][0] = Integer.parseInt(openingHoursArrayJSON.getJSONArray(i).getString(0));
                                openingHoursArray[i][1] = Integer.parseInt(openingHoursArrayJSON.getJSONArray(i).getString(1));
                            }

                            JSONArray priceRangeArrayJSON = companyJSON.getJSONArray("categories");
                            String priceRangeArray[] = new String[priceRangeArrayJSON.length()];
                            for(int i = 0; i < priceRangeArrayJSON.length(); i++)
                                priceRangeArray[i] = priceRangeArrayJSON.getString(i);

                            newCompany = new CompanyBuilder().id(companyJSON.getString("_id"))
                                    .email(companyJSON.getString("email"))
                                    .password(companyJSON.getString("password"))
                                    .name(companyJSON.getString("name"))
                                    .description(companyJSON.getString("description"))
                                    .address(companyJSON.getString("address"))
                                    .postalCode(companyJSON.getString("postalCode"))
                                    .location(companyJSON.getString("location"))
                                    .city(companyJSON.getString("city"))
                                    .country(companyJSON.getString("country"))
                                    .categories(categoriesArray)
                                    .openingHours(openingHoursArray)
                                    .priceRange(priceRangeArray)
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

    public static void loginCompany(final Context context, Company company, final IReturnCompanyCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/companies";

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("email", company.getEmail());
            js.put("password", company.getPassword());
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
                            String token = response.getString("token");

                            newCompany = new CompanyBuilder().buildCompany(token);

                            bearerToken = token;
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

    public static void editCompany(final Context context, Company company, final IReturnCompanyCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/companies/" + company.getId();

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("name", company.getName());
            js.put("description", company.getDescription());
            js.put("address", company.getAddress());
            js.put("postalCode", company.getPostalCode());
            js.put("location", company.getLocation());
            js.put("city", company.getCity());
            js.put("country", company.getCountry());
            js.put("categories", company.getCategories());
            js.put("openingHours", company.getOpeningHours());
            js.put("priceRange", company.getPriceRange());
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

                        Company newCompany;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject companyJSON = dataJSON.getJSONObject("company");

                            JSONArray categoriesArrayJSON = companyJSON.getJSONArray("categories");
                            String categoriesArray[] = new String[categoriesArrayJSON.length()];
                            for(int i = 0; i < categoriesArrayJSON.length(); i++)
                                categoriesArray[i] = categoriesArrayJSON.getString(i);

                            JSONArray openingHoursArrayJSON = companyJSON.getJSONArray("openingHours");
                            int openingHoursArray[][] = new int[openingHoursArrayJSON.length()][2];
                            for(int i = 0; i < openingHoursArrayJSON.length(); i++)
                            {
                                openingHoursArray[i][0] = Integer.parseInt(openingHoursArrayJSON.getJSONArray(i).getString(0));
                                openingHoursArray[i][1] = Integer.parseInt(openingHoursArrayJSON.getJSONArray(i).getString(1));
                            }

                            JSONArray priceRangeArrayJSON = companyJSON.getJSONArray("categories");
                            String priceRangeArray[] = new String[priceRangeArrayJSON.length()];
                            for(int i = 0; i < priceRangeArrayJSON.length(); i++)
                                priceRangeArray[i] = priceRangeArrayJSON.getString(i);

                            newCompany = new CompanyBuilder().id(companyJSON.getString("_id"))
                                    .email(companyJSON.getString("email"))
                                    .password(companyJSON.getString("password"))
                                    .name(companyJSON.getString("name"))
                                    .description(companyJSON.getString("description"))
                                    .address(companyJSON.getString("address"))
                                    .postalCode(companyJSON.getString("postalCode"))
                                    .location(companyJSON.getString("location"))
                                    .city(companyJSON.getString("city"))
                                    .country(companyJSON.getString("country"))
                                    .categories(categoriesArray)
                                    .openingHours(openingHoursArray)
                                    .priceRange(priceRangeArray)
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

    public static void deleteCompany(final Context context, Company company, final IDeletedCompany callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/companies/" + company.getId();

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        callback.deletedDone();
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

    //  requests for reservations
    public static void createReservation(final Context context, Reservation reservation, final IReturnReservationCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
            String url = apiUrl + "/reservations";

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("customer", reservation.getCustomer());
            js.put("date", reservation.getDate());
            js.put("offering", reservation.getOffering());
            js.put("quantity", reservation.getQuantity());
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

                        Reservation newReservation;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject reservationJSON = dataJSON.getJSONObject("reservation");

                            newReservation = new ReservationBuilder().id(reservationJSON.getString("_id"))
                                    .customer(reservationJSON.getString("customer"))
                                    .date(reservationJSON.getString("date"))
                                    .offering(reservationJSON.getString("offering"))
                                    .quantity(reservationJSON.getInt("quantity"))
                                    .buildReservation();

                            callback.returnReservation(newReservation);
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
    public static void editReservation(final Context context, Reservation reservation, final IReturnReservationCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/reservations/" + reservation.getId();

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("customer", reservation.getCustomer());
            js.put("date", reservation.getDate());
            js.put("offering", reservation.getOffering());
            js.put("quantity", reservation.getQuantity());
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

                        Reservation newReservation;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject reservationJSON = dataJSON.getJSONObject("reservation");

                            newReservation = new ReservationBuilder().id(reservationJSON.getString("_id"))
                                    .customer(reservationJSON.getString("customer"))
                                    .date(reservationJSON.getString("date"))
                                    .offering(reservationJSON.getString("offering"))
                                    .quantity(reservationJSON.getInt("quantity"))
                                    .buildReservation();

                            callback.returnReservation(newReservation);
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


    public static void deleteReservation(final Context context, Reservation reservation, final IDeletedReservation callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/reservations/" + reservation.getId();

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                        callback.deletedReservation();
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

    // requests for offerings

    public static void createOffering(final Context context, Offering offering, final IReturnOfferingCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/offerings";

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("company", offering.getCompany());
            js.put("offeringType", offering.getOfferingType());
            js.put("description", offering.getDescription());
            js.put("quantity", offering.getQuantity());
            js.put("tags", offering.getTags());
            js.put("price", offering.getPrice());
            js.put("deposit", offering.getDeposit());
            js.put("discounts", offering.getDiscounts());

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

                        Offering newOffering;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject offeringJSON = dataJSON.getJSONObject("offering");

                            /*JSONArray imagesArrayJSON = offeringJSON.getJSONArray("images");
                            String imagesArray[] = new String[imagesArrayJSON.length()];
                            for(int i = 0; i < imagesArrayJSON.length(); i++)
                                imagesArray[i] = imagesArrayJSON.getString(i);*/

                            JSONArray discountsArrayJSON = offeringJSON.getJSONArray("discounts");
                            String discountsArray[] = new String[discountsArrayJSON.length()];
                            for(int i = 0; i < discountsArrayJSON.length(); i++)
                                discountsArray[i] = discountsArrayJSON.getString(i);

                            newOffering = new OfferingBuilder().id(offeringJSON.getString("_id"))
                                    .company(offeringJSON.getString("company"))
                                    .offeringType(offeringJSON.getString("offeringType"))
                                    .description(offeringJSON.getString("description"))
                                    .quantity(offeringJSON.getInt("quantity"))
                                    .tags(offeringJSON.getString("tags"))
                                    .price(offeringJSON.getInt("price"))
                                    .deposit(offeringJSON.getInt("deposit"))
                                    .discounts(discountsArray)
                                    .buildOffering();

                            callback.returnOffering(newOffering);
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

    public static void editOffering(final Context context, Offering offering, final IReturnOfferingCallback callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/offerings" + offering.getId();

        // Making the JSON
        JSONObject js = new JSONObject();
        try
        {
            js.put("company", offering.getCompany());
            js.put("offeringType", offering.getOfferingType());
            js.put("description", offering.getDescription());
            js.put("quantity", offering.getQuantity());
            js.put("tags", offering.getTags());
            js.put("price", offering.getPrice());
            js.put("deposit", offering.getDeposit());
            js.put("discounts", offering.getDiscounts());

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

                        Offering newOffering;
                        try
                        {
                            JSONObject dataJSON = response.getJSONObject("data");
                            JSONObject offeringJSON = dataJSON.getJSONObject("offering");

                            /*JSONArray imagesArrayJSON = offeringJSON.getJSONArray("images");
                            String imagesArray[] = new String[imagesArrayJSON.length()];
                            for(int i = 0; i < imagesArrayJSON.length(); i++)
                                imagesArray[i] = imagesArrayJSON.getString(i);*/

                            JSONArray discountsArrayJSON = offeringJSON.getJSONArray("discounts");
                            String discountsArray[] = new String[discountsArrayJSON.length()];
                            for(int i = 0; i < discountsArrayJSON.length(); i++)
                                discountsArray[i] = discountsArrayJSON.getString(i);

                            newOffering = new OfferingBuilder().id(offeringJSON.getString("_id"))
                                    .company(offeringJSON.getString("company"))
                                    .offeringType(offeringJSON.getString("offeringType"))
                                    .description(offeringJSON.getString("description"))
                                    .quantity(offeringJSON.getInt("quantity"))
                                    .tags(offeringJSON.getString("tags"))
                                    .price(offeringJSON.getInt("price"))
                                    .deposit(offeringJSON.getInt("deposit"))
                                    .discounts(discountsArray)
                                    .buildOffering();

                            callback.returnOffering(newOffering);
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

    public static void deleteOffering(final Context context, Offering offering, final IDeletedOffering callback)
    {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = apiUrl + "/offerings" + offering.getId();


        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("AAA", "Response Reached");
                        Log.d("AAA", response.toString());

                            callback.deletedOffering();

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
