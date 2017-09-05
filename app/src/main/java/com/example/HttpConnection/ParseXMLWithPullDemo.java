package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lester.ding on 9/5/2017.
 */

public class ParseXMLWithPullDemo extends AppCompatActivity {

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
                            .url("http://192.168.70.50:8080/MyAJS/xml/get_data.xml")
                            .build();
                    //客户端发起请求并接受返回结果
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //解析服务器返回的数据
                    String parsedText = parseXMLWithPull(responseData);
                    //回调主线程将解析后的数据更新到UI上
                    showResponse(parsedText);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Pull方式解析服务器返回数据
    private String parseXMLWithPull(String responseData) {
        try {
            //创建XML解析器
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(responseData));
            //开始解析
            String id="", name="", version="";
            StringBuilder parsedText = new StringBuilder();

            int eventType = parser.getEventType();              //获取首标签类型
            while(eventType != XmlPullParser.END_DOCUMENT){     //当首标签类型不是文件末标签时开始循环
                String tagName = parser.getName();              //循环获取每一层标签名
                switch (eventType){
                    //开始解析某个标签
                    case XmlPullParser.START_TAG:
                        if("id".equals(tagName)){
                            id = parser.nextText();
                        }else if ("name".equals(tagName)){
                            name = parser.nextText();
                        }else if ("version".equals(tagName)){
                            version = parser.nextText();
                        }
                        break;
                    //完成解析某个标签
                    case XmlPullParser.END_TAG:
                        if("app".equals(tagName))
                            parsedText.append("id is "+id+"; name is "+name+"; version is "+version+";\r\n");
                        break;
                }
                eventType = parser.next();  //手动将当前标签移动至下个标签，以便循环继续
            }
            return parsedText.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
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
