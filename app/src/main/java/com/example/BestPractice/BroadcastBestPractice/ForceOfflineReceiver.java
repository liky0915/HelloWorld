package com.example.BestPractice.BroadcastBestPractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.helloworld.ActivityCollector;

/**
 * Created by lester.ding on 7/24/2017.
 */

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        //创建强制下线提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning");
        builder.setMessage("You are forced to be offline. Please try to login again.");
        builder.setCancelable(false);           //不允许用户按Back键关闭对话框！
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();  //关闭（销毁）所有已打开的Activity
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);  //跳转到登录画面
            }
        });
        builder.show(); //显示对话框
    }
}
