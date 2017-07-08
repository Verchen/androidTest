package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.cyk.R;

/**
 * Created by qiao on 2017/7/6.
 */

public class Yinghuan_adapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;

    public Yinghuan_adapter(Context context){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int i) {
        return "";
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0){
            view = inflater.inflate(R.layout.zhangdan_jine_item_layout, viewGroup, false);
            return view;
        }else {
            view = inflater.inflate(R.layout.zd_lijihuankuan_item_layout, viewGroup, false);
            return view;
        }
    }
}
