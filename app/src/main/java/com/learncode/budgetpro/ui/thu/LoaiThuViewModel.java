package com.learncode.budgetpro.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.learncode.budgetpro.dao.LoaiThuDao;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.Thu;
import com.learncode.budgetpro.repository.LoaiThuRepository;
import com.learncode.budgetpro.repository.ThuRepository;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuRepository mloaithurepsitory;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    private ThuRepository mThuRepository;
    private LiveData<List<Thu>> mAllThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        mloaithurepsitory = new LoaiThuRepository(application);
        mAllLoaiThu = mloaithurepsitory.getAllLoaiThu();
        mThuRepository = new ThuRepository(application);
        mAllThu = mThuRepository.getmAllThu();
    }

    public LiveData<List<LoaiThu>>getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public void insert (LoaiThu loaiThu){
        mloaithurepsitory.insert(loaiThu);
    }

    public void delete(LoaiThu loaiThu){
        mloaithurepsitory.detele(loaiThu);
    }


    public void update(LoaiThu loaiThu){
        mloaithurepsitory.update(loaiThu);
    }




    public LiveData<List<Thu>>getmAllThu(){
        return mAllThu;
    }
    public void insert (Thu thu){
        mThuRepository.insert(thu);
    }

    public void delete(Thu thu){
        mThuRepository.delete(thu);
    }


    public void update(Thu thu){
        mThuRepository.update(thu);
    }




}