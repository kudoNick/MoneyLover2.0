package com.Appthuchi.Appqlthuchi.ui.khoanchi.fragment;


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


import com.Appthuchi.Appqlthuchi.DAO.LoaichiDAO;
import com.Appthuchi.Appqlthuchi.R;

import com.Appthuchi.Appqlthuchi.adapter.LoaiChiAdapter;
import com.Appthuchi.Appqlthuchi.moder.LoaiChi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiChiConFragment extends Fragment {

    private ListView lvLoaiChi;
    private LoaichiDAO loaichiDAO;
    private LoaiChiAdapter loaiChiAdapter;
    private List<LoaiChi> loaiChiList;
    private EditText edtMaLoaiChi,edtTenLoaiChi;
    private Button btnAdd,btnHuy;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.loanthifragmet,container,false);

        loaichiDAO = new LoaichiDAO(getActivity());
        lvLoaiChi = view.findViewById(R.id.lvLoaiChi);
        hienThi();

        //Nút thêm
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showDialog();
            }
        });
        return view;
    }

    public void hienThi(){
        loaiChiList = loaichiDAO.getALLLoaiChi();
        loaiChiAdapter = new LoaiChiAdapter(getActivity(),loaiChiList);
        lvLoaiChi.setAdapter(loaiChiAdapter);
        loaiChiAdapter.notifyDataSetChanged();
    }

    public void showDialog(){
        final Dialog add_LoaiChi_layout = new Dialog(getContext());
        add_LoaiChi_layout.setTitle("Thêm Loại Chi");
        add_LoaiChi_layout.setContentView(R.layout.new_item_loaichi);

        btnAdd = add_LoaiChi_layout.findViewById(R.id.btnAdd);
        btnHuy = add_LoaiChi_layout.findViewById(R.id.btnHuy);

        edtTenLoaiChi = add_LoaiChi_layout.findViewById(R.id.edtTenLoaiChi);

        //Thêm data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoaiChi loaiChi= new LoaiChi();

                loaiChi.setTenLoaiChi(edtTenLoaiChi.getText().toString().trim());

                boolean result = loaichiDAO.insertLoaiChi(loaiChi);
                if(result){
                    Toast.makeText(getActivity(),"Thêm Thành công", Toast.LENGTH_LONG).show();
                    hienThi();
                    add_LoaiChi_layout.cancel();
                }else {
                    Toast.makeText(getActivity(),"không được trùng mã", Toast.LENGTH_LONG).show();
                }


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_LoaiChi_layout.cancel();
            }
        });
        add_LoaiChi_layout.show();

}
}

