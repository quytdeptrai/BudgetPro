package com.learncode.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learncode.budgetpro.entity.LoaiChi;
import com.learncode.budgetpro.entity.LoaiThu;

import java.util.List;


@Dao

public interface LoaiChiDao {
    @Query("SELECT * FROM loaichi")
    LiveData<List<LoaiChi>> fidAll();

    @Insert
    void insert(LoaiChi loaiChi);

    @Update
    void update(LoaiChi loaiChi);
     @Delete
    void delete(LoaiChi loaiChi);

}
