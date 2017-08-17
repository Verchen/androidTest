package com.jiayidai.boxuan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Adapter.Contact_adapter;
import com.jiayidai.boxuan.Model.ContactResp;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Contact_adapter adapter;
    private SharedPreferences sharedPreferences;
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("jyd", 0);

        listView = (ListView) findViewById(R.id.id_contacts_content);
        adapter = new Contact_adapter(this);
        listView.setAdapter(adapter);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_contacts_list_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorTheme);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestContacts();
            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshLayout.setRefreshing(false);
                String response = msg.getData().getString("response");
                Log.e("联系人", response);
                ContactResp contactResp = gson.fromJson(response, ContactResp.class);
                adapter.setDatasource(contactResp.getData());
            }
        };

        requestContacts();
    }

    private void requestContacts() {
        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", sharedPreferences.getString("userId", ""))
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/auth/contact/list")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("response", response.body().string());
                msg.setData(data);
                handler.sendMessage(msg);
            }
        });

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
