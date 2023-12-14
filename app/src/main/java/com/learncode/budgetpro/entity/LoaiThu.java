package com.learncode.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class LoaiThu {
    @PrimaryKey(autoGenerate = true)

    public int lid;  //loaithu ID
    @ColumnInfo(name = "ten")
    public String ten;
}
