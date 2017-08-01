package com.example.android.cyk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.cyk.Model.ProgressModel;
import com.example.android.cyk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao on 2017/7/6.
 */

public class Jindu_adapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<ProgressModel.itemModel> dataSource = new ArrayList<>();

    public Jindu_adapter(Context context){
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDataSource(List<ProgressModel.itemModel> dataSource) {
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
        view = inflater.inflate(R.layout.jindu_item_layout, viewGroup, false);
        ProgressModel.itemModel itemModel = dataSource.get(i);

        TextView moneyCount = view.findViewById(R.id.id_moneyCount_tv);
        TextView benjin = view.findViewById(R.id.id_benjin_tv);
        TextView lixi = view.findViewById(R.id.id_lixi_tv);
        TextView type = view.findViewById(R.id.id_huanType_tv);
        TextView state = view.findViewById(R.id.id_state_tv);
        Button cancel = view.findViewById(R.id.id_cancel_bt);

        moneyCount.setText("借款金额"+itemModel.getMoney().toString()+"元 | "+String.valueOf(itemModel.getDay())+"天期限");
        benjin.setText(String.valueOf(itemModel.getInAccountMoney()));
        lixi.setText(String.valueOf(itemModel.getServiceMoney()));
        type.setText("等额本金");
        if (itemModel.getAudit() == 0){
            state.setText("审核中");
        }else {
            state.setText("审核已通过");
        }
        return view;
    }
}
