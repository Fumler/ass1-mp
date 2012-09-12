package com.weheartgaming.ass1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;

public class SendData extends Activity {
	public String url = "http://gtl.hig.no/mobile/logging.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data);
        
        // Checks if the user is connected to the internet
     	ConnectivityManager connMgr = (ConnectivityManager)
     			getSystemService(Context.CONNECTIVITY_SERVICE);
     	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
     		
     	// If connected, show the picture
     	if(networkInfo != null && networkInfo.isConnected()) {
     		// do stuff
     		new SendGet().execute(url);
     	} 
     	// else notify user that he is not connected, and open wireless and network settings
     	else { 
             AlertDialog alertDialog = new AlertDialog.Builderthis).create();
             alertDialog.setTitle("Internet is disabled!");
             alertDialog.setMessage("Open settings?");
             alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {

            	 public void onClick(DialogInterface dialog, int which) {
             		  startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
             		   
            			// not sure how to refresh the activity, so finish this activity and go back to main
            			//  when the user user clicks back from the settings page
            			finish();
             	  }
             });
             alertDialog.setIcon(R.drawable.ic_launcher);
             alertDialog.show();
     	}
    }
        
    
	// Creates a new thread and downloads the image in that new thread
	private class SendGet extends AsyncTask <String, Void, String> {
		
		ProgressDialog pd;
	
		@Override
		protected String doInBackground(String...doUrl) {
			
			HttpClient httpclient = new DefaultHttpClient();
	        
	        HttpPost httppost = new HttpPost(doUrl[0]);
	        
	        String send = "Stay a while, and listen!";
	        
	        try  {
	        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        	nameValuePairs.add(new BasicNameValuePair("user", "Fredrik"));
	        	nameValuePairs.add(new BasicNameValuePair("data", send));
	        	
	        	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        	
	        	HttpResponse reponse = httpclient.execute(httppost);
	        	
	        	
	        } catch(Exception e) {
	        	System.out.println("ERROR:::::::::" + e);
	        }
			
			return doUrl[0];
		}
		
		@Override
		protected void onPostExecute(String result) {
			pd.dismiss();
			
			
		}
		
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(SendData.this, "Loading...", "Data is Loading...");
		}
	}



}
