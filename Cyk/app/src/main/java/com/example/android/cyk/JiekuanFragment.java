package com.example.android.cyk;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by qiao on 2017/7/3.
 */

public class JiekuanFragment extends Fragment implements View.OnClickListener{


    private Button shenqing_bt;
    private Button jindu_bt;

    private Fragment shenqing_fragment;
    private Fragment jindu_fragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jiekuan_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initEvent();
        selected(0);

    }

    private void selected(int i) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFrag(transaction);
        switch (i)
        {
            case 0:
                if (shenqing_fragment == null){
                    shenqing_fragment = new ShenqingFragment();
                    transaction.add(R.id.id_jiekuan_content, shenqing_fragment);
                }else {
                    transaction.show(shenqing_fragment);
                }
                shenqing_bt.setBackgroundResource(R.drawable.shape_corner_left_selected);
                shenqing_bt.setTextColor(Color.parseColor("#82cdc4"));
                break;
            case 1:
                if (jindu_fragment == null){
                    jindu_fragment = new JinduFragment();
                    transaction.add(R.id.id_jiekuan_content, jindu_fragment);
                }else {
                    transaction.show(jindu_fragment);
                }
                jindu_bt.setBackgroundResource(R.drawable.shape_corner_right_selected);
                jindu_bt.setTextColor(Color.parseColor("#82cdc4"));
                break;
        }
        transaction.commit();
    }

    private void hideFrag(FragmentTransaction transaction) {
        if (shenqing_fragment != null){
            transaction.hide(shenqing_fragment);
        }
        if (jindu_fragment != null){
            transaction.hide(jindu_fragment);
        }
    }

    private void initEvent() {
        shenqing_bt.setOnClickListener(this);
        jindu_bt.setOnClickListener(this);
    }

    private void initView() {
        shenqing_bt = getView().findViewById(R.id.id_shenqing_bt);
        jindu_bt = getView().findViewById(R.id.id_jindu_bt);
    }

    @Override
    public void onClick(View view) {
        resetStatus();
        switch (view.getId())
        {
            case R.id.id_shenqing_bt:
                selected(0);
                break;
            case R.id.id_jindu_bt:
                selected(1);
                break;
        }
    }

    private void resetStatus() {
        shenqing_bt.setBackgroundResource(R.drawable.shape_corner_left_normal);
        shenqing_bt.setTextColor(Color.parseColor("#ffffff"));
        jindu_bt.setBackgroundResource(R.drawable.shape_corner_right_normal);
        jindu_bt.setTextColor(Color.parseColor("#ffffff"));

    }
}
