package com.portfolio.apps.pharmeasymeds.retrofit;

import com.portfolio.apps.pharmeasymeds.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

        private static Retrofit retrofit;


        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(Constants.DEALS_OF_THE_DAY_QUERY_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

}
