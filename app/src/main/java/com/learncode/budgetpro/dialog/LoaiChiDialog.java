package com.learncode.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.learncode.budgetpro.R;
import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.ui.chi.LoaiChiFragment;
import com.learncode.budgetpro.ui.chi.LoaiChiViewModel;

public class LoaiChiDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;


    private TextInputEditText etld,etName;
    private boolean mEditMode;//trong che do EDIT MODE HAY KHONG?

    public LoaiChiDialog(Context context, LoaiChiFragment fragment,LoaiChi ... loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_chi,null);
        etld = view.findViewById(R.id.etld);
        etName = view.findViewById(R.id.etName);

        if(loaiChi !=null && loaiChi.length>0){
            etld.setText(""+loaiChi[0].lid);//dang o che do cap nhat
            etName.setText(loaiChi[0].ten);
            mEditMode = true;
        }else{
            mEditMode = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("ĐÓNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("LƯU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoaiChi lt = new LoaiChi();
                        lt.ten =etName.getText().toString();
                        if(mEditMode){
                            lt.lid =Integer.parseInt(etld.getText().toString());
                            mViewModel.update(lt);
                        }else{
                            mViewModel.insert(lt);
                            Toast.makeText(context,"LOẠI CHI ĐÃ ĐƯỢC LƯU !",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
