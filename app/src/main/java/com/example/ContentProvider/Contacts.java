package com.example.ContentProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.Bean.ContactBean;
import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
import static android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER;
import static android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;


/**
 * Created by lester.ding on 8/2/2017.
 */

public class Contacts extends AppCompatActivity {

    private RecyclerView rv;
    private List<ContactBean> list =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);
        //初始化
        rv = (RecyclerView) findViewById(R.id.contacts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        ContactsAdapter adapter = new ContactsAdapter(list);
        rv.setAdapter(adapter);
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.read_contacts:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                else
                    readContacts();
                break;
            case R.id.add_contacts:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 2);
                else
                    addContacts();
                break;
            case R.id.update_contacts:
                updateContacts();
                break;
            case R.id.delete_contacts:
                deleteContacts();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                //读取联系人
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    readContacts();
                else
                    Toast.makeText(this, "You denied the permission, failed to read contacts.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                //添加联系人
                break;
            case 3:
                //更新联系人
                break;
            case 4:
                //删除联系人
                break;
        }
    }

    private void readContacts() {
        //查询联系人，按名字排序
        Cursor c = getContentResolver().query(CONTENT_URI, null, null, null, DISPLAY_NAME);
        if (c != null) {
            while(c.moveToNext()){
                String name = c.getString(c.getColumnIndex(DISPLAY_NAME));
                String number = c.getString(c.getColumnIndex(NUMBER));
                ContactBean contact = new ContactBean(R.mipmap.ic_launcher, name, number);
                list.add(contact);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
        c.close();
    }

    private void addContacts() {
    }

    private void updateContacts() {
    }

    private void deleteContacts() {
    }
}
