package com.jiayidai.boxuan;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiayidai.boxuan.Model.AuthUrlResp;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EmailAuthActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private OkHttpClient client = new OkHttpClient();
    private Handler handler;
    private KProgressHUD hud;

    private WebView webView;
    private AuthUrlResp authUrlResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);

        hud = KProgressHUD.create(this, KProgressHUD.Style.SPIN_INDETERMINATE);

        webView = (WebView) findViewById(R.id.id_email_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    String response = msg.getData().getString("response");
                    authUrlResp = gson.fromJson(response, AuthUrlResp.class);
                    Log.e("数据", authUrlResp.toString());
                    loadWebView();
                } catch (Exception e) {
                    Toast.makeText(EmailAuthActivity.this, "异常", Toast.LENGTH_SHORT).show();
                }
            }
        };

        requestUrl();
    }

    private void loadWebView() {
        webView.loadUrl(authUrlResp.getData().getRedirectUrl());
        Log.e("链接", authUrlResp.getData().getRedirectUrl());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                hud.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hud.dismiss();
            }

        });
    }

    private void requestUrl() {
        Request request = new Request.Builder()
                .url(getString(R.string.host_url)+"/auth/tel")
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

    public void back(View view) {
        finish();
    }
}
