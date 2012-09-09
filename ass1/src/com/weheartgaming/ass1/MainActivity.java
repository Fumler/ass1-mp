package com.weheartgaming.ass1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button sendData = (Button) findViewById(R.id.button1);
        sendData.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(v.getContext(), SendData.class);
        		startActivity(intent);
        	}
        });
        
        Button getData = (Button) findViewById(R.id.button2);
        getData.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(v.getContext(), GetData.class);
        		startActivity(intent);
        	}
        });
        
        Button getImage = (Button) findViewById(R.id.button3);
        getImage.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(v.getContext(), GetImage.class);
        		startActivity(intent);
        	}
        });
        
        Button showLocation = (Button) findViewById(R.id.button4);
        showLocation.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(v.getContext(),ShowLocation.class );
        		startActivity(intent);
        	}
        });
        
    }


}
