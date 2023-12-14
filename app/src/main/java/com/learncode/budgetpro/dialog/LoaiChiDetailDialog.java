package com.learncode.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.learncode.budgetpro.R;
import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.ui.chi.LoaiChiFragment;
import com.learncode.budgetpro.ui.chi.LoaiChiViewModel;
import com.learncode.budgetpro.ui.thu.LoaiThuFragment;

public class LoaiChiDetailDialog { //hien thi chi tiet LOAI CHI


    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;


    private TextView tvId,tvName;


    public LoaiChiDetailDialog(final Context context, LoaiChiFragment fragment , LoaiChi loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_chi, null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName );
        tvId.setText(""+loaiChi.lid);
        tvName.setText(loaiChi.ten);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view).
                        setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDialog.dismiss();
                            }
                        });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
