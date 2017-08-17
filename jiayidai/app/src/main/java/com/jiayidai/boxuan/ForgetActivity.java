package com.jiayidai.boxuan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

public class ForgetActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;
    private KProgressHUD hud;

    private EditText phone;
    private EditText pwd;
    private EditText pwd2;
    private EditText sms;
    private CountDownButton smsBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        hud = KProgressHUD.create(this, KProgressHUD.Style.SPIN_INDETERMINATE);

        initView();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                try {
                    String response = msg.getData().getString("response");
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = gson.fromJson(response, map.getClass());
                    if (map.get("code").equals(200.0)) {
                        Toast.makeText(ForgetActivity.this, (String)map.get("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ForgetActivity.this, (String)map.get("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("忘记密码crash", msg.getData().getString("response"));
                }
            }
        };
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.id_forget_phone_et);
        pwd = (EditText) findViewById(R.id.id_forget_pwd_et);
        pwd2 = (EditText) findViewById(R.id.id_forget_pwdtwo_et);
        sms = (EditText) findViewById(R.id.id_forget_sms_et);
        smsBt = (CountDownButton) findViewById(R.id.forget_cd_btn);
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
    }


    public void back(View view) {
        finish();
    }

    public void commit(View view) {
        String phoneValue = phone.getText().toString();
        String pwdValue = pwd.getText().toString();
        String pwd2Value = pwd2.getText().toString();
        String smsValue = sms.getText().toString();
        if (phoneValue.isEmpty() || pwdValue.isEmpty() || pwd2Value.isEmpty() || smsValue.isEmpty()) {
            Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwdValue.equals(pwd2Value)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("tel", phoneValue)
                .addEncoded("newPass", pwdValue)
                .addEncoded("confirmPass", pwd2Value)
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/user/forgetpass")
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

    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    private void requestSMS() {
        String phoneValue = phone.getText().toString();
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

}
