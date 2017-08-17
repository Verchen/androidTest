package com.jiayidai.boxuan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiayidai.boxuan.R;


/**
 * Created by qiao on 2017/7/10.
 */

public class Setting_adapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;

    public Setting_adapter(Context Context){
        this.mContext = Context;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 4;
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
        view = inflater.inflate(R.layout.setting_item_layout, viewGroup, false);
        TextView textView = view.findViewById(R.id.id_setting_item_tv);
        switch (i){
            case 0:
                textView.setText("密码管理");
                break;
            case 1:
                textView.setText("帐号管理");
                break;
            case 2:
                textView.setText("意见反馈");
                break;
            case 3:
                textView.setText("关于");
                break;
        }
        return view;
    }
}
