package com.learncode.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.learncode.budgetpro.R;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.ui.thu.LoaiThuFragment;
import com.learncode.budgetpro.ui.thu.LoaiThuViewModel;

public class LoaiThuDialog {

    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId ,etName;
    private boolean mEditMode;


    public LoaiThuDialog( final Context context, LoaiThuFragment fragment ,LoaiThu ... loaiThu) { // ... đọc  JAVA
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_thu, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName );

        if(loaiThu != null && loaiThu.length>0){ // Ktra dữ liệu khi người dùng cập nhật
            etId.setText(""+loaiThu[0].lid);
            etName.setText(loaiThu[0].ten);
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
                        LoaiThu lt = new LoaiThu();
                        lt.ten = etName.getText().toString();
                        if(mEditMode){
                            lt.lid = Integer.parseInt(etId.getText().toString());
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
