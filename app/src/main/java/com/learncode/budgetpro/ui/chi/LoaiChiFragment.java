package com.learncode.budgetpro.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.learncode.budgetpro.R;
import com.learncode.budgetpro.adapter.ItemCLickListenner;
import com.learncode.budgetpro.adapter.LoaiChiRecylerviewAdapter;
import com.learncode.budgetpro.dialog.LoaiChiDetailDialog;
import com.learncode.budgetpro.dialog.LoaiChiDialog;
import com.learncode.budgetpro.dialog.LoaiThuDetailDialog;
import com.learncode.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiFragment extends Fragment {

    private RecyclerView mRv;
    private LoaiChiRecylerviewAdapter mAdapter;
    private LoaiChiViewModel mViewModel;

    public static LoaiChiFragment newInstance() {
        return new LoaiChiFragment();
    }

    public LoaiChiViewModel getViewModel()   {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_chi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView2);
        mAdapter = new LoaiChiRecylerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        final LoaiChiFragment currentFrament = this;
        //
        mAdapter.setOnItemEditCLickListener(new ItemCLickListenner() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(),currentFrament,loaiChi);
                dialog.show();
            }
        });

        //xu ly su kien HIEN THI CHI TIET LOAI CHI
        mAdapter.setOnItemViewClickListener(new ItemCLickListenner() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDetailDialog dialog = new LoaiChiDetailDialog (getActivity(),currentFrament,loaiChi);
                dialog.show();
            }
        });


        ItemTouchHelper helper = new ItemTouchHelper( // xoa du lieu chi
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        LoaiChi lt = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Loại Chi Đã Được Xóa !", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);

                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);
        mViewModel .getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });




    }

}