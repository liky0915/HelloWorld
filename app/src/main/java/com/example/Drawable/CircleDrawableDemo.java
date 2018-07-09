package com.example.Drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.helloworld.R;

public class CircleDrawableDemo extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_drawable_layout);

        iv = findViewById(R.id.iv);
        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
        CircleDrawable newpic = new CircleDrawable(pic);    //由于CircleDrawable的构造函数入参类型为Bitmap，所以这里创建图片时，类型要用Bitmap
        iv.setImageDrawable(newpic);    //由于CircleDrawable是继承Drawable类的，所以这里要使用setImageDrawable方法
    }
}
