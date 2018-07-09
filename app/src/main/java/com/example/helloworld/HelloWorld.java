package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWorld extends AppCompatActivity {

    private Button btn1;
    private TextView tv;
    private AutoCompleteTextView acTextView;
    private MultiAutoCompleteTextView macTextView;
    private String[] res = {"beijing1","beijing2","beijing3","shanghai1","shanghai2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        //初始化
        tv = (TextView) findViewById(R.id.textView);
        acTextView = findViewById(R.id.autoCompleteTextView);
        macTextView = findViewById(R.id.multiAutoCompleteTextView);

        //隐藏原始标题栏(下面定义的菜单也不会显示了）
        ActionBar ab = getSupportActionBar();
        if(ab!=null)
            ab.hide();

        //数据还原（存在的话）
        if(savedInstanceState!=null) {
            //当savedInstanceState不为空时说明之前Activity已被系统资源回收掉了，现在重新创建时需要还原原来留在画面上的临时数据
            String data = savedInstanceState.getString("data");
            tv.setText(data);
        }

        btn1 = (Button) findViewById(R.id.intent);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式Intent，给出上下文Context和要启动的Activity
                //Intent intent = new Intent(HelloWorld.this, SecondActivity.class);
                //startActivity(intent);

                //推荐！规范形式启动：在SecondActivity中定义actionStart静态方法，可方便管理传递参数，并且使调用启动更加方便
                SecondActivity.actionStart(HelloWorld.this, "Hello SecondActivity!");

                //隐式Intent，给出想要启动的意图关键字，让系统去Manifest里去找注册时Action满足意图关键字的Activity，找到后启动
                //Intent intent2 = new Intent("START");
                /*这里在添加category时需要注意一点:
                    调用startActivity时系统会自动将"android.intent.category.DEFAULT"加入到Intent2中去，
                    也就是默认加上intent2.addCategory("android.intent.category.DEFAULT");（Action只有一个，category可有多个）
                    所以在Manifest中注册Activity时除了自定义的category之外，默认的category也是必须加上的，
                    并且只有当注册的Action和Category能同时匹配intent的Action和category时，注册的Activity才会响应该intent */
                //intent2.addCategory("SEC_ACT");
                //setData方法的参数只能是Uri对象，这里是将一个字符串解析成Uri对象作为参数，在注册时由<data>标签规定数据的协议部分为http，
                //所以字符串一定要以http开头不能乱写，否则不会响应此intent
                //intent2.setData(Uri.parse("http://www.baidu.com"));
                //除了setData方法外更为常用的传参方法是使用putExtra， setData更多的是用于调用系统资源时，例如下面的intent3,intent4
                //intent2.putExtra("data", "Hello SecondActivity!");
                //带返回结果的启动，需要被启动的Activity中返回结果参数给本Activity，1为请求码，用于区分在多个带返回启动时的本启动的唯一性
                //startActivityForResult(intent2, 1);

                //隐式Intent方式调用系统其它程序
                //调用系统浏览器
                //Intent intent3 = new Intent(Intent.ACTION_VIEW);//ACTION_VIEW是系统内置的Action，其常量值为"android.intent.action.VIEW"
                //intent3.setData(Uri.parse("http://www.baidu.com"));
                //startActivity(intent3);
                //调用系统拨号界面
                //Intent intent4 = new Intent(Intent.ACTION_DIAL);//ACTION_VIEW是系统内置的Action，其常量值为"android.intent.action.DIAL"
                //intent4.setData(Uri.parse("tel:10086"));
                //startActivity(intent4);
                //还有很多其它的系统Action，比如geo显示地理位置等等...不一一举例
            }
        });

        //创建输入框自动填充所需的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        acTextView.setAdapter(adapter);
        macTextView.setAdapter(adapter);
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());   //多个关键词之间以逗号作为分隔符
    }

    @Override   //每个在本Activity中启动的带有返回结果的其他Activity都会在销毁时回调本Activity的此方法用于接收返回参数，requestCode用以区分每个从本活动启动的请求的唯一性
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK)
                    tv.setText("SecondActivity said to me: " + data.getStringExtra("data_return"));
                break;
        }
    }

    @Override   //此方法的作用是当Activity在停止状态时被系统自动资源回收前，把重要的数据进行保存，以便下次被用户重新创建时(onCreate)能够再次使用这些数据
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", "saved data before activity has been recycled");//Bundle对象可保存各类型的数据
    }

    @Override   //添加Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);//传入参数menu其实是将布局文件上的menu对象传入，而我们要将自己定义的menu添加到menu对象上去
        return true;    //返回True表示允许创建的菜单显示出来，False则不显示
    }

    @Override   //给添加的Menu设置点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "Add Item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "Remove Item", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
