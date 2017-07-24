package com.example.UICustomViews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/4/2017.
 */

public class TitleLayout extends LinearLayout {

    private TextView btn_back, btn_edit;

    //此处一定要使用带2个或3个参数的构造函数，因为在布局中引入此自定义控件时只有带2个或3个参数的构造函数才会被自动调用
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //对标题栏布局进行加载
        LayoutInflater.from(context).inflate(R.layout.common_title, this);
        //初始化
        btn_back = (TextView) findViewById(R.id.back);
        btn_edit = (TextView) findViewById(R.id.edit);
        //添加返回点击事件监听器
        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        //添加编辑点击事件监听器
        btn_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Editing", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
