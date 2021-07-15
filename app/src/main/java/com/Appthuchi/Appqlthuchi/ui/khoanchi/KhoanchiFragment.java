package com.Appthuchi.Appqlthuchi.ui.khoanchi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.ui.khoanchi.fragment.KhoanChiConFragment;
import com.Appthuchi.Appqlthuchi.ui.khoanchi.fragment.LoaiChiConFragment;
import com.Appthuchi.Appqlthuchi.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class KhoanchiFragment extends Fragment {

    private KhoanchiViewModel galleryViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(KhoanchiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        viewPager = root.findViewById(R.id.viewPager);
        tabLayout = root.findViewById(R.id.tabLayout);

        adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.AddFragment(new KhoanChiConFragment(),"Khoản Chi");
        adapter.AddFragment(new LoaiChiConFragment(),"Loại Chi");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

}