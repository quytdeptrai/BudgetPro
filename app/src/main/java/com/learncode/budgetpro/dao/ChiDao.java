package com.learncode.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learncode.budgetpro.entity.Chi;
import com.learncode.budgetpro.entity.LoaiChi;

import java.util.List;


@Dao

public interface ChiDao {
    @Query("SELECT * FROM chi")
    LiveData<List<Chi>> fidAll();

    @Query("SELECT sum(sotien) FROM chi")//dành cho thống kê tính tổng số chi
    LiveData<Float> sumTongChi();//tính tổng khoản chi

    @Insert
    void insert(Chi loaiChi);

    @Update
    void update(Chi loaiChi);
     @Delete
    void delete(Chi  loaiChi);

}
