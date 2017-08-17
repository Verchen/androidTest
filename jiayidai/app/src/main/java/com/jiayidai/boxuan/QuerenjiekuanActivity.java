package com.jiayidai.boxuan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Model.BorrowDetailModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
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

public class QuerenjiekuanActivity extends AppCompatActivity {

    private String shengqingID;
    private Handler handler;
    private Handler commitHandler;
    private OkHttpClient client = new OkHttpClient();
    private BorrowDetailModel.Detail detailModel;
    private Gson gson = new Gson();
    SharedPreferences sp;
    private Button commitBtn;
    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querenjiekuan);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);

        sp = getSharedPreferences("jyd", 0);

        Bundle bundle = this.getIntent().getExtras();
        shengqingID = bundle.getString("shenqingID");

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                try {
                    String response = msg.getData().getString("response");
                    Log.e("借款详情", response);
                    Gson gson = new Gson();
                    BorrowDetailModel model = gson.fromJson(response, BorrowDetailModel.class);
                    detailModel = model.getData();
                    initView();
                } catch (Exception e) {
                    Log.e("借款详情crach", msg.getData().getString("response"));
                }
            }
        };

        commitHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hud.dismiss();
                String response = msg.getData().getString("response");
                Log.e("确认借款", response);
                Map<String, Object> map = new HashMap<String, Object>();
                map = gson.fromJson(response, map.getClass());
                Log.e("code=", map.get("code").getClass().toString());
                if (map.get("code").equals(200.0)){
                    Log.e("开始跳转","");
                    Intent intent = new Intent(QuerenjiekuanActivity.this, ShengqingSuccessActivity.class);
                    startActivity(intent);
                }
            }
        };

        requestBorrowDetail();

    }

    private void requestBorrowDetail() {
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", sp.getString("userId", ""))
                .addEncoded("access_token", sp.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/money/loan/"+shengqingID)
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

    private void initView() {
        TextView level = (TextView) findViewById(R.id.level_tv);
        TextView money = (TextView) findViewById(R.id.money_tv);
        TextView serverMoney = (TextView) findViewById(R.id.serverMoney_tv);
        TextView day = (TextView) findViewById(R.id.day_tv);
        TextView name = (TextView) findViewById(R.id.name_tv);
        TextView bank = (TextView) findViewById(R.id.bankName_tv);
        TextView bankNum = (TextView) findViewById(R.id.bankNum_tv);
        TextView huanMoney = (TextView) findViewById(R.id.huanMoney_tv);
        TextView lixiMoney = (TextView) findViewById(R.id.lixi_tv);
        TextView huanDay = (TextView) findViewById(R.id.huanDay_tv);
        commitBtn = (Button) findViewById(R.id.commit_bt);
        commitBtn.setVisibility(View.VISIBLE);

        level.setText("LV"+detailModel.getDay());
        money.setText(detailModel.getInAccountMoney()+"元");
        serverMoney.setText(detailModel.getServiceMoney()+"元");
        day.setText(detailModel.getDay()+"天");
        name.setText("乔某人(接口没有)");
        bank.setText(detailModel.getBack());
        bankNum.setText(detailModel.getCardCode());
        huanMoney.setText(detailModel.getRepaymentAllMoney()+"元");
        lixiMoney.setText("(包含利息"+detailModel.getInterest()+"元)");
        huanDay.setText(detailModel.getRepaymentTime());
    }

    public void querenClick(View view){
        hud.show();
        RequestBody body = new FormBody.Builder()
                .addEncoded("userId", sp.getString("userId", ""))
                .addEncoded("projectId", shengqingID)
                .addEncoded("cardId", String.valueOf(detailModel.getCardId()))
                .addEncoded("access_token", sp.getString("token", ""))
                .addEncoded("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/money/loan")
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
                commitHandler.sendMessage(msg);
            }
        });
    }

    public void back(View view){
        QuerenjiekuanActivity.this.finish();
    }


}
