package com.Appthuchi.Appqlthuchi.ui.khoanthu.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.DAO.LoaiThuDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.adapter.LoaiThuAdapter;
import com.Appthuchi.Appqlthuchi.moder.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiThuConFragment extends Fragment {

    private EditText edtMa;
    private EditText edtTen;
    private Button btnAdd,btnHuy;
    private LoaiThuDAO loaiThuDAO;
    private ListView lvLoaiThu;
    private LoaiThuAdapter loaiThuAdapter;
    private List<LoaiThu> loaiThuList;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_loaithu,container,false);

       loaiThuDAO = new LoaiThuDAO(getActivity());
       lvLoaiThu =view.findViewById(R.id.lvLoaiThu);
       FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        //hiện thị data
        hienThi();
        return view;
    }

    public void showDialog(){
        final Dialog add_loaithu_layout = new Dialog(getContext());

        add_loaithu_layout.setContentView(R.layout.new_item_loaithu);
        add_loaithu_layout.setTitle("Loại Thu");
        btnAdd = add_loaithu_layout.findViewById(R.id.btnAdd);
        btnHuy = add_loaithu_layout.findViewById(R.id.btnHuy);
        edtTen = add_loaithu_layout.findViewById(R.id.edtName);

        //Thêm data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoaiThu loaiThu = new LoaiThu();

                loaiThu.setTenLoaiThu(edtTen.getText().toString().trim());

                long result = loaiThuDAO.insertLoaiThu(loaiThu);
                if(result>0){
                    Toast.makeText(getActivity(),"Chen thanh cong", Toast.LENGTH_LONG).show();
                    hienThi();
                    add_loaithu_layout.cancel();
                }else {
                    Toast.makeText(getActivity(),"Chen that bai", Toast.LENGTH_LONG).show();
                }


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_loaithu_layout.cancel();
            }
        });
        add_loaithu_layout.show();
    }
    public void hienThi(){
        loaiThuList = loaiThuDAO.getALLLoaiThu();
        loaiThuAdapter = new LoaiThuAdapter(getActivity(),loaiThuList);
        lvLoaiThu.setAdapter(loaiThuAdapter);
        loaiThuAdapter.notifyDataSetChanged();
    }
}
