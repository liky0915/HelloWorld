package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.helloworld.R;

public class FragmentDemo extends AppCompatActivity {

	private RadioGroup rg;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_demo);
		
		rg=(RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.static_load:
					intent = new Intent(FragmentDemo.this, FragmentStaticLoad.class);
					startActivity(intent);
					break;
				case R.id.dynamic_load:
					intent = new Intent(FragmentDemo.this, FragmentDynamicLoad.class);
					startActivity(intent);
					break;
				case R.id.lifecycle:
					intent = new Intent(FragmentDemo.this, FragmentLifeCycle.class);
					startActivity(intent);
					break;
				case R.id.comm:
					intent = new Intent(FragmentDemo.this, FragmentCommActivity.class);
					startActivity(intent);
					break;
				}
				
			}
		});
		
	}
}
