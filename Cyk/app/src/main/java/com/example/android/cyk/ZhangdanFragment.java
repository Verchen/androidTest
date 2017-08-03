package com.example.android.cyk;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by qiao on 2017/7/3.
 */

public class ZhangdanFragment extends Fragment implements View.OnClickListener
{

    private Button yinghuan_bt;
    private Button yihuan_bt;

    private Fragment yinghuanFragment;
    private Fragment yihuanFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhangdan_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();

        select(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("账单页面显示", "onResume");
    }

    private void initEvent() {
        yinghuan_bt.setOnClickListener(this);
        yihuan_bt.setOnClickListener(this);
    }

    private void initView() {
        yinghuan_bt = getView().findViewById(R.id.id_yinghuan_bt);
        yihuan_bt = getView().findViewById(R.id.id_yihuan_bt);
    }

    @Override
    public void onClick(View view) {
        resetStatus();
        switch (view.getId())
        {
            case R.id.id_yinghuan_bt:
                select(0);
                break;
            case R.id.id_yihuan_bt:
                select(1);
                break;
        }
    }

    private void select(int i) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        if (i == 0) {
            if (yinghuanFragment == null){
                yinghuanFragment = new YinghuanFragment();
                transaction.add(R.id.id_zhangdan_content, yinghuanFragment);
            }else {
                transaction.show(yinghuanFragment);
            }
            yinghuan_bt.setBackgroundResource(R.drawable.shape_corner_left_selected);
            yinghuan_bt.setTextColor(Color.parseColor("#82cdc4"));
        }
        if (i == 1){
            if (yihuanFragment == null){
                yihuanFragment = new YihuanFragment();
                transaction.add(R.id.id_zhangdan_content, yihuanFragment);
            }else {
                transaction.show(yihuanFragment);
            }
            yihuan_bt.setBackgroundResource(R.drawable.shape_corner_right_selected);
            yihuan_bt.setTextColor(Color.parseColor("#82cdc4"));
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (yinghuanFragment != null){
            transaction.hide(yinghuanFragment);
        }
        if (yihuanFragment != null){
            transaction.hide(yihuanFragment);
        }
    }


    private void resetStatus() {
        yinghuan_bt.setBackgroundResource(R.drawable.shape_corner_left_normal);
        yinghuan_bt.setTextColor(Color.parseColor("#ffffff"));
        yihuan_bt.setBackgroundResource(R.drawable.shape_corner_right_normal);
        yihuan_bt.setTextColor(Color.parseColor("#ffffff"));
    }
}
