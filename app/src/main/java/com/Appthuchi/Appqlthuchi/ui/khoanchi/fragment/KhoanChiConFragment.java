package com.Appthuchi.Appqlthuchi.ui.khoanchi.fragment;


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

import com.Appthuchi.Appqlthuchi.DAO.KhoanChiDAO;
import com.Appthuchi.Appqlthuchi.DAO.LoaichiDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.adapter.KhoanChiAdapter;
import com.Appthuchi.Appqlthuchi.moder.KhoanChi;
import com.Appthuchi.Appqlthuchi.moder.LoaiChi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhoanChiConFragment extends Fragment {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private EditText editMa,edtSoTien,edtMoTa;
    private TextView tvNgayChi;
    private Button btnChonNgay,btnAdd,btnHuy;
    private Spinner spinLoaiChi;

    private List<LoaiChi> loaiChiList;
    private LoaichiDAO loaichiDAO;
    private KhoanChiDAO khoanChiDAO;
    private List<KhoanChi> khoanChiList;
    private KhoanChiAdapter khoanChiAdapter;
    private ListView lvKhoanChi;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khoanchifragmet,container,false);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        loaichiDAO = new LoaichiDAO(getActivity());
        khoanChiDAO = new KhoanChiDAO(getActivity());
        lvKhoanChi = view.findViewById(R.id.lvKhoanChi);

        try {
            hienThi();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    public void showDialog(){
        final Dialog add_khoanchi_layout = new Dialog(getContext());
        add_khoanchi_layout.setTitle("Thêm khoản chi");

        add_khoanchi_layout.setContentView(R.layout.new_item_khoanchi);

        spinLoaiChi = add_khoanchi_layout.findViewById(R.id.spinLoaiChi);
        edtSoTien = add_khoanchi_layout.findViewById(R.id.edtSoTien);
        tvNgayChi = add_khoanchi_layout.findViewById(R.id.tvNgayChi);
        edtMoTa = add_khoanchi_layout.findViewById(R.id.edtMoTa);
        btnChonNgay = add_khoanchi_layout.findViewById(R.id.btnChonNgay);

        //Spiner

        loaiChiList = new ArrayList<>();

        loaiChiList = loaichiDAO.getALLLoaiChi();
        ArrayAdapter<LoaiChi> arrayAdapter= new ArrayAdapter<LoaiChi>(getActivity(),android.R.layout.simple_spinner_item,loaiChiList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinLoaiChi.setAdapter(arrayAdapter);

        spinLoaiChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiChiList.get(spinLoaiChi.getSelectedItemPosition()).getTenLoaiChi();
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
                        tvNgayChi.setText(dayOfMonth + "-" +(month+1) + "-" + year);
                    }
                },lich.get(Calendar.YEAR)
                ,lich.get(Calendar.MONTH)
                ,lich.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
            }
        });

        btnAdd = add_khoanchi_layout.findViewById(R.id.btnAdd);
        btnHuy = add_khoanchi_layout.findViewById(R.id.btnHuy);
        //Thêm data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    add();
                    add_khoanchi_layout.cancel();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_khoanchi_layout.cancel();
            }
        });
        add_khoanchi_layout.show();
    }

    public void add() throws ParseException {
        KhoanChi khoanChi = new KhoanChi();

        khoanChi.setLoaiChi(spinLoaiChi.getSelectedItem().toString().trim());
        khoanChi.setSoTien(edtSoTien.getText().toString().trim());
        khoanChi.setNgayChi(tvNgayChi.getText().toString());
        khoanChi.setMoTa(edtMoTa.getText().toString().trim());


        long result = khoanChiDAO.insertKhoanChi(khoanChi);

        if (result>0){
            Toast.makeText(getActivity(),"Thành Công",Toast.LENGTH_SHORT).show();
            hienThi();
        }else {
            Toast.makeText(getActivity(),"thêm thất bại",Toast.LENGTH_SHORT).show();
        }
    }
    public void hienThi() throws ParseException {
        khoanChiList = khoanChiDAO.getAllKhoanChi();
        khoanChiAdapter = new KhoanChiAdapter(getActivity(),khoanChiList);
        lvKhoanChi.setAdapter(khoanChiAdapter);
    }
}
