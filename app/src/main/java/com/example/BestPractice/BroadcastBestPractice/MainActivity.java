package com.example.BestPractice.BroadcastBestPractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.BaseActivity;
import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/24/2017.
 */

public class MainActivity extends BaseActivity {

    private Button offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bp_broadcast_main_layout);

        offline = (Button) findViewById(R.id.force_offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一条强制下线的广播
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
