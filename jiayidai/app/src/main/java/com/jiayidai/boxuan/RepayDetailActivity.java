package com.jiayidai.boxuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Model.BillModel;

public class RepayDetailActivity extends AppCompatActivity {

    private Gson gson = new Gson();

    private BillModel.BillList.BillItem itemModel;

    private TextView jineTV;
    private TextView benjinTV;
    private TextView lixiTV;
    private TextView huanTypeTV;
    private TextView huanDayTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay_detail);

        String itemJson = this.getIntent().getStringExtra("item");
        itemModel = gson.fromJson(itemJson, BillModel.BillList.BillItem.class);

        initView();
    }

    private void initView() {
        jineTV = (TextView) findViewById(R.id.yinghuan_detail_jine);
        benjinTV = (TextView) findViewById(R.id.yinghuan_detail_benjin);
        lixiTV = (TextView) findViewById(R.id.yinghuan_detail_lixi);
        huanTypeTV = (TextView) findViewById(R.id.yinghuan_detail_huanType);
        huanDayTV = (TextView) findViewById(R.id.yinghuan_detail_huanDay);

        jineTV.setText("借款金额"+itemModel.getMoney()+"元 | "+itemModel.getDay()+"天期限");
        benjinTV.setText(String.valueOf(itemModel.getInterest()));
        lixiTV.setText(String.valueOf(itemModel.getInterest()));
        huanTypeTV.setText("等额本金(接口还没有)");
        huanDayTV.setText(itemModel.getMaturityTime());
    }

    public void lijiHuanClick(View view) {
        Toast.makeText(this, "还了（接口还没有）", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        finish();
    }
}
