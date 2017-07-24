package com.example.BestPractice.FragmentBestPractice;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.List;

/**
 * Created by lester.ding on 7/14/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private Context context;
    private List<NewsBean> newsList;

    //适配器构造函数，用于接收传递进来的数据
    public NewsAdapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.newsList = list;
    }

    //指定item中各个所需数据绑定的控件
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title_item);
        }
    }

    @Override   //指定item的布局文件和点击事件
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title_item_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsBean item = newsList.get(holder.getAdapterPosition());//获取当前用户点击的位置
                if(v.getContext().getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
                    //竖屏时打开新闻内容Activity
                    NewsContentActivity.actionStart(context, item.getTitle(), item.getContent());
                }
                else{
                    //横屏时刷新右侧新闻内容Fragment
                    FragmentBestPractice fragmentBestPractice = (FragmentBestPractice)context;//一定要转化传进来的上下文为FragmentBestPractice实例后才能使用getSupportFragmentManager
                    NewsContentFragment newsContentFragment = (NewsContentFragment)fragmentBestPractice.getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(item.getTitle(), item.getContent());
                }
            }
        });
        return holder;
    }

    @Override   //指定item中各个所需数据绑定的控件如何和数据模型进行一一绑定
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsBean news = newsList.get(position);
        holder.title.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
