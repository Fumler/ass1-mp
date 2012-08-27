package com.weheartgaming.ass1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.weheartgaming.ass1.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // Called when the user clicks Send
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE,  message);
    	startActivity(intent);
    	
    }
    
    // Displays a web page
    public void displayWeb(View view) {
    	Intent intent = new Intent(this, DisplayWebActivity.class);
    	WebView webView = (WebView) findViewById(R.id.webview);
    	webView.loadUrl("http://weheartgaming.com");
    	startActivity(intent);
    }
}


