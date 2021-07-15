package com.Appthuchi.Appqlthuchi.ui.khoanthu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.adapter.ViewPagerAdapter;
import com.Appthuchi.Appqlthuchi.ui.khoanchi.KhoanchiViewModel;
import com.Appthuchi.Appqlthuchi.ui.khoanthu.fragment.KhoanThuConFragment;
import com.Appthuchi.Appqlthuchi.ui.khoanthu.fragment.LoaiThuConFragment;
import com.google.android.material.tabs.TabLayout;

public class KhoanthuFragment extends Fragment {

    private KhoanchiViewModel galleryViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private KhoanthuViewModel homeViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(KhoanthuViewModel.class);

        View root = inflater.inflate(R.layout.fragment_khoanthu, container, false);

        viewPager = root.findViewById(R.id.viewPager);
        tabLayout = root.findViewById(R.id.tabLayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPagerAdapter.AddFragment(new KhoanThuConFragment(),"Khoản Thu");
        viewPagerAdapter.AddFragment(new LoaiThuConFragment(),"Loại Thu");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

}