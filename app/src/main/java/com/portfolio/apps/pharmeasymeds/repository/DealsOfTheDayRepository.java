package com.portfolio.apps.pharmeasymeds.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portfolio.apps.pharmeasymeds.DealsOfTheDay;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;
import com.portfolio.apps.pharmeasymeds.retrofit.ApiRequest;
import com.portfolio.apps.pharmeasymeds.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealsOfTheDayRepository {
    private static final String TAG = DealsOfTheDayRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private static DealsOfTheDayRepository ourInstance;

    public static DealsOfTheDayRepository getInstance(Application application) {
        if(ourInstance == null){
            ourInstance = new DealsOfTheDayRepository();
        }
        return ourInstance;
    }

    public DealsOfTheDayRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<DealsOfTheDayResponse> getDealsOfTheDayList() {
        final MutableLiveData<DealsOfTheDayResponse> data = new MutableLiveData<>();

        apiRequest.getDealsOfTheDayList()
                .enqueue(new Callback<DealsOfTheDayResponse>() {
                    @Override
                    public void onResponse(Call<DealsOfTheDayResponse> call, Response<DealsOfTheDayResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            data.setValue(response.body().getResponse());

                            Log.i("Snath, ", "articles total result:: " + response.body().getTotalResults());

                        }
                    }

                    @Override
                    public void onFailure(Call<DealsOfTheDayResponse> call, Throwable t) {
                        data.setValue(null);
                        Log.i("Snath, OnError ","OnError "+t.getCause());
                    }
                });

        return data;
    }
}
