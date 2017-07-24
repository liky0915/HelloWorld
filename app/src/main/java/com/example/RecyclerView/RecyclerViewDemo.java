package com.example.RecyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Bean.ItemBean;
import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends AppCompatActivity {

    private RecyclerView rv;
    private List<ItemBean> list =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_demo_layout);
        getSupportActionBar().hide();   //隐藏原始标题栏

        //初始化
        rv = (RecyclerView) findViewById(R.id.recycler_view);

        //创建虚拟数据
        for (int i=0; i<20; i++)
            list.add(new ItemBean(R.mipmap.ic_launcher, "标题"+i, "内容"+i));

        //创建layoutManager并绑定RecyclerView，layoutManager用于指定RecyclerView的布局方式，这里使用LinearLayoutManager的为线性布局，可实现与ListView类似的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);   //设置布局排列方向为横向
        rv.setLayoutManager(layoutManager);

        //创建适配器并绑定RecyclerView
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        rv.setAdapter(adapter);
    }
}
