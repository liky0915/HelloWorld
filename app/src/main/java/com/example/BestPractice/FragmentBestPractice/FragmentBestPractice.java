package com.example.BestPractice.FragmentBestPractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lester.ding on 7/14/2017.
 */

public class FragmentBestPractice extends AppCompatActivity {

    private RecyclerView news_title_list;
    private List<NewsBean> list =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_best_practice_layout);

        news_title_list = (RecyclerView) findViewById(R.id.news_title_list);

        //创建虚拟数据
        for (int i=0; i<10; i++) {
            NewsBean news = new NewsBean();
            news.setTitle("新闻标题"+i);
            news.setContent("新闻内容"+i);
            list.add(news);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        news_title_list.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(this, list);
        news_title_list.setAdapter(adapter);
    }
}
