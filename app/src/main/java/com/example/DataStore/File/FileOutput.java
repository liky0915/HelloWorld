package com.example.DataStore.File;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.helloworld.R;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by lester.ding on 7/24/2017.
 */

public class FileOutput extends AppCompatActivity {

    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_output_layout);
        //初始化
        content = (EditText) findViewById(R.id.content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData(content.getText().toString()); //在画面销毁时保存画面上的数据到文件
    }

    private void saveData(String s) {
        FileOutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter = null;
        try {
            //调用openFileOutput打开要导出的文件，返回一个FileOutputStream输出流
            outputStream = openFileOutput("content", Context.MODE_PRIVATE);
            //将输出流封装成一个输出流写入类
            outputStreamWriter = new OutputStreamWriter(outputStream);
            //优化封装输出流写入类为一个缓冲写入类（提高性能）
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            //写入数据
            bufferedWriter.write(s);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
