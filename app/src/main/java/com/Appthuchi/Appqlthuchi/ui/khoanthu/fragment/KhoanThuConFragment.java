package com.Appthuchi.Appqlthuchi.ui.khoanthu.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.DAO.KhoanThuDAO;
import com.Appthuchi.Appqlthuchi.DAO.LoaiThuDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.adapter.KhoanThuAdapter;
import com.Appthuchi.Appqlthuchi.moder.KhoanThu;
import com.Appthuchi.Appqlthuchi.moder.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class KhoanThuConFragment extends Fragment {

    private EditText edtSoTien,edtMoTa;
    private TextView tvNgayThu;
    private Button btnChonNgay,btnAdd,btnHuy;
    private Spinner spinLoaiThu;

    private List<LoaiThu> loaiThuList;
    private LoaiThuDAO loaiThuDAO;
    private List<KhoanThu> khoanThuList;
    private KhoanThuDAO khoanThuDAO;
    private KhoanThuAdapter khoanThuAdapter;
    private ListView lvKhoanThu;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthu1,container,false);

        lvKhoanThu = view.findViewById(R.id.lvKhoanThu);
        khoanThuDAO = new KhoanThuDAO(getActivity());
        loaiThuDAO = new LoaiThuDAO(getActivity());

        hienThi();
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    public void showDialog(){
        final Dialog add_khoanthu_layout = new Dialog(getContext());
        add_khoanthu_layout.setTitle("Thêm khoản thu");

        add_khoanthu_layout.setContentView(R.layout.new_item_khoanthu);

        spinLoaiThu = add_khoanthu_layout.findViewById(R.id.spinLoaithu);
        edtSoTien = add_khoanthu_layout.findViewById(R.id.edtSoTien);
        tvNgayThu = add_khoanthu_layout.findViewById(R.id.tvNgayThu);
        edtMoTa = add_khoanthu_layout.findViewById(R.id.edtMoTa);
        btnChonNgay = add_khoanthu_layout.findViewById(R.id.btnChonNgay);

       // Spiner

        loaiThuList = new ArrayList<>();
        loaiThuList = loaiThuDAO.getALLLoaiThu();
        ArrayAdapter<LoaiThu> arrayAdapter= new ArrayAdapter<LoaiThu>(getActivity(),android.R.layout.simple_spinner_item,loaiThuList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinLoaiThu.setAdapter(arrayAdapter);

        spinLoaiThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiThuList.get(spinLoaiThu.getSelectedItemPosition()).getTenLoaiThu();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Chọn Ngày
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar lich = Calendar.getInstance();

                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvNgayThu.setText(dayOfMonth + "-" +(month+1) + "-" + year);
                    }
                },lich.get(Calendar.YEAR)
                        ,lich.get(Calendar.MONTH)
                        ,lich.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
            }
        });

        btnAdd = add_khoanthu_layout.findViewById(R.id.btnAdd);
        btnHuy = add_khoanthu_layout.findViewById(R.id.btnHuy);
        //Thêm data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    add();
                add_khoanthu_layout.cancel();

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_khoanthu_layout.cancel();
            }
        });
        add_khoanthu_layout.show();
    }

    private void add() {
        KhoanThu khoanThu= new KhoanThu();

        khoanThu.setLoaiThu(spinLoaiThu.getSelectedItem().toString().trim());
        khoanThu.setSoTien(edtSoTien.getText().toString().trim());
        khoanThu.setNgayThu(tvNgayThu.getText().toString());
        khoanThu.setMoTa(edtMoTa.getText().toString().trim());


        long result = khoanThuDAO.insertKhoanThu(khoanThu);

        if (result>0){
            Toast.makeText(getActivity(),"Thành Công",Toast.LENGTH_SHORT).show();
             hienThi();
        }else {
            Toast.makeText(getActivity(),"thêm thất bại",Toast.LENGTH_SHORT).show();
        }
    }


    public void hienThi()  {

        khoanThuList = khoanThuDAO.getAllKhoanThu();
        khoanThuAdapter = new KhoanThuAdapter(getActivity(),khoanThuList);
        lvKhoanThu.setAdapter(khoanThuAdapter);
        khoanThuAdapter.notifyDataSetChanged();
    }
}
