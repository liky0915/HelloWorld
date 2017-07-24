package com.example.BestPractice.UIBestPractice;

/**
 * Created by lester.ding on 7/6/2017.
 */

public class Msg {
    //定义消息类型常量
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
