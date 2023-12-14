package com.learncode.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learncode.budgetpro.R;
import com.learncode.budgetpro.entity.ThongKeLoaiChi;
import com.learncode.budgetpro.entity.ThongKeLoaiThu;

import java.util.List;

public class ThongKeLoaiChiRecylerViewAdapter
        extends RecyclerView.Adapter<ThongKeLoaiChiRecylerViewAdapter.ThongKeLoaiThuViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<ThongKeLoaiThu> mList;
    public ThongKeLoaiChiRecylerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeLoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recylerview_thongke_loaithu,parent,false);
        return new ThongKeLoaiThuViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiThuViewHolder holder, int position) {
        if (mList != null){
            holder.tvTenLoaiThu.setText(mList.get(position).ten);
            holder.etTongLoaiThu.setText(""+mList.get(position).tong);
        }

    }

    @Override
    public int getItemCount() {
        if(mList  == null)
        return 0;
        return mList.size();
    }

    public void setList(List<ThongKeLoaiChi> list){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenLoaiThu;
        public EditText etTongLoaiThu;

        public ThongKeLoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenLoaiThu = itemView.findViewById(R.id.tvTenLoaiThu);
            etTongLoaiThu = itemView.findViewById(R.id.etTongLoaiThu);

        }
    }
}
