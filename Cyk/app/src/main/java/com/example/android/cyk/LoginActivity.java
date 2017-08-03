package com.example.android.cyk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.cyk.Model.LoginModel;
import com.google.gson.Gson;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hud = KProgressHUD.create(this, KProgressHUD.Style.SPIN_INDETERMINATE);

        sharedPreferences = getSharedPreferences("jyd", 0);

        loginHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                String response = msg.getData().getString("response");
                LoginModel model = gson.fromJson(response, LoginModel.class);
                if (model.getCode().equals("200")){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putString("userId", model.getData().getId()).commit();
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
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

}
