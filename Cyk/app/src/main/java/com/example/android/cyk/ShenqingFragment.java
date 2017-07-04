package com.example.android.cyk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.cyk.Adapter.Jiekuan_list_adapter;

/**
 * Created by qiao on 2017/7/3.
 */

public class ShenqingFragment extends Fragment {

    Context context;

    private String[] dataSource = new String[]{
            "ListView控件演示",
            "ProgressBar控件演示",
            "GridView控件演示",
            "ScrollView控件演示",
            "ListView控件演示",
            "ProgressBar控件演示",
            "GridView控件演示",
            "ScrollView控件演示",
            "ListView控件演示",
            "ProgressBar控件演示",
            "GridView控件演示",
            "ScrollView控件演示",
            "ListView控件演示",
            "ProgressBar控件演示",
            "GridView控件演示",
            "ScrollView控件演示",
            "DatePicker控件演示"
    };

    private ListView listView;
    private Jiekuan_list_adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shenqing_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.shenqing_list_view);

        adapter = new Jiekuan_list_adapter(dataSource, context);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

