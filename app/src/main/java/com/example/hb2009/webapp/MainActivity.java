package com.example.hb2009.webapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String temp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*뷰xml이 있을때
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.wv_main);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://www.naver.com");*/

        final Context context = MainActivity.this;
        LinearLayout frame = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        frame.setLayoutParams(params);
       // frame.setBackgroundColor(Color.parseColor("#000"));

        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.addJavascriptInterface(new JavascriptInterface() {
            @Override @android.webkit.JavascriptInterface
            public void showToast(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override @android.webkit.JavascriptInterface
            public void sendMsg(String msg) {
                temp = msg;
            }
        },"Hybrid");

        webView.loadUrl("file:///android_asset/www/html/index.html");
        frame.addView(webView);
        setContentView(frame);//반드시 마지막에 해줘야화면이 만들어짐.
    }

    public interface JavascriptInterface{
        public void showToast(String msg);
        public void sendMsg(String msg);
    }
}
