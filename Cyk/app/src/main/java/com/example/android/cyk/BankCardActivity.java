package com.example.android.cyk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.android.cyk.Adapter.BankCard_adapter;

public class BankCardActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.id_bankcard_content);
        BankCard_adapter adapter = new BankCard_adapter(this);
        listView.setAdapter(adapter);
    }

    public void back(View view){
        BankCardActivity.this.finish();
    }

    public void add(View view){

    }
}
