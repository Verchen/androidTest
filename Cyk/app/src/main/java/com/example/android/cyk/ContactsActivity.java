package com.example.android.cyk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cyk.Adapter.Contact_adapter;

public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Contact_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.id_contacts_content);
        adapter = new Contact_adapter(this);
        listView.setAdapter(adapter);
    }

    public void back(View view){
        ContactsActivity.this.finish();
    }

    public void addContact(View view){
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
