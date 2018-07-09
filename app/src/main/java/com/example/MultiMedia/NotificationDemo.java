package com.example.MultiMedia;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.HelloWorld;
import com.example.helloworld.R;

import java.io.File;

/**
 * Created by lester.ding on 8/11/2017.
 */

public class NotificationDemo extends AppCompatActivity {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo_layout);

        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建NotificationManager用于管理所有的通知对象，包括发送，删除等等
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //创建PendingIntent对象，以实现点击通知后跳转操作（PendingIntent对象需要一个明确的Intent对象作为其参数）
                Intent intent = new Intent(NotificationDemo.this, HelloWorld.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationDemo.this, 0, intent, 0);
                //创建 一个通知对象，NotificationCompat是版本兼容的通知类
                Notification notification = new NotificationCompat.Builder(NotificationDemo.this)
                        //通知抬头
                        .setContentTitle("新通知")
                        //通知正文
                        .setContentText("您有一条新通知，请尽快查看！")
                        //通知时间
                        .setWhen(System.currentTimeMillis())
                        //状态栏小图标
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        //下拉状态栏后显示的大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        //点击通知后跳转的意图
                        .setContentIntent(pi)
                        //点击通知后状态栏自动取消掉该通知
                        .setAutoCancel(true)
                        //当通知到来时的提示音
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        //当通知到来时手机震动（下标0表示静止时长，下标1表示震动时长（毫秒），下标2又表示静止时长，以此类推）
                        .setVibrate(new long[]{0,1000,1000,1000})
                        //当通知到来时LED灯显示（参数2为灯亮起时长（毫秒），参数3为暗去时长）
                        .setLights(Color.BLUE, 1000, 1000)
                        //当不想那么繁琐的设置通知效果时可以使用默认设置，默认设置会根据当前手机的环境来决定播放的铃声，以及如何震动
                        //.setDefaults(NotificationCompat.DEFAULT_ALL)
                        //下拉显示一张图片，也可以显示长文字（BigTextStyle()）
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pic1)))
                        //通知重要程度，MAX为最高级，立即在屏幕上方显示通知内容
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        //实例化通知对象
                        .build();
                //通知管理器发送通知对象，每个发送出去的通知的Id都是需要不同的
                manager.notify(1, notification);
            }
        });
    }
}
