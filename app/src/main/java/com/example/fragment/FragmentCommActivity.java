package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;

public class FragmentCommActivity extends AppCompatActivity implements Fragment3.F3Listenter {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_comm_activity_layout);

		replaceFragment(new Fragment2());

		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Fragment静态加载时通信方式:
				//活动-->碎片：
				//	在活动中通过findFragmentById得到碎片的实例，然后通过实例调用碎片中的方法
				//碎片-->活动：
				//	在碎片中通过getActivity()可得到碎片所属活动的实例，然后通过实例调用活动中的方法
				Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
				fragment1.commMsg();

				//Fragment动态加载时通信方式:
				//活动-->碎片：
				//	创建Bundle用于装载数据，并将Bundle添加到要动态加载的Fragment上进行加载
				//	在碎片中需要接收数据的地方使用getArguments()来获取传递到碎片的数据
				//碎片-->活动：
				//	在碎片中创建一个内部接口并定义回调方法，然后在活动中需要实现这个接口而且还要实现其中定义的方法
				//	在碎片的onAttach方法中将参数Activity转化赋予内部接口的引用，即接口引用的实例化
				//	在碎片中需要的位置调用接口实例的方法，即可实现调用活动的方法
				Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
				if(currentFragment.getClass()==Fragment2.class){
					Bundle bundle = new Bundle();
					bundle.putString("content", "向碎片3发送数据！");	//添加向碎片3发送的数据内容
					Fragment3 fragment3 = new Fragment3();
					fragment3.setArguments(bundle);
					replaceFragment(fragment3);
				} else if(currentFragment.getClass()==Fragment3.class){
					replaceFragment(new Fragment2());
				}
			}
		});
	}

	@Override
	public void commMsg(Fragment fragment) {
		if(fragment.getClass()==Fragment1.class)
			Toast.makeText(this, "碎片1-->活动", Toast.LENGTH_SHORT).show();
		else if(fragment.getClass()==Fragment3.class)
			Toast.makeText(this, "碎片3-->活动", Toast.LENGTH_SHORT).show();
	}

	private void replaceFragment(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_layout, fragment);
		transaction.addToBackStack(null);//将事务添加到返回栈中，按下back键后会返回到前一个碎片（碎片会进入停止状态，如果没有这句，碎片进入销毁状态）
		transaction.commit();
	}
}
