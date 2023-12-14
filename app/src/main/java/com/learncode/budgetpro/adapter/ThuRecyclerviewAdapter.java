package com.learncode.budgetpro.adapter;

import android.annotation.SuppressLint;
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
import com.learncode.budgetpro.entity.Thu;

import java.util.List;

public class ThuRecyclerviewAdapter extends RecyclerView.Adapter<ThuRecyclerviewAdapter.ThuViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Thu> mlist;

    public static ItemCLickListenner itemEditCLickListener;
    public static ItemCLickListenner itemViewClickListener;

    public ThuRecyclerviewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);

    }
//
    public  void setOnItemEditCLickListener(ItemCLickListenner itemEditCLickListener) {
        ThuRecyclerviewAdapter.itemEditCLickListener = itemEditCLickListener;
    }

    public  void setOnItemViewClickListener(ItemCLickListenner itemViewClickListener) {
        ThuRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }
    //

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.recylerview_thu_item,parent,false);

        return new ThuViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder,  int position) {
        if(mlist  != null){
            holder.tvName.setText(mlist.get(position).ten);
            holder.tvAmount.setText(""+mlist.get(position).sotien+"Dong");
            holder.position = position;

        }

    }

    @Override
    public int getItemCount() {
        if(mlist == null)
        return 0;
        return  mlist.size();
    }
    public Thu getItem(int position){
        if(mlist == null || position>=mlist.size()){
            return null;
        }
        return mlist.get(position);
    }

    public void setlist(List<Thu> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();//cap nhat   lai du lieu
    }

    public static class ThuViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvAmount;
        public ImageView ivEdit,imView;
        public CardView cv;
        public int position;

        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            imView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;
            imView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemViewClickListener !=null){
                        itemEditCLickListener.onItemClick(position);
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
