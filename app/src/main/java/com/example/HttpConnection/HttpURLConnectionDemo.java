package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lester.ding on 9/1/2017.
 */

public class HttpURLConnectionDemo extends AppCompatActivity {

    private Button sendRequest;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_url_connection_layout);

        //初始化
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        //开启子线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                BufferedReader reader = null;

                try{
                    URL url = new URL("https://www.baidu.com");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");   //GET表示从服务器获取数据，POST表示提交数据到服务器
                    /*
                    如果要提交数据给服务器则需添加下面三行
                    conn.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes("username=admin&password=123");
                    数据要以键值对的形式存在，且数据与数据之间用"&"符号隔开
                    */
                    conn.setConnectTimeout(8000);   //连接超时
                    conn.setReadTimeout(8000);      //读取超时
                    InputStream in = conn.getInputStream(); //获取服务器返回的输入流
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());  //回归主线程更新UI
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(conn!=null)
                        conn.disconnect();
                }
            }
        }).start();     //start启动子线程
    }

    private void showResponse(final String s) {
        //由于Android不允许在子线程中进行UI操作，所以需通过runOnUiThread切换到主线程中更新UI元素
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(s);
            }
        });
    }
}
