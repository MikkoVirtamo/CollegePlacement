package com.example.robert.test4;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBhelper extends SQLiteAssetHelper {
    static final String DATABASE_NAME = "coappV2.db";
    static final int DATABASE_Ver = 1;

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_Ver);
    }


    //@Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {

  //  }

    //@Override
    //public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    //}
}
