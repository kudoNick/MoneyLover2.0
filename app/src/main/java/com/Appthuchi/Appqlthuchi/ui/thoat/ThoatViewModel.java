package com.Appthuchi.Appqlthuchi.ui.thoat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThoatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ThoatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}