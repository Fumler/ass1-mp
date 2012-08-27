package com.weheartgaming.ass1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DisplayWebActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WebView webview = (WebView) findViewById(R.id.webView);
        setContentView(webView);
    }


}
