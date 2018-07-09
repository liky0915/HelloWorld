package com.example.Drawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

public class LevelListDrawableDemo extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levellist_drawable_layout);

        iv = findViewById(R.id.iv);
        iv.setImageLevel(3);    //画面打开默认设置为熄灭状态
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.on:
                iv.setImageLevel(8);    //点亮状态
                break;
            case R.id.off:
                iv.setImageLevel(3);    //熄灭状态
                break;
        }
    }
}
