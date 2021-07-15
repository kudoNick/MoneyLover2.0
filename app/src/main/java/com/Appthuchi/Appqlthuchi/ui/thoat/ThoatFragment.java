package com.Appthuchi.Appqlthuchi.ui.thoat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.Appthuchi.Appqlthuchi.R;

public class ThoatFragment extends Fragment {

    private ThoatViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(ThoatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        return root;
    }
}