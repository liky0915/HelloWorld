package com.example.ContentProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 8/1/2017.
 * 权限级别分为危险和普通两种：
 *  普通权限不需要运行时授权，直接写入manifest即可，系统会自动授权
 *  危险权限必须要运行时授权，否则即使已写入manifest，6.0及以上系统也会报错
 */

public class RuntimePermission extends AppCompatActivity {

    private Button call_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runtime_permission_layout);
        //初始化
        call_btn = (Button) findViewById(R.id.call_btn);
        //点击拨打电话（弹出权限授权确认）
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断是否之前已经授权过此权限
                if(ContextCompat.checkSelfPermission(RuntimePermission.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                    //之前没有授权过则弹出运行时权限进行确认
                    ActivityCompat.requestPermissions(RuntimePermission.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                else
                    //之前已经授权过则直接拨打电话
                    call();
            }
        });
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL); //ACTION_CALL为系统内置拨打电话动作，需要授权危险级别权限CALL_PHONE, 而ACTION_DIAL动作只是打开拨号界面
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    //用户确认授权请求后会回调此方法来进行授权结果判定
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                //无论用户授权与否，选择结果都保存在grantResults中，判断后可决定后续操作
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    call();
                else
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
