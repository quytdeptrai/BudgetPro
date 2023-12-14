package com.learncode.budgetpro.dao;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.LoaiChi;

@Database(entities = {LoaiChi.class,Chi.class}, version = 3)

public abstract class AppDatabaseChi extends RoomDatabase {
    public abstract LoaiChiDao loaiChiDao();

    public abstract ChiDao chiDao();//phần liệt kê bên Khoản chi


    public static AppDatabaseChi INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };


    public static AppDatabaseChi getDatabaseChi(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabaseChi.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabaseChi.class,"pesonal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }
    public static class PopulateData extends AsyncTask<Void ,Void ,Void>{
        private LoaiChiDao loaiChiDao;
        private ChiDao chiDao;

        public PopulateData(AppDatabaseChi db) {
            loaiChiDao = db.loaiChiDao();
            chiDao = db.chiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaichis = new String[]{"Chi Lương  ", "Chi Cổ Phiếu ", "Chi Thưởng"};
            for(String it : loaichis){
                LoaiChi lt = new LoaiChi();
                lt.ten = it;
                loaiChiDao .insert(lt);
            }
            Chi chi = new Chi();
            chi.ten ="Chi Thang 1";
            chi.sotien = 3000;
            chi.ltid = 2;
            chi.ghichu="";
            chiDao.insert(chi);
            Log.d("BudgetPro","insert data");
        return null;
        }
    }
}
