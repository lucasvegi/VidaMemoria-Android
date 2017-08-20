package com.example.luizh.vidamemoria_android;

/**
 * Created by luizh on 20/08/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webView = (WebView) findViewById(R.id.webView1);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");
        webView.loadUrl(URL);

    }

}