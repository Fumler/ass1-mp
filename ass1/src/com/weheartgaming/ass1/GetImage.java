package com.weheartgaming.ass1;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;

public class GetImage extends Activity {
	ImageView imageview;
	String imgUrl = "http://hig.no/var/ezwebin_site/storage/images/ansatte/avdeling_for_informatikk_og_medieteknikk/simon_j_r_mccallum/270018-7-nor-NO/simon_j_r_mccallum.jpg";
	
	// Runs when the activity is created
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_image);
		
		// Checks if the user is connected to the internet
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		// If connected, show the picture
		if(networkInfo != null && networkInfo.isConnected()) {
			imageview = (ImageView) findViewById(R.id.image);
			new DownloadImage().execute(imgUrl);
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
	private class DownloadImage extends AsyncTask <String, Void, Bitmap> {
		protected Bitmap doInBackground(String...url) {
			return loadImage(url[0]);
		}
		
		protected void onPostExecute(Bitmap result) {
			imageview.setImageBitmap(result);
		}
	}
	
	// Connect to the file trough http and put in an inputstream, then convert it to bitmap
	public Bitmap loadImage(final String s) {
		Bitmap bm = null;
		try {
			URL url = new URL(s);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			bm = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			// Get logged message if it fails
			System.out.println("Failed to get image: " + e);
			e.printStackTrace();
		}
		return bm;
	}

	
}


                                                          