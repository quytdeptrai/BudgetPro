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
import com.learncode.budgetpro.adapter.LoaiThuSpinnerAdapter;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.Thu;
import com.learncode.budgetpro.ui.thu.KhoanThuFragment;
import com.learncode.budgetpro.ui.thu.KhoanThuViewModel;
import com.learncode.budgetpro.ui.thu.ThuFragment;

import java.util.List;


public class ThuDialog {

    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId ,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiThuSpinnerAdapter mAdapter;


    public ThuDialog(final Context context, KhoanThuFragment fragment , Thu ...thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_thu, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName );
        etAmount = view .findViewById(R.id.etAmount);
        etNote = view .findViewById(R.id.etNote);
        //spinner
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        spType.setAdapter(mAdapter);
       //

        if(thu != null && thu.length>0){
            etId.setText(""+thu[0].tid);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(thu[0].ghichu);
            mEditMode = true;
        }else{
            mEditMode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("ĐÓNG" , new  DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();//dong hop thoai

                    }
                })
                .setPositiveButton("LƯU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Thu lt = new Thu();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu=etNote.getText().toString();
                        //Phần LUU spinner
                        lt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;

                        if (mEditMode){
                            lt.tid = Integer.parseInt(etId.getText().toString());
                            mViewModel.update(lt);

                        }else {
                            mViewModel.insert(lt);
                            Toast.makeText( context,"LOẠI THU ĐƯỢC LƯU",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

        mDialog = builder.create();

    }
    public void show(){
        mDialog.show();
    }
}
