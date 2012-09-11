package com.weheartgaming.ass1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class ShowLocation extends Activity {
	public String coords = null;
	private double lat = 0;
	private double lon = 0;
	LocationManager lm;
	LocationListener ll;
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_location);
        
    	lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	ll = new MyLocationListener();
    	
        final boolean gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        
        
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        
        
        if(!gpsEnabled) {
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        	alertDialog.setTitle("GPS is disabled!");
        	alertDialog.setMessage("Open settings?");
        	alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
        	   public void onClick(DialogInterface dialog, int which) {
        		   startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
       				// not sure how to refresh the activity, so finish this activity and go back to main
       				//  when the user user clicks back from the settings page
       				finish();
        	   }
        	});
        	alertDialog.setIcon(R.drawable.ic_launcher);
        	alertDialog.show();
        } else {
        	try {
        		
        		double latitude = getLatitude();
        		double longitude = getLongitude();
        	Uri uri = Uri.parse("geo:"+latitude+","+longitude);
        	//Uri test = Uri.parse("geo:37.188331,-103.007812?z=10");
        	
        	Toast.makeText(getApplicationContext(), "Location is: "+uri, Toast.LENGTH_LONG).show();
        	startActivity(new Intent(Intent.ACTION_VIEW, uri));
        	
        	finish();

        	} catch(Exception e) {
        		System.out.println("derp:::: "+e);
        	}
        	

        }

    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	
    	lm.removeUpdates(ll);
    }
    
    public double getLatitude() {
    	return this.lat;
    }
    
    public double getLongitude() {
    	return this.lon;
    }

    public class MyLocationListener implements LocationListener {
    	
    	public void onLocationChanged(Location loc){
    		loc.getLatitude();
    		loc.getLongitude();
    		
    		if(loc != null) {
    			double latit = loc.getLatitude();
    			double longi = loc.getLongitude();
    			
    			
    			synchronized (this) {
    			if(longi != 0 && latit != 0) {
        			lat = latit;
        			lon = longi;
    			}
    			
    			}

    			
    		}
 
    		
    		
    		
//    		String latString = String.valueOf(lat);
//    		String lonString = String.valueOf(lon);
//    		
//    		coords = "geo:" + latString + "," + lonString;
    		
    		
    	}
    	
    	
    	public void onProviderDisabled(String provider) {
    		Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();

    	}
    	
    	
        public void onProviderEnabled(String provider)
        {

          Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }

       
        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }
    }

}



