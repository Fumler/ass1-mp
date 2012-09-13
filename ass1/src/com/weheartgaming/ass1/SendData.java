package com.weheartgaming.ass1;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.widget.Toast;

public class SendData extends Activity {

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
     		new SendGet().execute();
     	} 
     	// else notify user that he is not connected, and open wireless and network settings
     	else { 
             AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
	private class SendGet extends AsyncTask <Void, Void, String> {
		
		ProgressDialog pd;
	
		@Override
		protected String doInBackground(Void...params) {
	        try  {
	        	
	        	HttpClient client = new DefaultHttpClient();
	        	String getURL = "http://gtl.hig.no/mobile/logging.php?user=Fredrik&data=Stay%20a%20while%20and%20listen!";
	        	HttpGet get = new HttpGet(getURL);
	        	HttpResponse res = client.execute(get);
	        	
	        	if(res != null) {
	        		Toast.makeText(getApplicationContext(), "SHIT IS NOT WORKING", Toast.LENGTH_LONG).show();
	        	}


	        } catch(Exception e) {
	        	System.out.println("ERROR:::::::::" + e);
	        }
			
			return null;
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
