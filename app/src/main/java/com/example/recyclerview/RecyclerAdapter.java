package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.ItemBean;
import com.example.helloworld.R;

import java.util.List;

/**
 * Created by lester.ding on 7/5/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ViewHolder> {

    private List<ItemBean> list;

    //构造函数，接受数据
    public RecyclerAdapter(List<ItemBean> list) {
        this.list = list;
    }

    //内部静态类，用于存放每个item上的各个控件对象
    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView image;
        TextView title, content;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;    //接受item最外层布局实例，用于给item或者item中的各个控件注册点击事件
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item布局文件
        //纵向滚动时，item布局应该水平设置
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_h, parent, false);
        //横向滚动时，item布局应该垂直设置
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_v, parent, false);
        //创建holder，将加载进来的布局文件中的控件都保存在holder中
        final ViewHolder holder = new ViewHolder(view);
        //添加item点击事件
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition(); //获取当前用户点击的position
                ItemBean item = list.get(position);
                Toast.makeText(v.getContext(), "你点击了"+item.getItemTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        //添加item中image点击事件
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition(); //获取当前用户点击的position
                ItemBean item = list.get(position);
                Toast.makeText(v.getContext(), "你点击了"+item.getItemTitle()+"的图片", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    //得到当前要显示的数据对象，并将数据绑定到相应的控件对象上（每次item被滚动到屏幕内时调用）
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemBean item = list.get(position);
        holder.image.setImageResource(item.getItemImageResid());
        holder.title.setText(item.getItemTitle());
        holder.content.setText(item.getItemContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
