package com.learncode.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;
import com.learncode.budgetpro.R;
import com.learncode.budgetpro.adapter.LoaiChiSpinnerAdapter;
import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.ui.chi.KhoanChiFragment;
import com.learncode.budgetpro.ui.chi.KhoanChiViewModel;

import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;


    private TextInputEditText etld,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;//trong che do EDIT MODE HAY KHONG?
    private LoaiChiSpinnerAdapter mAdapter;

    public ChiDialog(final Context context, KhoanChiFragment fragment, Chi ... Chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etld = view.findViewById(R.id.etld);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        //sipner
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiChiSpinnerAdapter( fragment.getActivity());
        //lỗi spinner
//        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
//            @Override
//            public void onChanged(List<LoaiChi> loaiChis) {
//                mAdapter.setList(loaiChis);
//            }
//        });
        spType.setAdapter(mAdapter);


        if(Chi !=null && Chi.length>0){
            etld.setText(""+Chi[0].tid);//dang o che do cap nhat
            etName.setText(Chi[0].ten);
            etAmount .setText(Chi[0].ten);
            etNote.setText(Chi[0].ghichu);
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
                        Chi lt = new Chi();
                        lt.ten =etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu=etNote.getText().toString();
                        //Phần thông tin người dùng đưa vào adapter
//                        lt.ltid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        //

                        if(mEditMode){
                            lt.tid =Integer.parseInt(etld.getText().toString());
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
