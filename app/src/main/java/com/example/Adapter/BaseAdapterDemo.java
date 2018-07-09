package com.example.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Bean.ItemBean;
import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterDemo extends Activity {

	private ListView lv;
	private MyBaseAdapter adapter;
	private List<ItemBean> list =  new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseadapter_demo);

		//初始化
		lv = (ListView) findViewById(R.id.list);
		//创建虚拟数据
		for (int i=0; i<20; i++)
			list.add(new ItemBean(R.mipmap.ic_launcher, "标题"+i, "内容"+i));
		//初始化适配器，并绑定数据
		adapter = new MyBaseAdapter(this, list);
		//适配器绑定视图控件ListView, 画面显示数据
		lv.setAdapter(adapter);
		//添加点击事件
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ItemBean item = list.get(position);
				String title = item.getItemTitle();
				String content = item.getItemContent();
				Toast.makeText(BaseAdapterDemo.this, title+": "+content, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
