package com.Appthuchi.Appqlthuchi.ui.thongke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.Appthuchi.Appqlthuchi.DAO.KhoanChiDAO;
import com.Appthuchi.Appqlthuchi.DAO.KhoanThuDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.moder.KhoanChi;
import com.Appthuchi.Appqlthuchi.moder.KhoanThu;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongkeFragment extends Fragment {

    private ThongKeViewModel toolsViewModel;
    private int ab;
    private KhoanChiDAO khoanChiDAO;
    private List<KhoanChi> khoanChiList;
    private KhoanThuDAO khoanThuDAO;
    private List<KhoanThu> khoanThuList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ThongKeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        TextView tongChi = root.findViewById(R.id.tongchi);
        TextView tongThu= root.findViewById(R.id.tongthu);

        khoanChiDAO = new KhoanChiDAO(getActivity());
        khoanChiList = new ArrayList<>();
        khoanChiList = khoanChiDAO.getAllKhoanChi();

        khoanThuDAO = new KhoanThuDAO(getActivity());
        khoanThuList = new ArrayList<>();
        khoanThuList = khoanThuDAO.getAllKhoanThu();
        NumberFormat formatter = new DecimalFormat("#,###");

        Double tongKhoanChi = Double.valueOf(0);
        for (KhoanChi khoanChi: khoanChiList){
            tongKhoanChi += (Double.parseDouble(khoanChi.getSoTien()));
        }
       Double ab = tongKhoanChi;
        KhoanChi khoanChi = new KhoanChi();

        String formattedNumber1 = formatter.format(ab);

        khoanChi.setSoTien(String.valueOf(formattedNumber1));
        String chi = khoanChi.getSoTien();

        tongChi.setText(chi + " VNĐ");



        //////
        Double tongKhoanThu = Double.valueOf(0);


        for (KhoanThu khoanThu: khoanThuList){
            tongKhoanThu += (Double.parseDouble(khoanThu.getSoTien()));
        }
        Double ganTong = tongKhoanThu;
        String formattedNumber = formatter.format(ganTong);
        KhoanThu khoanThu = new KhoanThu();
        khoanThu.setSoTien(String.valueOf(formattedNumber));
        String  thu = khoanThu.getSoTien();

        tongThu.setText(thu + " VNĐ");
        return root;
    }
}