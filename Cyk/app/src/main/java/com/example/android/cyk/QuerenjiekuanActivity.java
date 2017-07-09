package com.example.android.cyk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class QuerenjiekuanActivity extends AppCompatActivity {

    private String shengqingID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querenjiekuan);

        Bundle bundle = this.getIntent().getExtras();
        shengqingID = bundle.getString("shenqingID");

        initView();
        initEvent();
    }

    private void initView() {

    }

    private void initEvent() {

    }

    public void querenClick(View view){
        Intent intent = new Intent(this, ShengqingSuccessActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        QuerenjiekuanActivity.this.finish();
    }
}
