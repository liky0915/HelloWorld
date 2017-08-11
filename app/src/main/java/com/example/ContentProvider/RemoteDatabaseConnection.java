package com.example.ContentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 8/10/2017.
 * 此画面是模拟外部程序画面访问本程序自定义的ContentProvider来共享数据，应部署在外部程序中且在外部程序中打开
 */

public class RemoteDatabaseConnection extends Activity {

    private String AUTHORITY = "content://com.example.helloworld.provider";
    private String newBookId, newCategoryId;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_database_connection_layout);
        result = (TextView) findViewById(R.id.result);
    }

    public void doClick(View v){
        Uri uri, newUri;
        ContentValues values = new ContentValues();
        switch (v.getId()){
            case R.id.add_data:
                //向Book表中插入数据
                uri = Uri.parse(AUTHORITY+"/book");
                values.clear();
                values.put("name", "我们的少年时代");
                values.put("author", "小陶子");
                values.put("pages", 140);
                values.put("price", 38.4);
                newUri = getContentResolver().insert(uri, values);
                newBookId = newUri.getPathSegments().get(1);
                //向Category表中插入数据
                uri = Uri.parse(AUTHORITY+"/category");
                values.clear();
                values.put("category_name", "Love Story");
                values.put("category_code", 12);
                newUri = getContentResolver().insert(uri, values);
                newCategoryId = newUri.getPathSegments().get(1);
                break;

            case R.id.query_data:
                StringBuilder builder = new StringBuilder();
                //从Book表中查询数据
                uri = Uri.parse(AUTHORITY+"/book");
                Cursor c = getContentResolver().query(uri,null,null,null,null);
                if(c!=null){
                    while(c.moveToNext()){
                        int id = c.getInt(c.getColumnIndex("id"));
                        String name = c.getString(c.getColumnIndex("name"));
                        String author = c.getString(c.getColumnIndex("author"));
                        int pages = c.getInt(c.getColumnIndex("pages"));
                        double price = c.getDouble(c.getColumnIndex("price"));
                        builder.append("书名："+name+", 作者："+author+", 页数："+pages+", 售价："+price+"\n");

                        //根据Book表中的id从Category表中查询对应id的数据
                        Cursor c1 = getContentResolver().query(Uri.parse(AUTHORITY+"/category/"+id),null,null,null,null);
                        if(c1!=null){
                            while(c1.moveToNext()){
                                String category_name = c1.getString(c1.getColumnIndex("category_name"));
                                int category_code = c1.getInt(c1.getColumnIndex("category_code"));
                                builder.append("分类名称："+category_name+", 分类编号："+category_code+"\n");
                            }
                            c1.close();
                        }
                    }
                    c.close();
                }
                result.setText(builder.toString());
                break;

            case R.id.update_data:
                //Book表更新数据
                uri = Uri.parse(AUTHORITY+"/book/"+newBookId);
                values.clear();
                values.put("name", "我的世界");
                values.put("author", "Big Daddy");
                values.put("pages", 344);
                values.put("price", 29.6);
                getContentResolver().update(uri, values, null, null);
                //Category表更新数据
                uri = Uri.parse(AUTHORITY+"/category/"+newCategoryId);
                values.clear();
                values.put("category_name", "Others");
                values.put("category_code", 99);
                getContentResolver().update(uri, values, null, null);
                break;

            case R.id.delete_data:
                //Book表删除数据
                uri = Uri.parse(AUTHORITY+"/book/"+newBookId);
                getContentResolver().delete(uri, null, null);
                //Category表删除数据
                uri = Uri.parse(AUTHORITY+"/category/"+newCategoryId);
                getContentResolver().delete(uri, null, null);
                break;
        }
    }
}
