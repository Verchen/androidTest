package com.jiayidai.boxuan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Model.RelationResp;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

public class AddContactActivity extends AppCompatActivity implements View.OnFocusChangeListener, NumberPicker.OnValueChangeListener{

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private Handler handler;
    private Handler relationHandlerl;
    private SharedPreferences sharedPreferences;

    private EditText name;
    private EditText phone;
    private EditText relation;
    private String nameValue;
    private String phoneValue;
    private String relaValue;
    private NumberPicker picker;
    private String relationId = "";
    private List<RelationResp.RelationModel> relations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        sharedPreferences = getSharedPreferences("jyd", 0);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String response = msg.getData().getString("response");
                Log.e("添加", response);
                Map<String, Object> map = new HashMap<String, Object>();
                map = gson.fromJson(response, map.getClass());
                if (map.get("code").equals(200.0)){
                    Toast.makeText(AddContactActivity.this, map.get("message").toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(AddContactActivity.this, map.get("message").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        relationHandlerl = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String response = msg.getData().getString("response");
                Log.e("关系列表", response);
                RelationResp relationResp = gson.fromJson(response, RelationResp.class);
                relations = relationResp.getData();
                initPicker();
            }
        };

        initView();

        requestRelation();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.id_contacts_add_name);
        phone = (EditText) findViewById(R.id.id_contacts_add_phone);
        relation = (EditText) findViewById(R.id.id_contacts_add_relation);
        relation.setOnFocusChangeListener(this);
        relation.setInputType(InputType.TYPE_NULL);
        picker = (NumberPicker) findViewById(R.id.id_add_picker);

    }

    private void initPicker() {

//        String[] source = {"选择关系", "父亲", "母亲", "夫妻", "子女", "兄弟姐妹", "其它亲属"};
        String[] source = new String[relations.size()+1];
        source[0] = "选择关系";
        for (int i = 1; i<(relations.size()+1); i++) {
            source[i] = relations.get(i-1).getName();
        }
        picker.setDisplayedValues(source);
        picker.setMinValue(0);
        picker.setMaxValue(source.length - 1);
        picker.setWrapSelectorWheel(false);
        picker.setOnValueChangedListener(this);
    }

    private void requestRelation(){
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/basic/relation")
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
                relationHandlerl.sendMessage(msg);
            }
        });
    }

    public void back(View view){
        AddContactActivity.this.finish();
    }

    public void add(View view){
        nameValue = name.getText().toString();
        phoneValue = phone.getText().toString();
        relaValue = relation.getText().toString();
        if (nameValue.isEmpty() || phoneValue.isEmpty() || relaValue.isEmpty()){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isTelPhoneNumber(phoneValue)){
            requestAdd();
        }else {
            Toast.makeText(this, "无效手机号", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestAdd() {
        RequestBody body = new FormBody.Builder()
                .addEncoded("tel", phoneValue)
                .addEncoded("name", nameValue)
                .addEncoded("relation", String.valueOf(relationId))
                .addEncoded("userId", sharedPreferences.getString("userId", ""))
                .addEncoded("access_token", sharedPreferences.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/auth/contact/add")
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
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        switch (i1){
            case 0:
                relation.setText("");
                relationId = "";
                break;
            default:
                RelationResp.RelationModel md = relations.get(i1 - 1);
                relation.setText(md.getName());
                relationId = md.getId();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b){
            picker.setVisibility(View.VISIBLE);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(relation.getWindowToken(), 0);
        }else {
            picker.setVisibility(View.INVISIBLE);
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
}
