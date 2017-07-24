package com.example.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lester.ding on 6/22/2017.
 */

public class WidgetProvider extends AppWidgetProvider {
    @Override   //每接收一次广播消息就调用一次，根据intent.getAction()的意图会将消息派发给其他相对应的方法，使用较频繁，一般不重写
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override   //每次在Service内通过remoteViews和AppWidgetManager来更新widget时触发
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override   //每删除一个就调用一次
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override   //当该Widget第一次添加到桌面时调用该方法，可添加多次但只第一次调用
    public void onEnabled(Context context) {
        super.onEnabled(context);
        context.startService(new Intent(context, WidgetService.class)); //widget被添加到屏幕上时开启服务
    }

    @Override   //当最后一个该Widget删除时调用该方法，注意是最后一个
    public void onDisabled(Context context) {
        super.onDisabled(context);
        context.stopService(new Intent(context, WidgetService.class));  //widget被从屏幕上移除时终止服务
    }
}
