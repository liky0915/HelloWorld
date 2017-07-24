package com.example.BestPractice.UIBestPractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class UIBestPractice extends AppCompatActivity {

    private EditText text;
    private Button send;
    private RecyclerView msgView;

    private List<Msg> msgList = new ArrayList<>();  //数据源数组
    private MsgAdapter adapter;                     //数据源适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bp_uibest_practice_layout);

        //初始化
        text = (EditText) findViewById(R.id.text);
        send = (Button) findViewById(R.id.send);
        msgView = (RecyclerView) findViewById(R.id.msg_view);

        //数据源初始化
        initMsgs();
        //为RecyclerView创建线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgView.setLayoutManager(layoutManager);
        //绑定数据源到适配器，并将适配器绑定到RecyclerView上显示数据
        adapter = new MsgAdapter(msgList);
        msgView.setAdapter(adapter);

        //发送按钮监听事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = text.getText().toString(); //获取输入的数据
                if(!content.equals("")){
                    Msg msg = new Msg(content, Msg.TYPE_SENT);  //实例化一个Msg类装载一条将要发送的数据
                    msgList.add(msg);                           //将实例化后的消息添加到消息集合中
                    adapter.notifyItemChanged(msgList.size()-1);//刷新适配器显示变更后的数据
                    msgView.scrollToPosition(msgList.size()-1); //滚动条滚动到新增消息处
                    text.setText("");                           //清空文本输入框
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("hi，你好!", Msg.TYPE_SENT);
        msgList.add(msg1);
        Msg msg2 = new Msg("你好!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", Msg.TYPE_RECEIVED);
        msgList.add(msg2);
        Msg msg3 = new Msg("你在干吗？", Msg.TYPE_SENT);
        msgList.add(msg3);
        Msg msg4 = new Msg("我在玩游戏！", Msg.TYPE_RECEIVED);
        msgList.add(msg4);
        Msg msg5 = new Msg("一起玩呗~", Msg.TYPE_SENT);
        msgList.add(msg5);
    }
}
