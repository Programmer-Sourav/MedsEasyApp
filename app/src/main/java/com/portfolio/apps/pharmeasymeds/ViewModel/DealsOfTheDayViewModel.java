package com.portfolio.apps.pharmeasymeds.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepository;
import com.portfolio.apps.pharmeasymeds.repository.DealsOfTheDayRepositoryInputStream;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;

import static com.portfolio.apps.pharmeasymeds.Constants.DEALS_OF_THE_DAY_QUERY_URL;

public class DealsOfTheDayViewModel extends ViewModel {
    private DealsOfTheDayRepository dealsOfTheDayRepository;
    private LiveData<DealsOfTheDayResponse> dealsOfTheDayResponseLiveData;


    public DealsOfTheDayViewModel(Application application){
        dealsOfTheDayRepository = new DealsOfTheDayRepository();
        this.dealsOfTheDayResponseLiveData = dealsOfTheDayRepository.getDealsOfTheDayList();
    }

    public LiveData<DealsOfTheDayResponse> getDealsOfTheDayResponseLiveData(){
        return dealsOfTheDayResponseLiveData;
    }
}
