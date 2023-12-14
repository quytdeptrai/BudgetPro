package com.learncode.budgetpro.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.repository.ChiRepository;
import com.learncode.budgetpro.repository.LoaiThuRepository;

import java.util.List;

public class KhoanChiViewModel extends AndroidViewModel {

    private ChiRepository mChiRepository;
    private LiveData<List<Chi>> mAllChi;
    private LoaiThuRepository mLoaiChiRepository ;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public KhoanChiViewModel(@NonNull Application application) {
        super(application);
        mChiRepository = new ChiRepository(application);
        mAllChi = mChiRepository.getAllChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }

    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;
    }

    public void insert(Chi chi) {
        mChiRepository.insert(chi);
    }
    public void delete(Chi chi ){
        mChiRepository.delete(chi);
    }
    public void update(Chi chi ){
        mChiRepository.update(chi);
    }
}