package com.learncode.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.learncode.budgetpro.dao.AppDatabase;
import com.learncode.budgetpro.dao.AppDatabaseChi;
import com.learncode.budgetpro.dao.LoaiChiDao;
import com.learncode.budgetpro.dao.LoaiThuDao;
import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.entity.LoaiThu;

import java.util.List;

public class LoaiChiRepository {
    private LoaiChiDao mloaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public LoaiChiRepository(Application application) {
        this.mloaiChiDao = AppDatabaseChi.getDatabaseChi(application).loaiChiDao();
        mAllLoaiChi = mloaiChiDao.fidAll();
    }


    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi){
        new InsertAsyncTask(mloaiChiDao).execute(loaiChi);
    }

    public void delete(LoaiChi loaiChi){
        new DeletetAsyncTask(mloaiChiDao).execute(loaiChi);
    }


    class InsertAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;

        public InsertAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }

//CAP NHAT LOAI CHI
    public void update(LoaiChi loaiChi){
        new UpdateAsyncTask(mloaiChiDao).execute(loaiChi);
    }
    class UpdateAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;

        public UpdateAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }

    //Xóa LOẠI CHI
    class DeletetAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;

        public DeletetAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }
}
