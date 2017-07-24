package com.example.Widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.example.helloworld.R;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lester.ding on 6/22/2017.
 */

public class WidgetService extends Service {
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //定义时间格式类
                String time = sdf.format(System.currentTimeMillis());   //获取当前时间并且格式化
                //定义RemoteViews绑定widget布局文件，并指定widget更新的内容
                RemoteViews rv = new RemoteViews(getPackageName(), R.layout.widget_demo_layout);
                rv.setTextViewText(R.id.time_widget, time);
                //定义ComponentName绑定Provider
                ComponentName cn = new ComponentName(getApplicationContext(), WidgetProvider.class);
                //定义AppWidgetManager,通过ComponentName和RemoteViews绑定的信息更新widget
                AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
                manager.updateAppWidget(cn, rv);    //会触发Provider中onUpdate方法
            }
        }, 0, 1000);    //延时0， 1秒更新一次
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer = null;
    }
}
