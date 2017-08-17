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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Adapter.Wode_adapter;
import com.jiayidai.boxuan.Model.UserInfroResp;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qiao on 2017/7/3.
 */

public class WodeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ListView listView;
    private Wode_adapter wode_adapter;
    private ImageButton setting_bt;
    private SharedPreferences sharedPreferences;
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;
    private UserInfroResp userInfroResp;

    private LocalBroadcastManager broadcastManager;
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wode_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configBroadcast();

        sharedPreferences = mContext.getSharedPreferences("jyd", 0);

        setting_bt = getView().findViewById(R.id.id_setting_bt);
        setting_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.getString("userId", "").isEmpty()){
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String response = msg.getData().getString("response");
                Log.e("获取用户信息", response);
                userInfroResp = gson.fromJson(response, UserInfroResp.class);
                wode_adapter.setUserModel(userInfroResp.getData());
            }
        };

        listView = getView().findViewById(R.id.id_wode_list_view);
        wode_adapter = new Wode_adapter(mContext);
        listView.setAdapter(wode_adapter);

        listView.setOnItemClickListener(this);

        refreshView();
    }

    private void configBroadcast() {
        broadcastManager = LocalBroadcastManager.getInstance(mContext);
        intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.login));
        localReceiver = new LocalReceiver();
        broadcastManager.registerReceiver(localReceiver, intentFilter);

    }

    private void refreshView(){
        if (sharedPreferences.getString("userId", "").isEmpty()){
            wode_adapter.setUserModel(new UserInfroResp.UserModel());
        }else {
            requestUserInfo();
        }
    }

    private void requestUserInfo() {

        String idValue = sharedPreferences.getString("userId", "");
        String token = sharedPreferences.getString("token", "");
        String timestamp = String.valueOf(new Date().getTime());
        String url = getString(R.string.host_url)+"/user/detail?id="+idValue+"&access_token="+token
                +"&timestamp="+timestamp;
        Request request = new Request.Builder()
                .url(url)
                .get()
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (sharedPreferences.getString("userId", "").isEmpty()){
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        switch (i){
            case 0:
                Toast.makeText(mContext, "我的信心", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(mContext, "我的信心", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(mContext, "账单", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(mContext, "借款记录", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(mContext, "更改密码", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                logout();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void logout() {
        sharedPreferences.edit().putString("userId", "").commit();
        sendBroadcast();
    }

    private void sendBroadcast() {
        Intent intent = new Intent(getString(R.string.login));
        broadcastManager.sendBroadcast(intent);
    }

    private class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refreshView();
        }
    }
}
