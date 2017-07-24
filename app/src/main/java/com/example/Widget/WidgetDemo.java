package com.example.Widget;

import android.app.Activity;
import android.os.Bundle;

import com.example.helloworld.R;

public class WidgetDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_demo_layout);

        //此Activity在本例中并无用处，可忽略。。。

        //借此在这里简述添加appwidget的基本步骤：
        //1. 首先为appwidget新建布局文件，这个不多说，参考@layout/widget_demo_layout即可
        //2. 然后新建appwidget配置文件：
        //      位置：在res文件夹下面新建文件夹xml(如已经存在则不需新建)，将创建的配置文件保存在这里
        //      参数：参考@xml/widgetconfig
        //3. 其次新建一个WidgetProvider继承自AppWidgetProvider
        //      1. 在WidgetProvider中需要重写几个方法，这几个方法都是对widget在屏幕上所做操作产生的相对应的驱动事件
        //      2. widget有自动更新功能，更新时间是在上面的配置文件中设定的，更新时会自动调用onUpdate方法
		//		3. 当广播到来以后，AppWidgetProvider会自动根据广播的action通过onReceive方法来自动派发广播，也就是适时的调用其他几个方法
        //      4. Service可在WidgetProvider中被调用，具体参考WidgetProvider
        //4. 接着新建一个WidgetService继承Service(可选)
        //      1. 可选的意思是指一个最简单的widget可不用开启服务，但如果要对显示内容做修改最好启用服务
        //      2. 在服务中可创建RemoteViews和AppWidgetManager来更新widget, 可参考WidgetService
        //      3. 服务一般在widget第一次被添加到屏幕上时开启，然后直到最后一个该widget被从屏幕上移除时才停止
        //5. 最后是在系统配置文件Manifest中注册appwidget和Service(很重要)
        //      1.  <intent-filter>
        //              <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
        //          </intent-filter>
        //      2.  <meta-data android:name="android.appwidget.provider" android:resource="@xml/widgetconfig" />
        //      这2句中的name属性值是固定写法，比较容易写错，记住就行

	}
}
