package com.example.BestPractice.FragmentBestPractice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/14/2017.
 */

public class NewsTitleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bp_news_title_fragment_layout, container, false);
        return view;
    }
}
