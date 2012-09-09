package com.weheartgaming.ass1;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class GetImage extends Activity {
	ImageView imageview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_image);
		
		imageview = (ImageView) findViewById(R.id.image);
		Drawable drawable = LoadImagesFromWeb("http://hig.no/var/ezwebin_site/storage/images/ansatte/avdeling_for_informatikk_og_medieteknikk/simon_j_r_mccallum/270018-7-nor-NO/simon_j_r_mccallum.jpg");
		imageview.setImageDrawable(drawable);

	}
	private Drawable LoadImagesFromWeb(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
			
		} catch(Exception e) {
			System.out.println("Exc="+e);
			return null;
		}
	}
	
}
                                                          