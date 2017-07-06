package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cyk.R;

/**
 * Created by qiao on 2017/7/5.
 */

public class Renzheng_adapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;

    public Renzheng_adapter (Context context){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (i == 0){
            view = inflater.inflate(R.layout.renzheng_jibenxinxi_item_layout, viewGroup, false);
        }else if (i == 1){
            view = inflater.inflate(R.layout.rz_lianxiren_item_layout, viewGroup, false);
        } else if (i == 2){
            view = inflater.inflate(R.layout.rz_qita_item_layout, viewGroup, false);
        }else {
            view = inflater.inflate(R.layout.rz_zhima_item_layout, viewGroup, false);
            ImageView imageView = view.findViewById(R.id.id_zhima_img);
            TextView textView = view.findViewById(R.id.id_zhima_tv);
            switch (i)
            {
                case 3:
                    imageView.setImageResource(R.drawable.zhima);
                    textView.setText("芝麻信用分");
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.jd);
                    textView.setText("京东白条");
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.zhima);
                    textView.setText("手机账单");
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.zhima);
                    textView.setText("信用卡账单");
                    break;
            }
        }
        return view;
    }
}
