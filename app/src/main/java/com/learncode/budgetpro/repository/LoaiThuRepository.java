package com.learncode.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learncode.budgetpro.dao.AppDatabase;
import com.learncode.budgetpro.dao.LoaiThuDao;
import com.learncode.budgetpro.entity.LoaiThu;

import java.util.List;

public class LoaiThuRepository {


    private LoaiThuDao mloaiThuDao;//ding nghia truong
    private LiveData<List<LoaiThu>> mAllLoaiThu;//ding nghia danhsach

    public LoaiThuRepository(Application application) {
        this.mloaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllLoaiThu = mloaiThuDao.findAll();//tra ve ds
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    public void insert(LoaiThu loaiThu){
        new InsertAsyncTask(mloaiThuDao).execute(loaiThu);
    }



    public void detele(LoaiThu loaiThu) {
        new DeleteAsyncTask(mloaiThuDao).execute(loaiThu);
    }

    class InsertAsyncTask extends AsyncTask<LoaiThu , Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public InsertAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.insert(loaiThus[0]);//goi inset
            return null;
        }
    }

    //Cập nhật lại loại thu
    public void update(LoaiThu loaiThu){
        new UpdateAsyncTask(mloaiThuDao).execute(loaiThu);
    }
    class UpdateAsyncTask extends AsyncTask<LoaiThu , Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.update(loaiThus[0]);//goi inset
            return null;
        }
    }




    //Xóa Loại Thu
    class DeleteAsyncTask extends AsyncTask<LoaiThu , Void, Void>{
        private LoaiThuDao mLoaiThuDao;
        public DeleteAsyncTask(LoaiThuDao loaiThuDao) {
            this.mLoaiThuDao = loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.delete(loaiThus[0]);//goi inset
            return null;
        }
    }
}
