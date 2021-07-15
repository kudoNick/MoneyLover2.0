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

import com.Appthuchi.Appqlthuchi.DAO.LoaichiDAO;
import com.Appthuchi.Appqlthuchi.R;
import com.Appthuchi.Appqlthuchi.moder.LoaiChi;

import java.util.List;

public class LoaiChiAdapter extends BaseAdapter {

    private Context context;
    private List<LoaiChi> loaiChiList;
    private LoaichiDAO loaichiDAO;

    public LoaiChiAdapter(Context context, List<LoaiChi> loaiChiList) {
        this.context = context;
        this.loaiChiList = loaiChiList;
    }

    @Override
    public int getCount() {
        return loaiChiList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiChiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.loaichi,parent,false);

        //gán data

        TextView tvTen = view.findViewById(R.id.tvTen);

        final LoaiChi loaiChi = (LoaiChi) getItem(position);


        tvTen.setText(loaiChi.getTenLoaiChi());

        //Tham chiếu
        LinearLayout linearLayout = view.findViewById(R.id.lineLoaiChi);
        ImageView imageView = view.findViewById(R.id.delete);

        //Xóa
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaichiDAO = new LoaichiDAO(context);
                        LoaiChi loaiChi1= loaiChiList.get(position);

                        loaichiDAO.delLoaiChi(loaiChi1);
                        loaiChiList.remove(loaiChi1);
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
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog add_edit_layout = new Dialog(context);
                add_edit_layout.setCancelable(false);
                add_edit_layout.setTitle("Sửa Loại Chi");
                add_edit_layout.setContentView(R.layout.edit_item_chi);

                //Tham Chiếu
                Button btnHuy = add_edit_layout.findViewById(R.id.btnHuy);
                Button btnAdd = add_edit_layout.findViewById(R.id.btnAdd);

                final EditText edtTen = add_edit_layout.findViewById(R.id.edtTenLoaiChi);

                edtTen.setText(loaiChi.getTenLoaiChi());


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaichiDAO = new LoaichiDAO(context);
                        LoaiChi loaiChi1 = (LoaiChi) getItem(position);
                        if (edtTen.getText().toString().trim().length()==0){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            loaiChi1.setTenLoaiChi(edtTen.getText().toString().trim());

                            loaichiDAO.update(loaiChi1);
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
