package com.portfolio.apps.pharmeasymeds.retrofit;

import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("get_deals")
    Call<DealsOfTheDayResponse> getDealsOfTheDayList(

    );
    @GET("get_all_products")
    Call<AllProductsResponse> getAllProductsList();
}
