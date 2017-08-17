package com.jiayidai.boxuan.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiayidai.boxuan.Model.BorrowModel;
import com.jiayidai.boxuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao on 2017/7/3.
 */

public class Jiekuan_list_adapter extends BaseAdapter {

    private List<BorrowModel.borrowItem> dataSource = new ArrayList<>();
    private Context context;
    private LinearLayout layout;

    public Jiekuan_list_adapter (List<BorrowModel.borrowItem> datas, Context context) {
        this.dataSource = datas;
        this.context = context;
    }

    public void setDataSource(List<BorrowModel.borrowItem> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BorrowModel.borrowItem item = dataSource.get(i);
        String lv = item.getLv();
        String money = item.getMoney();
        String day = item.getDay();
        String lock = item.getLock();

        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.jiekuan_list_layout, null);
        TextView jine = layout.findViewById(R.id.jiekuan_jine);
        LinearLayout bg = layout.findViewById(R.id.jiekaun_item_bg);
        TextView level = layout.findViewById(R.id.jiekuan_item_level);
        TextView dayText = layout.findViewById(R.id.jiekuan_item_day);

        if (lock.equals("1")) {
            bg.setBackgroundResource(R.drawable.shape_corner_white);
            Drawable drawable = context.getResources().getDrawable(R.drawable.lock_open);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            level.setCompoundDrawables(null, drawable, null, null);
        }
//        switch (i)
//        {
//            case 0:
//                bg.setBackgroundResource(R.drawable.shape_corner_white);
//
//                Drawable drawable = context.getResources().getDrawable(R.drawable.lock_open);
//                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
//
//                level.setCompoundDrawables(null, drawable, null, null);
//
//                break;
//
//            default:
//                break;
//        }

//        final SpannableStringBuilder sp = new  SpannableStringBuilder(money+"元");
//        sp.setSpan(new ForegroundColorSpan(0xff82cdc4), money.length(), "700元".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体颜色
//        sp.setSpan(new AbsoluteSizeSpan(18, true), "700".length(), "700元".length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //字体大小
        level.setText("LV"+lv);
        jine.setText(money+"元");
        dayText.setText(day+"天");

        return layout;
    }
}
