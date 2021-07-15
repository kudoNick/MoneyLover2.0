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

import com.Appthuchi.Appqlthuchi.DAO.KhoanChiDAO;
import com.Appthuchi.Appqlthuchi.DAO.LoaichiDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.moder.KhoanChi;
import com.Appthuchi.Appqlthuchi.moder.LoaiChi;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KhoanChiAdapter extends BaseAdapter {
    private Context context;
    private List<KhoanChi> khoanChiList;
    private List<LoaiChi> loaiChiList;
    private KhoanChiDAO khoanChiDAO;
    private LoaichiDAO loaichiDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public KhoanChiAdapter(Context context, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.khoanChiList = khoanChiList;
    }


    @Override
    public int getCount() {
        return khoanChiList.size();
    }

    @Override
    public Object getItem(int position) {
        return khoanChiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.khoanchi,parent,false);


        final TextView tvloaiChi = view.findViewById(R.id.tvloaiChi);
        TextView tvSoTien = view.findViewById(R.id.tvSoTien);
        TextView tvNgayChi = view.findViewById(R.id.tvNgayChi);


        final KhoanChi khoanChi = (KhoanChi) getItem(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        Double a = Double.valueOf(khoanChi.getSoTien());
        String formattedNumber1 = formatter.format(a);
        tvSoTien.setText(formattedNumber1 +" VNĐ");

        tvloaiChi.setText(khoanChi.getLoaiChi());


        tvNgayChi.setText(khoanChi.getNgayChi());

        //xóa
        ImageView delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanChiDAO = new KhoanChiDAO(context);

                        KhoanChi khoanChi1= khoanChiList.get(position);

                        khoanChiDAO.delLoaiChi(khoanChi1);
                        khoanChiList.remove(khoanChi1);
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
        //update
        LinearLayout linearLayout = view.findViewById(R.id.lineKhoanChi);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog add_edit_layout = new Dialog(context);
                add_edit_layout.setCancelable(false);
                add_edit_layout.setTitle("Sửa Khoản Chi");
                add_edit_layout.setContentView(R.layout.edit_khoan_chi);


                //Tham Chiếu
                final Spinner spinner = add_edit_layout.findViewById(R.id.spinKhoanChi);
                final EditText edtSoTien = add_edit_layout.findViewById(R.id.edtSoTien);
                final TextView tvNgayChi = add_edit_layout.findViewById(R.id.tvNgayChi);
                Button btnChonNgay = add_edit_layout.findViewById(R.id.btnChonNgay);
                final EditText edtMoTa = add_edit_layout.findViewById(R.id.edtMoTa);
                //hiện thị data cũ

                edtSoTien.setText(khoanChi.soTien);
                tvNgayChi.setText(khoanChi.ngayChi);
                edtMoTa.setText(khoanChi.getMoTa());



                //Hiện Thị Cho Spiner
                loaiChiList = new ArrayList<>();
                loaichiDAO = new LoaichiDAO(context);
                loaiChiList = loaichiDAO.getALLLoaiChi();
                ArrayAdapter<LoaiChi> arrayAdapter = new ArrayAdapter<LoaiChi>(context,android.R.layout.simple_spinner_item,loaiChiList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       loaiChiList.get(spinner.getSelectedItemPosition()).getTenLoaiChi();
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
                        KhoanChiDAO khoanChiDAO = new KhoanChiDAO(context);
                        if (edtSoTien.getText().toString().trim().length()==0 || edtMoTa.getText().toString().trim().length()==0){
                            Toast.makeText(context, "Khôn được để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            KhoanChi khoanChi1 = (KhoanChi) getItem(position);
                            khoanChi1.setLoaiChi(spinner.getSelectedItem().toString());
                            khoanChi1.setSoTien(edtSoTien.getText().toString().trim());
                            String ngay = tvNgayChi.getText().toString();
                            khoanChi1.setNgayChi(ngay);
                            khoanChi1.setMoTa(edtMoTa.getText().toString().trim());

                            add_edit_layout.cancel();
                            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                            khoanChiDAO.update(khoanChi1);
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

        return view ;
    }
}
