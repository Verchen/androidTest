package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.cyk.Model.BillModel;
import com.example.android.cyk.R;

import java.util.ArrayList;

/**
 * Created by qiao on 2017/7/6.
 */

public class Yinghuan_adapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private BillModel.BillList model;
    private Callback mCallback;

    public interface Callback{
        public void huanClick(BillModel.BillList.BillItem item);
    }

    public Yinghuan_adapter(Context context, Callback callback){
        this.mContext = context;
        this.mCallback = callback;
        this.inflater = LayoutInflater.from(context);
        model = new BillModel.BillList();
        model.setNotYetList(new ArrayList<BillModel.BillList.BillItem>());
    }

    public void setModel(BillModel.BillList model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 1+model.getNotYetList().size();
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
            view = inflater.inflate(R.layout.zhangdan_jine_item_layout, viewGroup, false);
            TextView should = view.findViewById(R.id.id_yinghuan_tv);
            TextView weihuan = view.findViewById(R.id.id_weihuan_tv);
            should.setText(model.getYetMoney()+"元");
            weihuan.setText(model.getNotYetMoney()+"元");
            return view;
        }else {
            final BillModel.BillList.BillItem item = model.getNotYetList().get(i - 1);

            view = inflater.inflate(R.layout.zd_lijihuankuan_item_layout, viewGroup, false);
            TextView jine = view.findViewById(R.id.yinghuan_jine);
            TextView benjin = view.findViewById(R.id.yinghuan_benjin);
            TextView lixi = view.findViewById(R.id.yinghuan_lixi);
            TextView type = view.findViewById(R.id.yinghuan_huanType);
            TextView time = view.findViewById(R.id.yinghuan_huanDay);
            Button lijiHuan = view.findViewById(R.id.yinghuan_lijihuan);
            lijiHuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.huanClick(item);
                }
            });
            jine.setText("借款金额"+item.getMoney()+"元 | "+item.getDay()+"天期限");
            benjin.setText(String.valueOf(item.getInterest()));
            lixi.setText(String.valueOf(item.getInterest()));
            type.setText("等额本金(接口还没有)");
            time.setText(item.getMaturityTime());
            return view;
        }
    }

}
