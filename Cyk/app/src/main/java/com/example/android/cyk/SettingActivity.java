package com.example.android.cyk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.android.cyk.Adapter.Setting_adapter;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;
    private Setting_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.id_setting_content);
        adapter = new Setting_adapter(this);
        listView.setAdapter(adapter);
    }

    public void back(View view){
        SettingActivity.this.finish();
    }

}
