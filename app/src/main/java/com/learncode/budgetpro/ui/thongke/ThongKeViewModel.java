package com.learncode.budgetpro.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learncode.budgetpro.entity.ThongKeLoaiChi;
import com.learncode.budgetpro.entity.ThongKeLoaiThu;
import com.learncode.budgetpro.entity.Thu;
import com.learncode.budgetpro.repository.ThuRepository;

import java.util.List;

public class ThongKeViewModel  extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<Float>mTongThu;
    private LiveData<Float>mTongChi;
    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;
    private LiveData<List<ThongKeLoaiChi>> mThongKeLoaiChis;

    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mThuRepository = new ThuRepository(application);
        mTongThu = mThuRepository.sumTongThu();
        mThongKeLoaiThus = mThuRepository.sumByLoaiThu();
    }
    public LiveData<Float>getTongThu(){
        return mTongThu;
    }

    public LiveData<Float>getTongChi(){
        return mTongChi;
    }

    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus() {
        return mThongKeLoaiThus;
    }

    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChis() {
        return mThongKeLoaiChis;
    }
}
