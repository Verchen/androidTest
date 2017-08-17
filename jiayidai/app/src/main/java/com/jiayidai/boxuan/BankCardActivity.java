package com.jiayidai.boxuan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Adapter.BankCard_adapter;

import okhttp3.OkHttpClient;

public class BankCardActivity extends AppCompatActivity {

    private ListView listView;

    SharedPreferences sharedPreferences;
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        sharedPreferences = getSharedPreferences("jyd", 0);

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.id_bankcard_content);
        BankCard_adapter adapter = new BankCard_adapter(this);
        listView.setAdapter(adapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };
    }

    public void back(View view){
        BankCardActivity.this.finish();
    }

    public void add(View view){

    }
}
