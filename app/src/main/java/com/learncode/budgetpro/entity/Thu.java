package com.learncode.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Thu {

    @PrimaryKey(autoGenerate = true)
    public int tid;  //loaithu ID

    @ColumnInfo(name ="ltid")
    public int ltid;

    @ColumnInfo(name = "ten")
    public String ten;

    @ColumnInfo(name ="sotien")
    public float sotien;

    @ColumnInfo(name ="ghichu")
    public String ghichu;

}
