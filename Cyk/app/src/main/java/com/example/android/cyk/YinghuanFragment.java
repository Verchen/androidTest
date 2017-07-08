package com.example.android.cyk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.cyk.Adapter.Yinghuan_adapter;

/**
 * Created by qiao on 2017/7/6.
 */

public class YinghuanFragment extends Fragment {

    private ListView listView;
    private Yinghuan_adapter adapter;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yinghuan_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.id_yinghuan_list_view);
        adapter = new Yinghuan_adapter(mContext);
        listView.setAdapter(adapter);
    }
}
