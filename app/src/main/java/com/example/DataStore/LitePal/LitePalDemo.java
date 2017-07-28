package com.example.DataStore.LitePal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.helloworld.R;

import org.litepal.LitePal;

/**
 * Created by lester.ding on 7/28/2017.
 */

public class LitePalDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litepal_demo_layout);
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.create_database:
                LitePal.getDatabase();  //根据assets/litepal.xml中的设定自动创建数据库，并添加表信息
                break;

            case R.id.add_data:
                Book book1 = new Book();
                book1.setAuthor("动感小辣鸡");
                book1.setName("魔兽世界");
                book1.setPages(259);
                book1.setPrice(16.88);
                book1.setPress("中国邮电出版社");
                book1.save();   //此方法是调用Book类的父类DataSupport中的save方法
                break;

            case R.id.update_data:
                //更新方式一
                Book book2 = new Book();
                book2.setAuthor("小陶子");
                book2.setName("我们的少年时代");
                book2.setPages(308);
                book2.setPrice(46.7);
                book2.setPress("中国湖南出版社");
                book2.save();
                book2.setPages(334);    //修改book2的页数
                book2.setPrice(54.1);   //修改book2的售价
                book2.save();           //判断book2是已存储的对象，直接更新修改内容
                //更新方式二
                Book book = new Book();                     //新建一个Book对象用于构成更新语句
                book.setPress("上海脑残出版社");              //更新出版社
                book.setToDefault("price");                 //将price字段更新成默认值（直接设置0的话LitePal会视为无效，因为Book实例化后price字段已经被初始为0了）
                book.updateAll("author=?", "动感小辣鸡");    //第一个为where条件，后面几个为参数
                break;

            case R.id.delete_data:
                break;

            case R.id.query_data:
                break;
        }
    }
}
