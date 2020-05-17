package com.example.commsci_android1.ui.generalinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeneralinfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GeneralinfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is general information fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}