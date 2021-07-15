package com.Appthuchi.Appqlthuchi.ui.thongke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThongKeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ThongKeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}