package com.jiayidai.boxuan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout tab_news;
    private LinearLayout tab_find;
    private LinearLayout tab_live;
    private LinearLayout tab_mine;

    private ImageButton tab_news_img;
    private ImageButton tab_find_img;
    private ImageButton tab_live_img;
    private ImageButton tab_mine_img;

    private TextView tab_news_tv;
    private TextView tab_find_tv;
    private TextView tab_live_tv;
    private TextView tab_mine_tv;

    private Fragment jiekuan_fragment;
    private Fragment renzheng_fragment;
    private Fragment zhangdan_fragment;
    private Fragment wode_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

        selected(0);
    }

    private void initEvent() {
        tab_news.setOnClickListener(this);
        tab_find.setOnClickListener(this);
        tab_live.setOnClickListener(this);
        tab_mine.setOnClickListener(this);
    }

    private void initView() {
        tab_news = (LinearLayout) findViewById(R.id.id_tab_news);
        tab_find = (LinearLayout) findViewById(R.id.id_tab_find);
        tab_live = (LinearLayout) findViewById(R.id.id_tab_live);
        tab_mine = (LinearLayout) findViewById(R.id.id_tab_mine);

        tab_news_img = (ImageButton) findViewById(R.id.id_tab_news_img);
        tab_find_img = (ImageButton) findViewById(R.id.id_tab_find_img);
        tab_live_img = (ImageButton) findViewById(R.id.id_tab_live_img);
        tab_mine_img = (ImageButton) findViewById(R.id.id_tab_mine_img);

        tab_news_tv = (TextView) findViewById(R.id.id_tab_news_tv);
        tab_find_tv = (TextView) findViewById(R.id.id_tab_find_tv);
        tab_live_tv = (TextView) findViewById(R.id.id_tab_live_tv);
        tab_mine_tv = (TextView) findViewById(R.id.id_tab_mine_tv);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.id_tab_news:
                selected(0);
                break;
            case R.id.id_tab_find:
                selected(1);
                break;
            case R.id.id_tab_live:
                selected(2);
                break;
            case R.id.id_tab_mine:
                selected(3);
                break;
        }

    }

    private void resetStatus() {
        tab_news_img.setImageResource(R.drawable.tabbar_home_normal);
        tab_find_img.setImageResource(R.drawable.tabbar_auth_normal);
        tab_live_img.setImageResource(R.drawable.tabbar_zhangdan_normal);
        tab_mine_img.setImageResource(R.drawable.tabbar_wo_normal);

        tab_news_tv.setTextColor(Color.parseColor("#cdcdcd"));
        tab_find_tv.setTextColor(Color.parseColor("#cdcdcd"));
        tab_live_tv.setTextColor(Color.parseColor("#cdcdcd"));
        tab_mine_tv.setTextColor(Color.parseColor("#cdcdcd"));
    }

    private void selected(int i) {
        resetStatus();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFrag(transaction);
        switch (i)
        {
            case 0:
                if (jiekuan_fragment == null){
                    jiekuan_fragment = new JiekuanFragment();
                    transaction.add(R.id.id_content, jiekuan_fragment);
                }else {
                    transaction.show(jiekuan_fragment);
                }
                tab_news_img.setImageResource(R.drawable.tabbar_home_select);
                tab_news_tv.setTextColor(Color.parseColor("#82cdc4"));
                break;
            case 1:
                if (renzheng_fragment == null){
                    renzheng_fragment = new RenzhengFragment();
                    transaction.add(R.id.id_content, renzheng_fragment);
                }else {
                    transaction.show(renzheng_fragment);
                }
                tab_find_img.setImageResource(R.drawable.tabbar_auth_select);
                tab_find_tv.setTextColor(Color.parseColor("#82cdc4"));
                break;
            case 2:
                if (zhangdan_fragment == null){
                    zhangdan_fragment = new ZhangdanFragment();
                    transaction.add(R.id.id_content, zhangdan_fragment);
                }else {
                    transaction.show(zhangdan_fragment);
                }
                tab_live_img.setImageResource(R.drawable.tabbar_zhangdan_select);
                tab_live_tv.setTextColor(Color.parseColor("#82cdc4"));
                break;
            case 3:
                if (wode_fragment == null){
                    wode_fragment = new WodeFragment();
                    transaction.add(R.id.id_content, wode_fragment);
                }else {
                    transaction.show(wode_fragment);
                }
                tab_mine_img.setImageResource(R.drawable.tabbar_wo_select);
                tab_mine_tv.setTextColor(Color.parseColor("#82cdc4"));
                break;
        }

        transaction.commit();

    }

    private void hideFrag(FragmentTransaction transaction) {
        if (jiekuan_fragment != null){
            transaction.hide(jiekuan_fragment);
        }
        if (renzheng_fragment != null){
            transaction.hide(renzheng_fragment);
        }
        if (zhangdan_fragment != null){
            transaction.hide(zhangdan_fragment);
        }
        if (wode_fragment != null){
            transaction.hide(wode_fragment);
        }
    }

}
