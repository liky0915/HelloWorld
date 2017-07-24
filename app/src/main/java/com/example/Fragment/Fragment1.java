package com.example.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helloworld.R;

public class Fragment1 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1_layout, container, false);
	}

	public void commMsg(){
		Toast.makeText(getActivity(), "活动-->碎片1", Toast.LENGTH_SHORT).show();
		//通过getActivity得到此碎片所关联的活动的实例，进而判断活动类名，并通过实例调用关联活动中的方法
		if(getActivity().getClass()==FragmentCommActivity.class){
			FragmentCommActivity activity = (FragmentCommActivity) getActivity();
			activity.commMsg(this);
		}
	}
}
