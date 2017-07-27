package com.example.DataStore.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/26/2017.
 */

public class SQLiteDemo extends AppCompatActivity {

    private Button createDatabase, addData, updateData, deleteData, queryData;
    private TextView queryResult;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_demo_layout);

        //初始化
        createDatabase = (Button) findViewById(R.id.create_database);
        addData = (Button) findViewById(R.id.add_data);
        updateData = (Button) findViewById(R.id.update_data);
        deleteData = (Button) findViewById(R.id.delete_data);
        queryData = (Button) findViewById(R.id.query_data);
        queryResult = (TextView) findViewById(R.id.query_result);

        //定义数据库名Books.db及其表结构（MyDatabaseHelper内定义），并非实际创建数据库
        dbHelper = new MyDatabaseHelper(this, "Books.db", null, 2);
        //实例化数据装载对象
        values = new ContentValues();

        //创建数据库（如果不存在则创建，已存在则直接打开）
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
            }
        });

        //添加数据
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                values.put("author", "动感小辣鸡");
                values.put("price", 99.8);
                values.put("pages", 101);
                values.put("name", "魔兽世界");
                db.insert("Book", null, values);
                values.clear();
                values.put("author", "陶西");
                values.put("price", 79.6);
                values.put("pages", 264);
                values.put("name", "我的少年时代");
                db.insert("Book", null, values);
                values.clear();
            }
        });

        //更新数据
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                values.put("price", 38.9);
                db.update("Book", values, "id=? and author=?", new String[]{"1", "动感小辣鸡"});
            }
        });

        //删除数据
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                db.delete("Book", "id=? and author=?", new String[]{"4", "陶西"});
            }
        });

        //查询数据
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                //定义要查询的字段
                String cols[] = {"author", "price", "pages", "name"};
                //打开游标接收查询结果
                StringBuilder content = new StringBuilder();
                Cursor cursor = db.query("Book", cols, "author=?", new String[]{"动感小辣鸡"}, null, null, "id");
                if(cursor.moveToFirst()){
                    do {
                        //从游标中提取数据
                        String author = "作者："+cursor.getString(cursor.getColumnIndex("author"));
                        String price = "售价："+cursor.getDouble(cursor.getColumnIndex("price"));
                        String pages = "页数："+cursor.getInt(cursor.getColumnIndex("pages"));
                        String name = "书名："+cursor.getString(cursor.getColumnIndex("name"));

                        content.append(author+", "+price+", "+pages+", "+name+"\n");
                    } while (cursor.moveToNext());
                }
                queryResult.setText(content.toString());
                cursor.close(); //关闭游标
            }
        });

        //直接使用SQL来进行数据库操作（仅供参考，效果同上API）
        //添加数据
        //db.execSQL("insert into Book (name, author, pages, price) values(?,?,?,?)",
        //        new String[]{"魔兽世界", "动感小辣鸡", "102", "36.4"});
        //db.execSQL("insert into Book (name, author, pages, price) values(?,?,?,?)",
        //        new String[]{"我的少年时代", "小陶子", "334", "72.6"});
        //更新数据
        //db.execSQL("update Book set price = ? where name = ?", new String[]{"10.99", "魔兽世界"});
        //删除数据
        //db.execSQL("delete from Book where pages > ?", new String[]{"200"});
        //查询数据
        //db.rawQuery("select * from Book", null);
    }
}
