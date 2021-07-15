package com.Appthuchi.Appqlthuchi.ui.khoanthu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KhoanthuViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KhoanthuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }
    public LiveData<String> getText() {
        return mText;
    }
}