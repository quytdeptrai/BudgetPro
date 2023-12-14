package com.learncode.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learncode.budgetpro.dao.AppDatabaseChi;
import com.learncode.budgetpro.dao.ChiDao;
import com.learncode.budgetpro.dao.LoaiChiDao;
import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.LoaiChi;

import java.util.List;

public class ChiRepository {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiRepository(Application application) {
        this.mChiDao = AppDatabaseChi.getDatabaseChi(application).chiDao();
        mAllChi =   mChiDao.fidAll();
    }


    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

    //phần thống kê loại chi
    public LiveData<Float> sumTongCHi() {//trả về số tiền chi đc
        return mChiDao.sumTongChi();
    }

    public void insert(Chi Chi){
        new InsertAsyncTask(mChiDao).execute(Chi);
    }

    public void delete(Chi Chi){
        new DeletetAsyncTask(mChiDao).execute(Chi);
    }


    class InsertAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;

        public InsertAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(Chi...chis) {
            mChiDao.insert(chis[0]);
            return null;
        }
    }

//CAP NHAT LOAI CHI
    public void update(Chi Chi){
        new UpdateAsyncTask(mChiDao  ).execute(Chi);
    }
    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;

        public UpdateAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.delete(Chis[0]);
            return null;
        }
    }

    //Xóa LOẠI CHI
    class DeletetAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;

        public DeletetAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.delete(Chis[0]);
            return null;
        }
    }
}
