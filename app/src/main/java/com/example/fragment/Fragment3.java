package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helloworld.R;

public class Fragment3 extends Fragment {

	//内部接口
	public interface F3Listenter{
		void commMsg(Fragment fragment);
	}
	public F3Listenter myListenter;

	@Override
	public void onAttach(Context context) {
		myListenter = (F3Listenter) context;
		super.onAttach(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		String text = getArguments().getString("content");
		Toast.makeText(getActivity(), "活动-->碎片3，通信数据：" + text, Toast.LENGTH_SHORT).show();
		myListenter.commMsg(this);

		return inflater.inflate(R.layout.fragment3_layout, container, false);
	}
}
