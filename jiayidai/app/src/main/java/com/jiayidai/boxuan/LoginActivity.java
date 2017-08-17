package com.jiayidai.boxuan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Model.LoginModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText phone;
    private EditText pwd;

    private String phoneValue, pwdValue;

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    SharedPreferences sharedPreferences;


    private Handler loginHandler;

    private KProgressHUD hud;

    private LocalBroadcastManager broadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        broadcastManager = LocalBroadcastManager.getInstance(this);

        hud = KProgressHUD.create(this, KProgressHUD.Style.SPIN_INDETERMINATE);

        sharedPreferences = getSharedPreferences("jyd", 0);

        loginHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                try {
                    String response = msg.getData().getString("response");
                    LoginModel model = gson.fromJson(response, LoginModel.class);
                    if (model.getCode().equals("200")){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().putString("userId", model.getData().getId()).commit();
                        Intent intent = new Intent(getString(R.string.login));
                        broadcastManager.sendBroadcast(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("登录crash", msg.getData().getString("response"));
                }
            }
        };

        initView();
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.id_login_phone_et);
        pwd = (EditText) findViewById(R.id.id_login_pwd_et);
    }

    public void loginClick(View view){
        phoneValue = phone.getText().toString();
        pwdValue = pwd.getText().toString();

        if (phoneValue.isEmpty() || pwdValue.isEmpty()){
            Toast.makeText(this, "请填写帐号密码", Toast.LENGTH_SHORT).show();
        }else {
            requestLogin();
        }
    }

    public void registerClick(View view){
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }

    public void forgetClick(View view){
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }

    public void requestLogin(){
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("tel", phoneValue)
                .addEncoded("pass", pwdValue)
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/user/login")
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
                loginHandler.sendMessage(msg);
            }
        });

    }

    public void back(View view) {
        finish();
    }
    
    public void loginAdBanner(View view) {
        Toast.makeText(this, "查看大额贷", Toast.LENGTH_SHORT).show();
    }

}
