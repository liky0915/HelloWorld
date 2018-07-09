package com.example.Drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

import java.util.Timer;

public class ClipDrawableDemo extends AppCompatActivity {

    private ImageView iv;
    private ClipDrawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clip_drawable_layout);

        iv = findViewById(R.id.iv);
        drawable = (ClipDrawable) iv.getDrawable();
        drawable.setLevel(3000);    //画面打开默认显示一半图片 level值从0-10000
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.show1:
                drawable.setLevel(8000);    //显示2/3
                break;
            case R.id.show2:
                drawable.setLevel(10000);   //完全显示
                break;
        }
    }
}
