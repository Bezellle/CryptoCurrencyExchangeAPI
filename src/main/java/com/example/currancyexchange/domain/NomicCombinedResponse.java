package com.example.currancyexchange.domain;

import java.util.ArrayList;

public class NomicCombinedResponse {
    private final ArrayList<CurrancyRequest> combinedResponse = new ArrayList<CurrancyRequest>();


    public ArrayList<CurrancyRequest> getCombinedResponse() {
        return combinedResponse;
    }

    public void add(CurrancyRequest currancyRequest) {
        combinedResponse.add(currancyRequest);
    }

    public Integer length() {
        return combinedResponse.size();
    }

}
