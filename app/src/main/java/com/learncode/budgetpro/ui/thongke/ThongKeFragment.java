package com.learncode.budgetpro.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.learncode.budgetpro.R;
import com.learncode.budgetpro.adapter.ThongKeLoaiChiRecylerViewAdapter;
import com.learncode.budgetpro.adapter.ThongKeLoaiThuRecylerViewAdapter;
import com.learncode.budgetpro.entity.ThongKeLoaiChi;
import com.learncode.budgetpro.entity.ThongKeLoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {
    private ThongKeViewModel mThongKeViewModel;
    private EditText mEtTongThu;
    private RecyclerView rvThongKeLoaiThu;
    private ThongKeLoaiThuRecylerViewAdapter mThongKeLoaiThuAdapTer;

    //phần chi
    private  EditText mEtTongChi;
    private RecyclerView rvThongKeLoaiChi;
    private ThongKeLoaiChiRecylerViewAdapter mThongKeLoaiChiAdapter;







    public  ThongKeFragment() {
    }


    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke3, container, false);

        mEtTongThu = view .findViewById(R.id.etThongThu);
        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeLoaiThu);
        //phan thong ke chi
//        mEtTongChi = view.findViewById(R.id.etLoaiChi);
//        rvThongKeLoaiChi = view.findViewById(R.id.rvThongKeLoaiChi);
        //

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);
        mThongKeLoaiThuAdapTer = new ThongKeLoaiThuRecylerViewAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(mThongKeLoaiThuAdapTer);

        //Thong ke chi
//        mThongKeLoaiChiAdapter = new ThongKeLoaiChiRecylerViewAdapter(getActivity());
//        rvThongKeLoaiChi.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvThongKeLoaiChi.setAdapter(mThongKeLoaiChiAdapter);

//        mThongKeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
//            @Override
//            public void onChanged(Float tong) {
//                mEtTongChi.setText(""+tong);
//            }
//        });


        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongThu.setText("" + tong);
            }
        });

//        mThongKeViewModel.getThongKeLoaiChis().observe(getActivity(), new Observer<List<ThongKeLoaiChi>>() {
//            @Override
//            public void onChanged(List<ThongKeLoaiChi> thongKeLoaiChis) {
//                mThongKeLoaiChiAdapter.setList(thongKeLoaiChis);
//            }
//        });

        mThongKeViewModel.getThongKeLoaiThus().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                mThongKeLoaiThuAdapTer.setList(thongKeLoaiThus);
            }
        });

        return view  ;
    }
}