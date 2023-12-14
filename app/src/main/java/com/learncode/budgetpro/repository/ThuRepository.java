package com.learncode.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learncode.budgetpro.dao.AppDatabase;
import com.learncode.budgetpro.dao.LoaiThuDao;
import com.learncode.budgetpro.dao.ThuDao;
import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.ThongKeLoaiThu;
import com.learncode.budgetpro.entity.Thu;

import java.util.List;

public class ThuRepository {


   private ThuDao mThuDao;
   private LiveData<List<Thu>>mAllThu;
   public ThuRepository(Application application){
       this.mThuDao = AppDatabase.getDatabase(application).thuDao();
       mAllThu = mThuDao.findAll();
    }
    public LiveData<List<Thu>>getmAllThu(){
       return mAllThu;
        }


    public LiveData<Float>sumTongThu(){
        return mThuDao.sumTongThu();
    }

    public LiveData<List<ThongKeLoaiThu>>sumByLoaiThu(){
        return mThuDao.sumByLoaiThu();
    }



        public void insert(Thu thu){
       new InsertAsyncTask(mThuDao).execute(thu);
    }
    public void delete(Thu thu){
       new DeleteAsyncTask(mThuDao).execute(thu);
        }
        public void update(Thu thu){
       new UpdateAsyncTask(mThuDao).execute(thu);
    }

    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

    class UpdateAsyncTask extends AsyncTask<Thu,Void,Void>{
       private ThuDao mThuDao;
       public UpdateAsyncTask(ThuDao thuDao){
           this.mThuDao=thuDao;
        }
        @Override
        protected Void doInBackground(Thu... thus){
           mThuDao.update(thus[0]);
           return null;
        }
    }
    class InsertAsyncTask  extends AsyncTask<Thu ,Void ,Void>{
       private ThuDao mThuDao;
       public InsertAsyncTask(ThuDao thuDao){
           this.mThuDao = thuDao;
       }
       @Override
        protected Void doInBackground(Thu... thus){
           mThuDao.insert(thus[0]);
           return null;
       }
    }
    class DeleteAsyncTask extends AsyncTask<Thu,Void,Void>{
       private ThuDao mThuDao;
       public DeleteAsyncTask(ThuDao thuDao){
           this.mThuDao=thuDao;
       }
       @Override
        protected Void doInBackground(Thu...thus){
           mThuDao.delete(thus[0]);
           return null;
       }
    }
}
