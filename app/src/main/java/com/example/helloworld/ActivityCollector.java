package com.example.helloworld;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lester.ding on 7/3/2017.
 */

public class ActivityCollector {
    //声明Activity集合，用于管理Activity
    public static List<Activity> activities = new ArrayList<>();
    //添加Activity
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    //移除Activity
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //销毁全部Activity，并清空集合
    public static void finishAll(){
        for (Activity activity : activities){
            if(!activity.isFinishing())
                activity.finish();
        }
        activities.clear();
    }
}
