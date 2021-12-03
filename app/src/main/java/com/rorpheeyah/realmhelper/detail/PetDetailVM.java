package com.rorpheeyah.realmhelper.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetDetailVM extends ViewModel {
    private MutableLiveData<String> action;

    public PetDetailVM(){
        action = new MutableLiveData<>();
    }

    public MutableLiveData<String> getAction() {
        return action;
    }

    public void goBack(){
        action.setValue("BACK");
    }

    public void favorite(){
        action.setValue("FAVORITE");
    }
}
