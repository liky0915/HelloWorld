package com.example.HttpConnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lester.ding on 9/5/2017.
 */

public class ParseXMLWithSAXDemo extends AppCompatActivity {

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
                    String parsedText = parseXMLWithSAX(responseData);
                    //回调主线程将解析后的数据更新到UI上
                    showResponse(parsedText);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String parseXMLWithSAX(String responseData) {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader reader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            //将ContentHandler的实例设置到Reader中，ContentHandler其实就是规定Reader如何解析XML
            reader.setContentHandler(handler);
            //传入服务器返回的XML数据并开始执行解析
            reader.parse(new InputSource(new StringReader(responseData)));
            //返回解析后的数据(此数据存在Handler的parsedText属性中）
            return handler.parsedText.toString();
        }catch(Exception e){
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
