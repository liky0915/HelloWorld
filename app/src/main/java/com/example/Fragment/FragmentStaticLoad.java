package com.example.Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.helloworld.R;

public class FragmentStaticLoad extends AppCompatActivity {

	private TextView text;
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_static_load_layout);
		
		//Fragment布局中的控件在Activity类中可以共享，
		//在这里执行的程序会在Fragment类中的程序之后执行，即可能覆盖Fragment类中的程序(具体视程序内容而定)
		btn = (Button) findViewById(R.id.btn);
		text = (TextView) findViewById(R.id.text);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String txt = text.getText().toString();
				text.setText(txt+"被点击了666！");
			}
		});
	}
}
