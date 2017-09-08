package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Bean.AppBean;
import com.example.helloworld.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lester.ding on 9/8/2017.
 */

public class ParseJSONWithGSONDemo extends AppCompatActivity {
    private Button sendRequest;
    private TextView responseText;
    private String urlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_url_connection_layout);

        //初始化
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        //读取配置文件内容来获取本地服务器地址
        urlAddress = getString(R.string.url_httpconnection_json);
        //点击按钮发送请求
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //创建客户端（请求者）对象
                    OkHttpClient client = new OkHttpClient();
                    //创建请求对象并添加请求内容（连缀方式）
                    Request request = new Request.Builder()
                            .url(urlAddress)
                            .build();
                    //客户端发起请求并接受返回结果
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //解析服务器返回的数据
                    String parsedText = parseJSONWithGSON(responseData);
                    //回调主线程将解析后的数据更新到UI上
                    showResponse(parsedText);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String parseJSONWithGSON(String responseData) {
        StringBuilder parsedText = new StringBuilder();
        Gson gson = new Gson();
        List<AppBean> appList = gson.fromJson(responseData, new TypeToken<List<AppBean>>(){}.getType());
        for(AppBean app : appList)
            parsedText.append("id is "+app.getId()+"; name is "+app.getName()+"; version is "+app.getVersion()+";\r\n");
        return parsedText.toString();
    }

    private void showResponse(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(s);
            }
        });
    }
}
