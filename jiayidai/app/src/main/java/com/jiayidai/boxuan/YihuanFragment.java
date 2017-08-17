package com.jiayidai.boxuan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiayidai.boxuan.Adapter.Yihuan_adapter;


/**
 * Created by qiao on 2017/7/6.
 */

public class YihuanFragment extends Fragment {

    private Context mContext;
    private ListView listView;
    private Yihuan_adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yihuan_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.id_yihuan_list_view);
        adapter = new Yihuan_adapter(mContext);
        listView.setAdapter(adapter);
    }
}
