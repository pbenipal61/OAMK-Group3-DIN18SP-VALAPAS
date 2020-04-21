package com.group3.valapas.ApiHandler.ApiCallbacks;

import com.group3.valapas.Models.Company;
import java.util.ArrayList;

public interface IReturnCompanySearchResultsCallback
{
    void returnSearchResults(ArrayList<Company> returnedCompanies);
}
