package com.learncode.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.learncode.budgetpro.R;
import com.learncode.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiRecylerviewAdapter extends RecyclerView.Adapter<LoaiChiRecylerviewAdapter.LoaiChiViewHolder> {

    private LayoutInflater mLayoutInfaler;
    private List<LoaiChi> mList;

    public static ItemCLickListenner itemEditCLickListener;
    public static ItemCLickListenner itemViewClickListener;



    public LoaiChiRecylerviewAdapter(Context context) {
        mLayoutInfaler = LayoutInflater.from(context);
    }
    //cap nhat LOAI CHI

    public  void setOnItemEditCLickListener(ItemCLickListenner itemEditCLickListener) {
        LoaiChiRecylerviewAdapter.itemEditCLickListener = itemEditCLickListener;
    }

    public  void setOnItemViewClickListener(ItemCLickListenner itemViewClickListener) {
        LoaiChiRecylerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = mLayoutInfaler.inflate(R.layout.recyclerview_loai_chi_item,parent,false);

        return new LoaiChiViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position    ) {
        if(mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.position = position;//xac dinh lai noi dung
        }

    }

    @Override
    public int getItemCount() {

        if(mList == null)
        return 0;
        return mList.size();
    }


    public LoaiChi getItem(int position){
        if(mList == null || position > mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        public ImageView ivView,ivEdit;
        public CardView cv;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView =itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {   //Phan EDIT LOAI CHI
                @Override
                public void onClick(View v) {
                    if(itemViewClickListener != null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemEditCLickListener != null){
                        itemEditCLickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
