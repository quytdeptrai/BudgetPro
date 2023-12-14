package com.learncode.budgetpro.ui.chi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learncode.budgetpro.R;
import com.learncode.budgetpro.adapter.ChiRecylerviewAdapter;
import com.learncode.budgetpro.adapter.ThuRecyclerviewAdapter;
import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.Thu;
import com.learncode.budgetpro.ui.thu.KhoanThuViewModel;

import java.util.List;

public class KhoanChiFragment extends Fragment {

    private KhoanChiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecylerviewAdapter mAdapter;

    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }



    public KhoanChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new ChiRecylerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_thu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
            }
        });

    }

}