package com.example.DataStore.SQLite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/26/2017.
 */

public class SQLiteDemo extends AppCompatActivity {

    private Button createDatabase;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_demo_layout);

        //初始化
        createDatabase = (Button) findViewById(R.id.create_database);
        dbHelper = new MyDatabaseHelper(this, "Books.db", null, 2); //数据库名Books.db

        //点击按钮创建数据库
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
    }
}
