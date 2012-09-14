package com.weheartgaming.ass1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SaveData extends Activity {
	String url = "http://hum.re/filar/herp.txt";
	TextView textview;
	String text = null;
	String file = "herp.txt";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_data);
        
        textview = (TextView) findViewById(R.id.text4);
        
        // Checks if the user is connected to the internet
     	ConnectivityManager connMgr = (ConnectivityManager)
     			getSystemService(Context.CONNECTIVITY_SERVICE);
     	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
     		
     	// If connected, show the online version of the file
     	if(networkInfo != null && networkInfo.isConnected()) {
     		new DownloadText().execute();
     		
     		}
     	else {
     		try {
     			FileInputStream fis = openFileInput(file);
     			StringBuffer sb = new StringBuffer("");
     			
     			byte[] buffer = new byte[1024];
     			
     			int length;
     			while((length = fis.read(buffer)) != -1) {
     				sb.append(new String(buffer));
     			}
     			text = sb.toString();
     			textview.setText(text);
     			Toast.makeText(getApplicationContext(), "Text loaded from file",Toast.LENGTH_LONG).show();
     		} catch(Exception e) {
     			System.out.println("ERROR::::::::::::"+e);
     		}
     		
     	}
        
        
        
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	
    	try {
    		
    		FileOutputStream fos = openFileOutput(file,Context.MODE_PRIVATE);
    		fos.write(text.getBytes());
    		fos.close();
    		Toast.makeText(getApplicationContext(), "Txt stored", Toast.LENGTH_LONG).show();
    	} catch (Exception e) {
    		System.out.println("ERROR:::::::::::::"+e);
    	}
    }
    
 // Creates a new thread and downloads the text in that new thread
 	private class DownloadText extends AsyncTask <Void, Void, String> {
 		ProgressDialog pd;
 		
 		protected String doInBackground(Void...params) {
 			String response = null;
 			try {
 				HttpClient client = new DefaultHttpClient();
 				HttpGet get = new HttpGet(url);
 				HttpResponse res = client.execute(get);
 				HttpEntity ent = res.getEntity();
 				
 				if(ent != null) {
 					response = EntityUtils.toString(ent);
 					return response;
 				} else {
 					response = "OooOOops!";
 					return response;
 				}
 				
 			} catch(Exception e) {
 				System.out.println("ERROR::::::"+e);
 				return response;
 			}
 			
 		}
 		
 		@Override
 		protected void onPostExecute(String result) {
 			text = result;
 			pd.dismiss();
 			textview.setText(text);
 			
 		}
 		
 		@Override
 		protected void onPreExecute() {
 			pd = ProgressDialog.show(SaveData.this, "Loading...", "Data is Loading...");
 			
 		}
 		
 	}    

}
