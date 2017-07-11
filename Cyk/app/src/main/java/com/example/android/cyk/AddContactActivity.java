package com.example.android.cyk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void back(View view){
        AddContactActivity.this.finish();
    }

    public void add(View view){
        AddContactActivity.this.finish();
    }
}
