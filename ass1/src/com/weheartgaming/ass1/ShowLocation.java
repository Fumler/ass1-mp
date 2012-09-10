package com.weheartgaming.ass1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class ShowLocation extends Activity {
	private ProgressDialog progressDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_location);
        
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	runDialog(5);
        	Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        	
        	
        	if(location != null)
        	{
        		location.getLatitude();
        		location.getLongitude();
        	}
        	
        	String coord = "geo:"+ location.getLatitude() + "," + location.getLongitude();
        	
        	
        	//String uri = "geo:"+ lat + "," + lon;
        	

        	startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(coord)));

        	
        	
        	//Toast.makeText(getApplicationContext(), "GPS is turned on!", Toast.LENGTH_LONG).show();
        } else {
        	Toast.makeText(getApplicationContext(), "GPS is turned off!", Toast.LENGTH_LONG).show();
        	
        	startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        	
        	finish();
        }
    }
    
    private void runDialog(final int seconds)
    {
        	progressDialog = ProgressDialog.show(this, "Please wait....", "Updating location");

        	new Thread(new Runnable(){
        		public void run(){
        			try {
        				Thread.sleep(seconds * 1000);
    					progressDialog.dismiss();
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
        		}
        	}).start();
    }

}

