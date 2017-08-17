package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 8/17/2017.
 * WebView是一个嵌入式的简易浏览器，在不允许使用系统浏览器的前提下，使用WebView控件可以在程序中简单显示网页
 */

public class WebViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_demo);
        //初始化
        WebView webView = (WebView) findViewById(R.id.web_view);
        //调用getSettings方法可以设置一些浏览器属性，这里只设置WebView支持JavaScript脚本即可
        webView.getSettings().setJavaScriptEnabled(true);
        //当浏览器开启或网页跳转时，首页或目标网页仍然在当前WebView中显示，而非打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //加载网址
        webView.loadUrl("http://www.baidu.com");
    }
}
