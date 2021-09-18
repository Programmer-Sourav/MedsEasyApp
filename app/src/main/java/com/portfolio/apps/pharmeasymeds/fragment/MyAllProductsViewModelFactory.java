package com.portfolio.apps.pharmeasymeds.fragment;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.portfolio.apps.pharmeasymeds.ViewModel.AllProductsViewModel;
import com.portfolio.apps.pharmeasymeds.ViewModel.DealsOfTheDayViewModel;
import com.portfolio.apps.pharmeasymeds.repository.AllProductsRepository;
import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepository;

public class MyAllProductsViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;
    AllProductsRepository allProductsRepository = AllProductsRepository.getInstance(mApplication);

    public MyAllProductsViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AllProductsViewModel(mApplication);
    }

}
