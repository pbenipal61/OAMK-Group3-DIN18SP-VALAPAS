package com.group3.valapas.ApiHandler.ApiCallbacks;

import com.group3.valapas.Models.Reservation;

import java.util.ArrayList;

public interface IReturnReservationsFromSearchCallback
{
    void returnReservations(ArrayList<Reservation> reservations);
}
