package com.example.ContentProvider;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
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

import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;


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
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 3);
                else
                    updateContacts();
                break;
            case R.id.delete_contacts:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 4);
                else
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
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    addContacts();
                else
                    Toast.makeText(this, "You denied the permission, failed to add contacts.", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                //更新联系人
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    updateContacts();
                else
                    Toast.makeText(this, "You denied the permission, failed to update contacts.", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                //删除联系人
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    deleteContacts();
                else
                    Toast.makeText(this, "You denied the permission, failed to delete contacts.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void readContacts() {
        //查询联系人，按名字排序
        list.clear();   //每次读取联系人之前先清空集合
        Cursor c = getContentResolver().query(Phone.CONTENT_URI, null, null, null, Phone.DISPLAY_NAME);
        if (c != null) {
            while(c.moveToNext()){
                String name = c.getString(c.getColumnIndex(Phone.DISPLAY_NAME));
                String number = c.getString(c.getColumnIndex(Phone.NUMBER));
                ContactBean contact = new ContactBean(R.mipmap.ic_launcher, name, number);
                list.add(contact);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
        c.close();
    }

    private void addContacts() {

        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();

        //向RawContacts表插入新增数据并获取_id属性
        long raw_contact_id = ContentUris.parseId(resolver.insert(RawContacts.CONTENT_URI, values));

        //Data表添加联系人名字
        values.clear();
        values.put(StructuredName.RAW_CONTACT_ID, raw_contact_id);
        values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        values.put(StructuredName.DISPLAY_NAME, "Luffy");
        resolver.insert(Data.CONTENT_URI, values);
        //Data表添加联系人电话
        values.clear();
        values.put(Phone.RAW_CONTACT_ID, raw_contact_id);
        values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, "13817726723");
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        resolver.insert(Data.CONTENT_URI, values);

        /*--------------------------------批量添加数据------------------------------------------------
        //定义批量处理操作集合
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        //向raw_contact表添加一条记录
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)//此处.withValue("account_name", null)一定要加，不然会抛NullPointerException
                .build();
        ops.add(op1);

        //向data添加数据
        uri = Uri.parse("content://com.android.contacts/data");
        //添加姓名
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference(StructuredName.RAW_CONTACT_ID, 0)//withValueBackReference的第二个参数表示引用ops[0]操作的返回id作为此值
                .withValue(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, "Luffy")
                .build();
        ops.add(op2);
        //添加手机数据
        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri).
                withValueBackReference(Phone.RAW_CONTACT_ID, 0)
                .withValue(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.TYPE, Phone.TYPE_MOBILE)
                .withValue(Phone.NUMBER, "13817726723")
                .build();
        ops.add(op3);

        //批量提交
        try {
            resolver.applyBatch("com.android.contacts", ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        */

    }

    private void updateContacts() {
        String name = "Luffy";
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();

        //在RawContacts中根据姓名求_ID
        Cursor c = resolver.query(RawContacts.CONTENT_URI, new String[]{RawContacts._ID}, RawContacts.DISPLAY_NAME_PRIMARY+"=?", new String[]{name}, null);
        if(c.moveToFirst()) {
            int id = c.getInt(0);
            //根据 _ID=RAW_CONTACT_ID 和 MIMETYPE=StructuredName.CONTENT_ITEM_TYPE 更新Data中的相应数据
            values.put(StructuredName.DISPLAY_NAME, "Lucy");
            resolver.update(Data.CONTENT_URI, values, Data.RAW_CONTACT_ID + "=? and " + Data.MIMETYPE + "=?", new String[]{id+"", StructuredName.CONTENT_ITEM_TYPE});
        }
    }

    private void deleteContacts() {
        String name = "Lucy";
        ContentResolver resolver = getContentResolver();

        //在RawContacts中根据姓名求_ID
        Cursor c = resolver.query(RawContacts.CONTENT_URI, new String[]{RawContacts._ID}, RawContacts.DISPLAY_NAME_PRIMARY+"=?", new String[]{name}, null);
        if(c.moveToFirst()){
            int id = c.getInt(0);
            //根据 _ID=RAW_CONTACT_ID删除Data中的相应数据
            resolver.delete(RawContacts.CONTENT_URI, RawContacts.DISPLAY_NAME_PRIMARY+"=?", new String[]{name});
            resolver.delete(Data.CONTENT_URI, Data.RAW_CONTACT_ID+"=?", new String[]{id+""});
        }
        c.close();
    }
}
