package com.weheartgaming.ass1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class GetData extends Activity {
	TextView textview;
	String url = "http://www.hum.re/derp.txt";
	
	// Runs when the activity is created
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_data);
		
		textview = new TextView(this);
		
		// Checks if the user is connected to the internet
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		// If connected, show the picture
		if(networkInfo != null && networkInfo.isConnected()) {
			textview = (TextView) findViewById(R.id.text);
			new DownloadText().execute();
			getVideo();
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
			textview.setText(result);
			pd.dismiss();
		}
		
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(GetData.this, "Loading...", "Data is Loading...");
			
		}
		
	}
	
	public void getVideo() {
		String path = "http://hum.re/filar/gangnam.mp4";
		VideoView video;
		MediaController mc;
		
		Uri uri = Uri.parse(path);
		
		video = (VideoView) findViewById(R.id.video);
		video.setVideoURI(uri);
		
		mc = new MediaController(this);
		mc.setMediaPlayer(video);
		video.setMediaController(mc);
		video.requestFocus();
	}
	
}


                                                          