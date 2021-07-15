package com.Appthuchi.Appqlthuchi.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.DAO.LoaiThuDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.moder.LoaiThu;

import java.util.List;

public class LoaiThuAdapter extends BaseAdapter {

    private LinearLayout linearLayout;
    private Context context;
    private List<LoaiThu> loaiThuList;
    private LoaiThuDAO loaiThuDAO;
    private ImageView imageXoa;

    public LoaiThuAdapter(Context context, List<LoaiThu> loaiThuList) {
        this.context = context;
        this.loaiThuList = loaiThuList;
    }

    @Override
    public int getCount() {
        return loaiThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiThuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.loaithu,parent,false);

        TextView tvTen = view.findViewById(R.id.tvTen);

        final LoaiThu loaiThu = (LoaiThu) getItem(position);

        tvTen.setText(loaiThu.tenLoaiThu);

        //xoa
        imageXoa = view.findViewById(R.id.delete);
        imageXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiThuDAO = new LoaiThuDAO(context);
                        LoaiThu loaiThu= loaiThuList.get(position);

                        loaiThuDAO.delLoaiThu(loaiThu);
                        loaiThuList.remove(loaiThu);
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
    //edit
        linearLayout = view.findViewById(R.id.lineLoaiThu);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog add_edit_layout = new Dialog(context);
                add_edit_layout.setCancelable(false);
                add_edit_layout.setTitle("Sửa Loại Thu");
                add_edit_layout.setContentView(R.layout.edit_item_thu);

                //Tham Chiếu
                Button btnHuy = add_edit_layout.findViewById(R.id.btnHuy);
                Button btnAdd = add_edit_layout.findViewById(R.id.btnAdd);
                final EditText edtTen = add_edit_layout.findViewById(R.id.edtTenLoaiThu);

                edtTen.setText(loaiThu.getTenLoaiThu());


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiThuDAO = new LoaiThuDAO(context);
                        LoaiThu loaiThu1= (LoaiThu) getItem(position);
                        if (edtTen.getText().toString().trim().length()==0){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            loaiThu1.setTenLoaiThu(edtTen.getText().toString().trim());

                            loaiThuDAO.update(loaiThu1);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Thành cÔng", Toast.LENGTH_SHORT).show();
                            add_edit_layout.cancel();
                        }

                    }
                });
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
