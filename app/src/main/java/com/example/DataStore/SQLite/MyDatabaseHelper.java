package com.example.DataStore.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lester.ding on 7/26/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private String CREATE_BOOK = "create table if not exists Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";

    private String CREATE_CATEGORY = "create table if not exists Category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    private Context context;

    //构造函数
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //MyDatabaseHelper实例化后，首次调用getReadableDatabase或者getWritableDatabase都会执行此方法，通常在此创建表和一些初始化操作
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(context, "Table created successfully", Toast.LENGTH_SHORT).show();
    }

    //数据库版本变更时被调用，一般用于备份数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
