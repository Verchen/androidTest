package com.jiayidai.boxuan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Adapter.Jiekuan_list_adapter;
import com.jiayidai.boxuan.Model.BorrowModel;
import com.jiayidai.boxuan.Model.TokenModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by qiao on 2017/7/3.
 */

public class ShenqingFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context context;
    private TokenModel tokenModel;
    SharedPreferences sp;
    private BorrowModel borrowModel;
    private List<BorrowModel.borrowItem> items = new ArrayList<>();

    private OkHttpClient client = new OkHttpClient();

    private ListView listView;
    private Jiekuan_list_adapter adapter;
    private SwipeRefreshLayout refreshLayout;

    private Handler handler;
    private Handler crachHandler;

    private String userId = "";

    private LocalReceiver localReceiver;
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shenqing_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configBroadcast();

        sp = context.getSharedPreferences("jyd", 0);
        userId = sp.getString("userId", "");

        listView = getView().findViewById(R.id.shenqing_list_view);
        adapter = new Jiekuan_list_adapter(items, context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        refreshLayout = getView().findViewById(R.id.id_shenqing_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorTheme);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestBorrowList();
            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshLayout.setRefreshing(false);
                try {
                    String response = msg.getData().getString("response");
                    Log.e("借款列表", response);
                    Gson gson = new Gson();
                    borrowModel = gson.fromJson(response, BorrowModel.class);
                    items = borrowModel.getData();
                    adapter.setDataSource(items);
                } catch (Exception e) {

                }
            }
        };

        crachHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshLayout.setRefreshing(false);
            }
        };

        requestToken();

        requestBorrowList();
    }

    private void configBroadcast() {
        broadcastManager = LocalBroadcastManager.getInstance(context);
        intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.login));
        localReceiver = new LocalReceiver();
        broadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    private void requestToken() {
        RequestBody body = new FormBody.Builder()
                .addEncoded("grant_type", "password")
                .addEncoded("client_id", "arclient")
                .addEncoded("client_secret", "eyJpdiI6IndOMjdYaWZRTW1WSElyMFVNQXdjcXc9PSIsInZhbHVlIjoiXC9UeWVoU1FOdHFtdGlvbkZc")
                .addEncoded("username", "13261301876")
                .addEncoded("password", "111")
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/oauth/token")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    tokenModel = gson.fromJson(response.body().string(), TokenModel.class);
                    requestRefreshToken();
                }catch (Exception e) {
                }

            }
        });
    }

    private void requestRefreshToken() {
        RequestBody body = new FormBody.Builder()
                .addEncoded("grant_type", "refresh_token")
                .addEncoded("refresh_token", tokenModel.getRefresh_token())
                .addEncoded("username", "13261301876")
                .addEncoded("password", "111")
                .addEncoded("client_id", "arclient")
                .addEncoded("client_secret", "eyJpdiI6IndOMjdYaWZRTW1WSElyMFVNQXdjcXc9PSIsInZhbHVlIjoiXC9UeWVoU1FOdHFtdGlvbkZc")
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/oauth/refresh/token")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    TokenModel model = gson.fromJson(response.body().string(), TokenModel.class);
                    sp.edit().putString("token", model.getAccess_token()).commit();
                }catch (Exception e){
                }

            }
        });
    }


    private void requestBorrowList() {
        long time = new Date().getTime();

        RequestBody body;
        Request request;

        if (sp.getString("userId", "").isEmpty()){
            request = new Request.Builder()
                    .url(getString(R.string.host_url)+"/project/list")
                    .build();
        } else {
            body = new FormBody.Builder()
                    .addEncoded("userId", sp.getString("userId", ""))
                    .addEncoded("access_token", sp.getString("token", ""))
                    .addEncoded("timestamp", String.valueOf(time))
                    .build();
            request = new Request.Builder()
                    .url(getString(R.string.host_url)+"/project/list")
                    .post(body)
                    .build();
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                crachHandler.sendMessage(new Message());
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (sp.getString("userId", "").isEmpty()){
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        } else {
            BorrowModel.borrowItem item = borrowModel.getData().get(i);
            if (item.getLock().equals("1")){
                Intent intent = new Intent(context, QuerenjiekuanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shenqingID", item.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context,Intent intent){
            requestBorrowList();
        }
    }
}

