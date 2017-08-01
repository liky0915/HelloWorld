package com.example.DataStore.LitePal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.helloworld.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lester.ding on 7/28/2017.
 */

public class LitePalDemo extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litepal_demo_layout);
        //初始化
        text = (TextView) findViewById(R.id.query_result);
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
                //删除方式一
                Book book3 = new Book();
                book3.setAuthor("山歌");
                book3.setName("十里春风不如你");
                book3.setPages(211);
                book3.setPrice(11.2);
                book3.setPress("中国湖南出版社");
                book3.save();
                book3.delete();
                //删除方式二
                Book book4 = new Book();
                book4.setAuthor("山哥");
                book4.setName("十里春风不如你");
                book4.setPages(211);
                book4.setPrice(11.2);
                book4.setPress("中国湖南出版社");
                book4.save();
                DataSupport.deleteAll(Book.class, "author=?", "山哥");//第一个参数指定要删哪张表，第二和三个参数组成过滤条件
                break;

            case R.id.query_data:
                //查询方式一：使用LitePal查询API（推荐）
                //Book firstBook = DataSupport.findFirst(Book.class);     //查询表中第一条数据
                //Book lastBook = DataSupport.findLast(Book.class);       //查询表中最后条数据
                //List<Book> allBooks = DataSupport.findAll(Book.class);  //查询表中所有数据
                //组合条件查询
                List<Book> selBooks = DataSupport.select("author", "name")
                        .where("author=?", "小陶子")
                        .order("pages desc")
                        .find(Book.class);
                //读取查询结果
                StringBuilder content = new StringBuilder();
                for(Book b: selBooks){
                    content.append("作者："+b.getAuthor()+", 书名："+b.getName()+"\n");
                }

                //查询方式二：原生SQL查询
                Cursor c = DataSupport.findBySQL("select author, name from Book where author=?", "动感小辣鸡");
                if(c.moveToFirst()){
                    do {
                        //从游标中提取数据
                        String author = "作者："+c.getString(c.getColumnIndex("author"));
                        String name = "书名："+c.getString(c.getColumnIndex("name"));
                        content.append(author+", "+name+"\n");
                    } while (c.moveToNext());
                }
                c.close(); //关闭游标

                //显示最终查询结果
                text.setText(content.toString());
                break;
        }
    }
}
