package com.example.android.cyk.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.cyk.R;

/**
 * Created by qiao on 2017/7/3.
 */

public class Jiekuan_list_adapter extends BaseAdapter {

    private String[] dataSource;
    private Context context;
    private LinearLayout layout;

    public Jiekuan_list_adapter (String[] datas, Context context) {
        this.dataSource = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSource.length;
    }

    @Override
    public Object getItem(int i) {
        return dataSource[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.jiekuan_list_layout, null);
        TextView jine = layout.findViewById(R.id.jiekuan_jine);
        LinearLayout bg = layout.findViewById(R.id.jiekaun_item_bg);
        TextView level = layout.findViewById(R.id.jiekuan_item_level);

        switch (i)
        {
            case 0:
                bg.setBackgroundResource(R.drawable.shape_corner_white);

                Drawable drawable = context.getResources().getDrawable(R.drawable.lock_open);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());

                level.setCompoundDrawables(null, drawable, null, null);

                break;

            default:
                break;
        }

        final SpannableStringBuilder sp = new  SpannableStringBuilder("700元");
        sp.setSpan(new ForegroundColorSpan(0xff82cdc4), "700".length(), "700元".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
        sp.setSpan(new AbsoluteSizeSpan(18, true), "700".length(), "700元".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        jine.setText(sp);

        return layout;
    }
}
