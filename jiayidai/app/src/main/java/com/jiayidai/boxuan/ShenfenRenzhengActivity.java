package com.jiayidai.boxuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShenfenRenzhengActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenfen_renzheng);
    }
    public void back(View view){
        ShenfenRenzhengActivity.this.finish();
    }
}
