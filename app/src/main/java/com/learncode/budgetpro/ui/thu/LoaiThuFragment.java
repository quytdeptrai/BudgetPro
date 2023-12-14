package com.learncode.budgetpro.ui.thu;

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
import com.learncode.budgetpro.adapter.LoaiThuRecyclerviewAdapter;

import com.learncode.budgetpro.adapter.ThuRecyclerviewAdapter;
import com.learncode.budgetpro.dialog.LoaiThuDetailDialog;
import com.learncode.budgetpro.dialog.LoaiThuDialog;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.Thu;

import java.lang.reflect.Array;
import java.util.List;

public class LoaiThuFragment extends Fragment {

    private RecyclerView mRv;
    private LoaiThuRecyclerviewAdapter mAdapter;
    private LoaiThuViewModel mViewModel;


    public static LoaiThuFragment newInstance() {
        return new LoaiThuFragment();
    }

    public LoaiThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_thu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view .findViewById(R.id.recyclerView);
        mAdapter = new LoaiThuRecyclerviewAdapter(getActivity());

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        final  LoaiThuFragment currentFrament = this;
        mAdapter.setOnItemEditCLickListener(new ItemCLickListenner() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position);
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(),currentFrament,loaiThu);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemCLickListenner() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position);
                LoaiThuDetailDialog dialog = new LoaiThuDetailDialog(getActivity(),currentFrament,loaiThu);
                dialog.show();
            }
        });


//Hiển thị Thông tin LOAI THU
        mAdapter.setOnItemViewClickListener(new ItemCLickListenner() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position) ;
                    LoaiThuDetailDialog dialog = new LoaiThuDetailDialog(getActivity(),currentFrament,loaiThu);
                    dialog.show();
                }

        });

//Xoa thông tin loại thu
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT//Lướt qua trái Hoặc qua phải
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();//vị trí
                        LoaiThu lt = mAdapter.getItem(position);//lấy đối tương Của loại thu

                        Toast.makeText(getActivity(),"LOẠI THU ĐÃ ĐƯỢC XÓA",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);//và thực hiện xóa

                    }
                }
        );
        helper.attachToRecyclerView(mRv);//để xử lý trượt của người dùng

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setlist(loaiThus);
            }
        });
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaithus ) {
                mAdapter.setlist(loaithus);
            }
        });


    }

}