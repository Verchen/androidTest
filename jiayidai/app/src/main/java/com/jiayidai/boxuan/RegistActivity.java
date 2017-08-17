package com.jiayidai.boxuan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.white.countdownbutton.CountDownButton;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    private Handler registHandler;

    private EditText phone;
    private String phoneValue;
    private EditText password;
    private String passwordValue;
    private EditText sms;
    private String smsValue;
    private Button registBt;
    private CountDownButton smsBt;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        sharedPreferences = getSharedPreferences("jyd", 0);
        hud = KProgressHUD.create(this, KProgressHUD.Style.SPIN_INDETERMINATE);

        initView();
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.id_regist_phone_et);
        password = (EditText) findViewById(R.id.id_regist_pwd_et);
        sms = (EditText) findViewById(R.id.id_regist_sms_et);
        registBt = (Button) findViewById(R.id.id_regist_Button);
        registBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRegist();
            }
        });
        smsBt = (CountDownButton) findViewById(R.id.cd_btn);
        smsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSMS();
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                smsBt.setEnableCountDown(isTelPhoneNumber(editable.toString()));
            }
        });

        registHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                try {
                    String response = msg.getData().getString("response");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = gson.fromJson(response, map.getClass());
                    if (map.get("code").equals(200.0)) {
                        Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistActivity.this, (String)map.get("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        };
    }

    private void requestRegist() {
        phoneValue = phone.getText().toString();
        passwordValue = password.getText().toString();
        smsValue = sms.getText().toString();
        if (phoneValue.isEmpty() || passwordValue.isEmpty() || smsValue.isEmpty()) {
            Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
            return;
        }
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("tel", phoneValue)
                .addEncoded("pass", passwordValue)
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/user/reg")
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
                registHandler.sendMessage(msg);
            }
        });

    }

    private void requestSMS() {
        phoneValue = phone.getText().toString();
        if (phoneValue.isEmpty()) {
            Toast.makeText(this, "请填写手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isTelPhoneNumber(phoneValue)) {
            Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "无效手机号", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    public void back(View view) {
        finish();
    }

    public void adBannerClick(View view) {
        Toast.makeText(this, "查看大额贷", Toast.LENGTH_SHORT).show();
    }
}
