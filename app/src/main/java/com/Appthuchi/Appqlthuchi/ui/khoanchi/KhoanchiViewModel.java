package com.Appthuchi.Appqlthuchi.ui.khoanchi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KhoanchiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KhoanchiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}