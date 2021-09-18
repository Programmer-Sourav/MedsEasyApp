package com.portfolio.apps.pharmeasymeds.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.portfolio.apps.pharmeasymeds.Pojo.AllProducts;
import com.portfolio.apps.pharmeasymeds.repository.AllProductsRepository;
import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepository;
import com.portfolio.apps.pharmeasymeds.response.AllProductsResponse;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;

public class AllProductsViewModel extends ViewModel {
    private AllProductsRepository allProductsRepository;
    private LiveData<AllProductsResponse> allProductsResponseLiveData;


    public AllProductsViewModel(Application application){
        allProductsRepository = new AllProductsRepository();
        this.allProductsResponseLiveData = allProductsRepository.getAllProductsList();
    }

    public LiveData<AllProductsResponse> getAllProductsResponseLiveData(){
        return allProductsResponseLiveData;
    }
}
