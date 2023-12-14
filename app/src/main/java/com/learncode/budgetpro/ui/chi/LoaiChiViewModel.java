package com.learncode.budgetpro.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.repository.LoaiChiRepository;

import java.util.List;

public class LoaiChiViewModel extends AndroidViewModel {

    private LoaiChiRepository mLoaiChiRepository;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiViewModel(@NonNull Application application) {
        super(application);

        mLoaiChiRepository = new LoaiChiRepository(application);
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi) {
        mLoaiChiRepository.insert(loaiChi);
    }

    public void delete(LoaiChi loaiChi){
        mLoaiChiRepository.delete(loaiChi);
    }
    public void update (LoaiChi loaiChi){
        mLoaiChiRepository.update(loaiChi);
    }
}