package com.portfolio.apps.pharmeasymeds.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;
import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;
import com.portfolio.apps.pharmeasymeds.retrofit.ApiRequest;
import com.portfolio.apps.pharmeasymeds.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductsRepository {
    private static final String TAG = AllProductsRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private static AllProductsRepository ourInstance;

    public static AllProductsRepository getInstance(Application application) {
        if(ourInstance == null){
            ourInstance = new AllProductsRepository();
        }
        return ourInstance;
    }

    public AllProductsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<AllProductsResponse> getAllProductsList() {
        final MutableLiveData<AllProductsResponse> data = new MutableLiveData<>();

        apiRequest.getAllProductsList()
                .enqueue(new Callback<AllProductsResponse>() {
                    @Override
                    public void onResponse(Call<AllProductsResponse> call, Response<AllProductsResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            data.setValue(response.body().getResponse());
                            Log.i("Snath, ", "articles total result:: " + response.body().getStatus());

                        }
                    }

                    @Override
                    public void onFailure(Call<AllProductsResponse> call, Throwable t) {
                        data.setValue(null);
                        Log.i("Snath, OnError ","OnError "+t.getCause());
                    }
                });

        return data;
    }
}
