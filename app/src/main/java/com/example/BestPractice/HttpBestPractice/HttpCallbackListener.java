package com.example.BestPractice.HttpBestPractice;

/**
 * Created by lester.ding on 9/8/2017.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
