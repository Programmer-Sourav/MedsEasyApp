package com.portfolio.apps.pharmeasymeds.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.portfolio.apps.pharmeasymeds.DealsOfTheDay;

import java.util.List;

public class DealsOfTheDayResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    public DealsOfTheDayResponse getResponse() {
        return response;
    }

    public void setResponse(DealsOfTheDayResponse response) {
        this.response = response;
    }

    @SerializedName("response")
    @Expose
    private DealsOfTheDayResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<DealsOfTheDay> getDealsOfTheDayList() {
        return dealsOfTheDayList;
    }

    public void setDealsOfTheDayList(List<DealsOfTheDay> dealsOfTheDayList) {
        this.dealsOfTheDayList = dealsOfTheDayList;
    }
    @SerializedName("data")
    @Expose
    private List<DealsOfTheDay> dealsOfTheDayList = null;


}
