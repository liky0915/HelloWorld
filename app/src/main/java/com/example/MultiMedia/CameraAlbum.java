package com.example.MultiMedia;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lester.ding on 8/14/2017.
 */

public class CameraAlbum extends AppCompatActivity {

    private static final int TAKE_PHOTO=1, CHOOSE_PHOTO=2;
    private ImageView picture;
    private Uri imageUri;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_album_layout);
        //初始化
        picture = (ImageView) findViewById(R.id.picture);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        Button chooseAlbum = (Button) findViewById(R.id.choose_album);

        //调用摄像头拍照选取图片
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建图片文件对象（并非物理创建），如果已经存在相同文件名则删除
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                if(outputImage.exists())
                    outputImage.delete();
                //物理创建图片文件
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //判断运行设备的系统版本，将图片文件路径转换成Uri对象
                //  如果低于Android7.0，就调用Uri的fromFile方法将图片文件的本地真实路径转换成一个Uri对象
                //  如果等于或高于Android7.0， 调用FileProvider的getUriForFile方法将File对象转换成一个封装过的Uri对象
                //之所以要区分系统版本是因为从7.0开始，直接使用本地真是路径的Uri被认为是不安全的，会抛出一个FileUriExposedException异常
                //而FileProvider是一种特殊的内容提供器，可以选择性的将封装过的Uri共享给外部，从而提高安全性
                if(Build.VERSION.SDK_INT>=24)
                    imageUri = FileProvider.getUriForFile(CameraAlbum.this, "com.example.helloworld.fileprovider", outputImage);
                else
                    imageUri = Uri.fromFile(outputImage);
                //构建一个Intent对象，指定action用于调用系统摄像头，putExtra中传入上一步得到的Uri来指定图片的输出地址
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        //直接从相册选取图片
        chooseAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SD卡读取权限检查，没有授权的情况下需对用户进行运行时授权询问
                if(ContextCompat.checkSelfPermission(CameraAlbum.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        !=PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(CameraAlbum.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                else
                    openAlbum();    //已经有权限则调用系统相册
            }
        });
    }

    //运行时权限选择结果判断
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    openAlbum();    //授权通过调用系统相册
                else
                    Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
        }
    }

    //打开系统相册
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    //调用系统资源（拍摄/相册）后结果判断处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:    //拍摄
                if(resultCode==RESULT_OK){
                    try {
                        //利用BitmapFactory.decodeStream将output_image.jpg这张照片解析成Bitmap对象，然后显示到画面上
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:  //相册
                if(resultCode==RESULT_OK){
                    //之所以要区分版本是因为从4.4开始，选取相册中的图片不再返回图片的真实Uri了，这也是为了提高安全性考虑
                    if(Build.VERSION.SDK_INT>=19)
                        //4.4及以上系统使用此方法处理图片
                        handleImageOnKitKat(data);
                    else
                        //4.4以下系统使用此方法处理图片
                        handleImageBeforeKitKat(data);
                }
                break;
        }
    }

    //4.4及以上系统使用此方法处理在相册中选取的图片
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri = data.getData();   //返回从相册选取的图片经过封装后的Uri，需要对其进行解析才能得到真实的Uri
        if(DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];    //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme()))
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        else if("file".equalsIgnoreCase(uri.getScheme()))
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        //显示图片
        displayImage(imagePath);
    }

    //4.4以下系统使用此方法处理在相册中选取的图片
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();   //返回从相册选取的图片真实Uri
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    //通过Uri和selection来获取真实的图片路径
    private String getImagePath(Uri uri, String selection) {
        String path=null;
        Cursor c = getContentResolver().query(uri, null, selection, null, null);
        if(c!=null){
            while(c.moveToNext()){
                path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            c.close();
        }
        return path;
    }

    //根据传入的图片路径参数将图片转换成Bitmap形式显示在屏幕上
    private void displayImage(String imagePath) {
        if(imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else
            Toast.makeText(this, "Failed to get image.", Toast.LENGTH_SHORT).show();
    }
}
