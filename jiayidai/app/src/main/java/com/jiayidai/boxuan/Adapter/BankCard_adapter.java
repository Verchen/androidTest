package com.jiayidai.boxuan.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jiayidai.boxuan.R;

/**
 * Created by qiao on 2017/7/12.
 */

public class BankCard_adapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;

    public BankCard_adapter(Context context){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
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
        view = inflater.inflate(R.layout.bankcard_item_layout, viewGroup, false);
        return view;
    }
}
