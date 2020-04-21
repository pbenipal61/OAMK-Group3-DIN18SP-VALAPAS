package com.group3.valapas.ApiHandler.ApiCallbacks;

import com.group3.valapas.Models.Offering;

import java.util.ArrayList;

public interface IReturnOfferingsFromSearchCallback
{
    public void returnOfferings(ArrayList<Offering> offerings);
}
