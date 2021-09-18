package com.portfolio.apps.pharmeasymeds.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.portfolio.apps.pharmeasymeds.Pojo.AllProducts;


import java.util.List;

public class AllProductsResponse {
    @SerializedName("response")
    @Expose
    private AllProductsResponse response;

    @SerializedName("data")
    @Expose
    private List<AllProducts> AllProductsList = null;

    public List<AllProducts> getAllProductsList() {
        return AllProductsList;
    }

    public void setAllProductsList(List<AllProducts> AllProductsList) {
        this.AllProductsList = AllProductsList;
    }

    public AllProductsResponse getResponse() {
        return response;
    }
    @SerializedName("status")
    @Expose
    private String status;

    public void setResponse(AllProductsResponse response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
