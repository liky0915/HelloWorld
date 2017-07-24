package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.ItemBean;
import com.example.helloworld.R;

import java.util.List;

/**
 * Created by lester.ding on 6/14/2017.
 */

public class MyBaseAdapter extends BaseAdapter {

    private List<ItemBean> list;
    private LayoutInflater inflater;

    //构造函数
    public MyBaseAdapter(Context context, List<ItemBean> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*逗比式item布局载入方式(完全没有利用到ListView的数据缓存机制，每次data build的时候都要重新载入布局文件)
            View view = inflater.inflate(R.layout.baseadapter_item_layout, null);
            //获取item布局上的各个控件对象
            ImageView iv = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView content = (TextView) view.findViewById(R.id.content);
            //得到当前要显示的数据对象，并将数据绑定到相应的控件对象上
            ItemBean bean = list.get(position);
            iv.setImageResource(bean.ItemImageResid);
            title.setText(bean.ItemTitle);
            content.setText(bean.ItemContent);
            //返回自定义的View对象
            return view;
        */

        /*普通式item布局载入方式(利用到了ListView的数据缓存机制，只有当布局不在缓存中才重新载入，效率提高，但重复使用findViewById仍然耗时)
            if(convertView == null){
                convertView = inflater.inflate(R.layout.baseadapter_item_layout, null);
            }
            //获取item布局上的各个控件对象
            ImageView iv = (ImageView) convertView.findViewById(R.id.image);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            //得到当前要显示的数据对象，并将数据绑定到相应的控件对象上
            ItemBean bean = list.get(position);
            iv.setImageResource(bean.ItemImageResid);
            title.setText(bean.ItemTitle);
            content.setText(bean.ItemContent);
            //返回系统提供的View对象
            return convertView
        */

        /*文艺式item布局载入方式(不仅利用到了ListView的数据缓存机制，还自定义内部类实现了布局控件对象的缓存，效率最高)*/
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.baseadapter_item_layout, null);
            //获取item布局上的各个控件对象一次性装载进ViewHolder对象中
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            //绑定ViewHolder到convertView上
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //得到当前要显示的数据对象，并将数据绑定到相应的控件对象上
        ItemBean bean = list.get(position);
        viewHolder.iv.setImageResource(bean.ItemImageResid);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);
        //返回系统提供的View对象
        return convertView;
    }

    //自定义内部类用于一次性装载item布局文件的控件对象,避免重复使用findViewById
    class ViewHolder {
        private ImageView iv;
        private TextView title, content;
    }
}
