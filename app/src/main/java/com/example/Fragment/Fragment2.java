package com.example.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.R;

public class Fragment2 extends Fragment {

	private static final String TAG = "Fragment2";

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.i(TAG, TAG+"...onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, TAG+"...onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, TAG+"...onCreateView");
		return inflater.inflate(R.layout.fragment2_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, TAG+"...onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, TAG+"...onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, TAG+"...onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, TAG+"...onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, TAG+"...onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, TAG+"...onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, TAG+"...onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, TAG+"...onDetach");
	}
}
