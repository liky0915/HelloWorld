package com.example.BestPractice.HttpBestPractice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lester.ding on 9/8/2017.
 */

public class HttpUtil {

    //使用JAVA的回调机制将服务器响应的数据返回到主线程（需要额外定义一个接口来实现, 不推荐）
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try{
                    //与服务器建立连接
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    //获取服务器返回数据
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    //回调onFinish方法
                    if(listener!=null)
                        listener.onFinish(response.toString());

                }catch(Exception e){
                    //回调onError方法
                    if(listener!=null)
                        listener.onError(e);
                }finally {
                    if(conn!=null)
                        conn.disconnect();
                }
            }
        }).start();
    }

    //使用OkHttp封装好的回调机制将服务器响应的数据返回到主线程（推荐使用）
    public static void sendOkHttpRequest(String address, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
