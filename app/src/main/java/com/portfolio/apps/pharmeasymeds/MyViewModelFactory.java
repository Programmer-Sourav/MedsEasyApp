package com.portfolio.apps.pharmeasymeds;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.portfolio.apps.pharmeasymeds.ViewModel.AllProductsViewModel;
import com.portfolio.apps.pharmeasymeds.ViewModel.DealsOfTheDayViewModel;
import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepository;
import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepositoryInputStream;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;
    DealsOfTheDayRepository dealsOfTheDayRepository = DealsOfTheDayRepository.getInstance(mApplication);

    public MyViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DealsOfTheDayViewModel(mApplication);
    }

}
