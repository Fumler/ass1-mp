package com.weheartgaming.ass1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SendData extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data);
        
        HttpClient httpclient = new DefaultHttpClient();
        
        HttpPost httppost = new HttpPost("http://gtl.hig.no/logging.php");
        
        String send = "Stay a while, and listen!";
        
        try  {
        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        	nameValuePairs.add(new BasicNameValuePair("action", send));
        	
        	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        	
        	ResponseHandler<String> responseHandler = new BasicResponseHandler();
        	String response = httpclient.execute(httppost, responseHandler);
        	
        	String reverseString = response;
        	Toast.makeText(this, "reponse" + reverseString, Toast.LENGTH_LONG).show();
        	
        } catch (ClientProtocolException e) {
        	Toast.makeText(this, "CPE Exception " + e.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
        	Toast.makeText(this, "IO Exception "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }



}
