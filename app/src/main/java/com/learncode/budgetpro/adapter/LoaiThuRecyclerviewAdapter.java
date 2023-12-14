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
import com.learncode.budgetpro.entity.LoaiThu;

import java.util.List;

public class LoaiThuRecyclerviewAdapter extends RecyclerView.Adapter<LoaiThuRecyclerviewAdapter.LoaithuViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<LoaiThu> mlist;

    public static ItemCLickListenner itemEditCLickListener;
    public static ItemCLickListenner itemViewClickListener;

    public LoaiThuRecyclerviewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);

    }

//
    public  void setOnItemEditCLickListener(ItemCLickListenner itemEditCLickListener) {
        LoaiThuRecyclerviewAdapter.itemEditCLickListener = itemEditCLickListener;
    }

    public  void setOnItemViewClickListener(ItemCLickListenner itemViewClickListener) {
        LoaiThuRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }
    //


    @NonNull
    @Override
    public LoaithuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recylerview_loai_thu_item,parent,false);

        return new LoaithuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaithuViewHolder holder, int position) {
        if(mlist  != null){
            holder.tvName.setText(mlist.get(position).ten);
            holder.position = position;

        }

    }

    @Override
    public int getItemCount() {
        if(mlist == null)
        return 0;
        return  mlist.size();
    }
    public LoaiThu getItem(int position){
        if(mlist == null || position>=mlist.size()){
            return null;
        }
        return mlist.get(position);
    }

    public void setlist(List<LoaiThu> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();//cap nhat   lai du lieu
    }

    public static class LoaithuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit,imView;
        public CardView cv;
        public int position;

        public LoaithuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            imView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
            imView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemViewClickListener !=null){//Kiểm tra
                        itemViewClickListener.onItemClick(position);//vị trí cần nhấn
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditCLickListener != null){
                        itemEditCLickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
