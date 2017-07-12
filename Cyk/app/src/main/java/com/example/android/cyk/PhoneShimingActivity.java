package com.example.android.cyk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PhoneShimingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_shiming);
    }

    public void back(View view){
        PhoneShimingActivity.this.finish();
    }
}
