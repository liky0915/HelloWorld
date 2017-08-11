package com.example.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by lester.ding on 8/10/2017.
 */

public class BaseContentProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);   //实例化UriMatcher
        uriMatcher.addURI("com.example.helloworld.provider", "book", BOOK_DIR);
        uriMatcher.addURI("com.example.helloworld.provider", "book/#", BOOK_ITEM);
        uriMatcher.addURI("com.example.helloworld.provider", "category", CATEGORY_DIR);
        uriMatcher.addURI("com.example.helloworld.provider", "category/#", CATEGORY_ITEM);
    }

    //初始化，完成对数据库的创建和升级，返回true表示初始化成功，false表示失败，只有当ContentResolver访问数据时才会被初始化
    @Override
    public boolean onCreate() {
        return false;
    }

    //从CP中查询数据
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                break;
            case BOOK_ITEM:
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return null;
    }

    //向CP中添加一条数据
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                break;
            case BOOK_ITEM:
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return null;
    }

    //从CP中删除数据
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                break;
            case BOOK_ITEM:
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return 0;
    }

    //更新CP中已有的数据
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                break;
            case BOOK_ITEM:
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return 0;
    }

    //根据传入的内容URI来返回相应的MIME类型
    @Override
    public String getType(Uri uri) {
        //一个内容URI所对应的MIME字符串由三部分组成
        //1. 必须以vnd开头
        //2. 如果以内容URI以路径结尾，则后接android.cursor.dir/, 如果内容URI以id结尾，则后接android.cursor.item/
        //3. 最后接上vnd.<authority>.<path>
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.helloworld.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.helloworld.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.helloworld.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.helloworld.provider.category";
            default:
                break;
        }
        return null;
    }
}
