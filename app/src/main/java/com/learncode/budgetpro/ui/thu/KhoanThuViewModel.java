package com.learncode.budgetpro.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.Thu;
import com.learncode.budgetpro.repository.LoaiThuRepository;
import com.learncode.budgetpro.repository.ThuRepository;

import java.util.List;

public class KhoanThuViewModel extends AndroidViewModel  {
    private ThuRepository mThurepsitory;
    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;


    public KhoanThuViewModel  (@NonNull Application application) {
        super(application);
        mThurepsitory = new ThuRepository(application);
        mAllThu = mThurepsitory.getAllThu();
        mLoaiThuRepository = new LoaiThuRepository(application);
        mAllLoaiThu = mLoaiThuRepository.getAllLoaiThu();
    }

    public LiveData<List<LoaiThu>>getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public LiveData<List<Thu>>getAllThu(){
        return mAllThu;
    }
    public void insert (Thu Thu){
        mThurepsitory.insert(Thu);
    }

    public void delete(Thu Thu){
        mThurepsitory.delete(Thu);
    }


    public void update(Thu Thu){
        mThurepsitory.update(Thu);
    }

}