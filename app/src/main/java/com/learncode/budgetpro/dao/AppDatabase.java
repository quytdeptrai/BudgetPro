package com.learncode.budgetpro.dao;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.learncode.budgetpro.entity.LoaiThu;
import com.learncode.budgetpro.entity.Thu;

@Database(entities = {LoaiThu.class, Thu.class},version = 3)

public abstract class AppDatabase extends RoomDatabase {
    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();


    public static AppDatabase INSTANCE;//luu app
    private static RoomDatabase.Callback callback = new Callback() {//goi thuc hien Popdata
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };


    public static AppDatabase getDatabase(final Context context){//kiem tra INSTANCE
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),//tao ra database
                        AppDatabase.class,"personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)  //khoi tao du lieu ban dau khi chay ung dung
                        .build() ; //the hien cua lop appdatabase
            }
        }
        return INSTANCE;
    }

    public static class PopulateData extends AsyncTask<Void, Void ,Void > {
        private LoaiThuDao loaiThuDao;
        private ThuDao thuDao;


        public PopulateData( AppDatabase db ) {
            loaiThuDao = db.loaiThuDao();
            thuDao = db.thuDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaithus = new String[]{"LƯƠNG","THƯỞNG","CỔ PHIẾU"};//them cac du lieu
            for(String it: loaithus){
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);


            }
            Thu thu = new Thu();
            thu.ten = "Luong thang 1";
            thu.sotien = 3000;
            thu.ltid = 2;
            thu.ghichu = "";
            thuDao.insert(thu);

            Log.d("BudgetPro:","insert data");
            return null;
        }
    }



}
