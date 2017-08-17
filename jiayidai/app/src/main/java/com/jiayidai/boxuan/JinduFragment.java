package com.jiayidai.boxuan;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Adapter.Jindu_adapter;
import com.jiayidai.boxuan.Model.ProgressModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

public class JinduFragment extends Fragment implements Jindu_adapter.Callback {

    private Context mContext;
    private ListView listView;
    private Button emptyView;
    private Jindu_adapter adapter;
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;
    private Handler listHandler;
    private SwipeRefreshLayout refreshLayout;
    private Handler cancelHandler;
    private KProgressHUD hud;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getContext();
        sharedPreferences = mContext.getSharedPreferences("jyd", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jindu_layout, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferences.getString("userId", "").isEmpty()){
            adapter.setDataSource(new ArrayList<ProgressModel.itemModel>());
        }else {
            requestProgressList();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        hud = KProgressHUD.create(mContext, KProgressHUD.Style.SPIN_INDETERMINATE);

        listView = getView().findViewById(R.id.id_jindu_list_view);
        adapter = new Jindu_adapter(mContext, this);
        listView.setAdapter(adapter);

        emptyView = getView().findViewById(R.id.id_jindu_empty);

        refreshLayout = getView().findViewById(R.id.jindu_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorTheme);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestProgressList();
            }
        });

        listHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                refreshLayout.setRefreshing(false);
                String response = msg.getData().getString("response");
                ProgressModel model = gson.fromJson(response, ProgressModel.class);
                if (model.getData().size()>0) {
                    refreshLayout.setVisibility(View.VISIBLE);
                    adapter.setDataSource(model.getData());
                    emptyView.setVisibility(View.GONE);
                }else {
                    emptyView.setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                }
            }
        };

        cancelHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                String response = msg.getData().getString("response");
                Map<String, Object> map = new HashMap<String, Object>();
                map = gson.fromJson(response, map.getClass());
                if (map.get("code").equals(200.0)){
                    Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                    requestProgressList();
                }
            }
        };
    }

    private void requestProgressList() {
        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", sharedPreferences.getString("userId", ""))
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/project/progress")
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
                listHandler.sendMessage(msg);
            }
        });
    }

    @Override
    public void cancelClick(ProgressModel.itemModel model) {
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", sharedPreferences.getString("userId", ""))
                .addEncoded("loanId", String.valueOf(model.getLoan_id()))
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/user/unapply")
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
                cancelHandler.sendMessage(msg);
            }
        });

    }
}
