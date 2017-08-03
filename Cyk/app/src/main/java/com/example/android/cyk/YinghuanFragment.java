package com.example.android.cyk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.cyk.Adapter.Yinghuan_adapter;
import com.example.android.cyk.Model.BillModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by qiao on 2017/7/6.
 */

public class YinghuanFragment extends Fragment implements Yinghuan_adapter.Callback {

    private ListView listView;
    private Yinghuan_adapter adapter;
    private Context mContext;
    SharedPreferences sharedPreferences;
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;
    private BillModel billModel;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yinghuan_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedPreferences = mContext.getSharedPreferences("jyd", 0);

        refreshLayout = getView().findViewById(R.id.id_zhangdan_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorTheme);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestReply();
            }
        });

        listView = getView().findViewById(R.id.id_yinghuan_list_view);
        adapter = new Yinghuan_adapter(mContext, this);
        listView.setAdapter(adapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshLayout.setRefreshing(false);
                String response = msg.getData().getString("response");
                billModel = gson.fromJson(response, BillModel.class);
                adapter.setModel(billModel.getData());
            }
        };

        requestReply();
    }

    private void requestReply() {

        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", "1")
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/project/bill")
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

    @Override
    public void huanClick(BillModel.BillList.BillItem item) {
        Toast.makeText(mContext, "还了(还没接口)", Toast.LENGTH_SHORT).show();
    }
}
