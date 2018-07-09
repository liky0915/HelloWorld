package com.example.Drawable;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

public class TransitionDrawableDemo extends AppCompatActivity {

    private ImageView iv;
    private TransitionDrawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_drawable_layout);

        iv = findViewById(R.id.iv);
        drawable = (TransitionDrawable) iv.getDrawable();
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.on:
                drawable.startTransition(3000); //点亮状态, 3秒后完全点亮
                break;
            case R.id.off:
                drawable.reverseTransition(3000);   //熄灭状态, 3秒后完全熄灭
                break;
        }
    }
}
