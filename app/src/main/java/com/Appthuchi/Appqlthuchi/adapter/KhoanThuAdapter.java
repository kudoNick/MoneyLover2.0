package com.Appthuchi.Appqlthuchi.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.DAO.KhoanThuDAO;
import com.Appthuchi.Appqlthuchi.DAO.LoaiThuDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.moder.KhoanThu;
import com.Appthuchi.Appqlthuchi.moder.LoaiThu;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KhoanThuAdapter extends BaseAdapter {

    private Context context;
    private List<KhoanThu> khoanThuList;
    private List<LoaiThu> loaiThuList;
    private KhoanThuDAO khoanThuDAO;
    private LoaiThuDAO loaiThuDAO;

    public KhoanThuAdapter(Context context, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return khoanThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.khoanthu,parent,false);

        final TextView tvLoaiThu = view.findViewById(R.id.tvloaiThu);

        TextView tvSoTien = view.findViewById(R.id.tvSoTien);
        TextView tvNgayThu = view.findViewById(R.id.tvNgayThu);

        khoanThuDAO = new KhoanThuDAO(context);
        loaiThuDAO = new LoaiThuDAO(context);
        final KhoanThu khoanThu= (KhoanThu) getItem(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        Double a = Double.valueOf(khoanThu.getSoTien());
        String formattedNumber1 = formatter.format(a);
        tvSoTien.setText(formattedNumber1 +" VNĐ");
        tvLoaiThu.setText(khoanThu.getLoaiThu());
        tvNgayThu.setText(khoanThu.getNgayThu());

        //Xóa
        ImageView delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanThuDAO = new KhoanThuDAO(context);

                        KhoanThu khoanThu1= khoanThuList.get(position);

                        khoanThuDAO.delLoaiThu(khoanThu1);
                        khoanThuList.remove(khoanThu1);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        //Edit
        LinearLayout linearLayout  = view.findViewById(R.id.lineKhoanThu);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog add_edit_layout = new Dialog(context);
                add_edit_layout.setCancelable(false);
                add_edit_layout.setTitle("Sửa Khoản Thu");
                add_edit_layout.setContentView(R.layout.edit_khoan_thu);


                //Tham Chiếu
                final Spinner spinner = add_edit_layout.findViewById(R.id.spinKhoanThu);
                final EditText edtSoTien = add_edit_layout.findViewById(R.id.edtSoTien);
                final TextView tvNgayChi = add_edit_layout.findViewById(R.id.tvNgayThu);
                Button btnChonNgay = add_edit_layout.findViewById(R.id.btnChonNgay);
                final EditText edtMoTa = add_edit_layout.findViewById(R.id.edtMoTa);
                //hiện thị data cũ

                edtSoTien.setText(khoanThu.soTien);
                tvNgayChi.setText(khoanThu.ngayThu);
                edtMoTa.setText(khoanThu.getMoTa());



//                //Hiện Thị Cho Spiner
                loaiThuList = new ArrayList<>();
                loaiThuList = loaiThuDAO.getALLLoaiThu();
                ArrayAdapter<LoaiThu> arrayAdapter= new ArrayAdapter<LoaiThu>(context,android.R.layout.simple_spinner_item,loaiThuList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner.setAdapter(arrayAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        loaiThuList.get(spinner.getSelectedItemPosition()).getTenLoaiThu();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnChonNgay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar lich = Calendar.getInstance();

                        DatePickerDialog pickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                Button btnAdd = add_edit_layout.findViewById(R.id.btnAdd);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        KhoanThuDAO khoanThuDAO= new KhoanThuDAO(context);
                        if (edtSoTien.getText().toString().trim().length()==0 || edtMoTa.getText().toString().trim().length()==0){
                            Toast.makeText(context, "Khôn được để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            KhoanThu khoanThu1= (KhoanThu) getItem(position);
                            khoanThu1.setLoaiThu(spinner.getSelectedItem().toString());
                            khoanThu1.setSoTien(edtSoTien.getText().toString().trim());
                            String ngay = tvNgayChi.getText().toString();
                            khoanThu1.setNgayThu(ngay);
                            khoanThu1.setMoTa(edtMoTa.getText().toString().trim());

                            add_edit_layout.cancel();
                            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                            khoanThuDAO.update(khoanThu1);
                            notifyDataSetChanged();
                        }
                    }
                });

                Button btnHuy = add_edit_layout.findViewById(R.id.btnHuy);
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_edit_layout.cancel();
                    }
                });
                add_edit_layout.show();
            }
        });
        return view;
    }
}
