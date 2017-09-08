package com.example.BestPractice.HttpBestPractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Bean.AppBean;
import com.example.helloworld.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lester.ding on 9/8/2017.
 */

public class HttpBestPractice extends AppCompatActivity {

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
                sendRequest();
            }
        });
    }

    //使用HttpUtil中的2种静态方法发送网络请求
    private void sendRequest() {
        responseText.setText("");   //每次网络请求前先清空画面
        //使用HttpURLConnection发送请求
        HttpUtil.sendHttpRequest(urlAddress, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                //解析返回数据并显示, 由于这里还是处于子线程中运行, UI更新仍需要回归主线程才可
                showResponse(parseJSONWithGSON(response));
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

        //使用OkHttp发送请求
        HttpUtil.sendOkHttpRequest(urlAddress, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析返回数据并显示, 由于这里还是处于子线程中运行, UI更新仍需要回归主线程才可
                showResponse(parseJSONWithGSON(response.body().string()));
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
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
                responseText.append(s);
            }
        });
    }
}
