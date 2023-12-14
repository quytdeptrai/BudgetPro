package com.learncode.budgetpro.ui.chi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.learncode.budgetpro.R;
import com.learncode.budgetpro.adapter.ChiViewPager2Adapter;


public class ChiFragment extends Fragment {
    private ViewPager2 mVp;//định nghĩa trường
    private TabLayout mTl;

    public ChiFragment() {

        // Required empty public constructor
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp = view.findViewById(R.id.viewPager2);//tìm kiếm
        mTl = view .findViewById(R.id.tabLayout);

        ChiViewPager2Adapter adapter = new ChiViewPager2Adapter(getActivity());//tạo ra adapter
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl , mVp, new TabLayoutMediator.TabConfigurationStrategy() {//tạo ra đối tương
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Khoản Chi");
                    tab.setIcon(R.drawable.ic_menu_camera);


            }else{
                    tab.setText("Loại Khoản Chi");
                    tab.setIcon(R.drawable.ic_menu_camera);
                }
        }
     }).attach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }
}