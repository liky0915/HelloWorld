package com.example.DataStore.File;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by lester.ding on 7/24/2017.
 */

public class FileInput extends AppCompatActivity {

    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_input_layout);
        //初始化
        content = (EditText) findViewById(R.id.content);
        //从内存文件读取数据并添加到控件上
        String data = loadData();
        if(!TextUtils.isEmpty(data)) {  //推荐：使用TextUtils.isEmpty()可一次性判断字符串=null或者空串的两种情况
            content.setText(loadData());
            content.setSelection(data.length());    //将光标移动到文本末尾，以便继续输入
        }
    }

    private String loadData() {
        FileInputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //调用openFileInput打开要导入的文件，返回一个FileInputStream输入流
            inputStream = openFileInput("content");
            //将输入流封装成一个输入流读取类
            inputStreamReader = new InputStreamReader(inputStream);
            //优化封装输入流读取类为一个缓冲读取类（提高性能）
            bufferedReader = new BufferedReader(inputStreamReader);
            //读取数据
            String line;
            while ( (line=bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
