package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lester.ding on 9/7/2017.
 */

public class ParseJSONWithJSONObjectDemo extends AppCompatActivity {

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
                    String parsedText = parseJSONWithJSONObject(responseData);
                    //回调主线程将解析后的数据更新到UI上
                    showResponse(parsedText);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String parseJSONWithJSONObject(String responseData) {
        StringBuilder parsedText = new StringBuilder();
        try{
            JSONArray array = new JSONArray(responseData);
            for(int i=0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                String id = object.getString("id");
                String name = object.getString("name");
                String version = object.getString("version");
                parsedText.append("id is "+id+"; name is "+name+"; version is "+version+";\r\n");
            }
            return parsedText.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
