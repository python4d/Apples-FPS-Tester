package com.python4d.libGDXGame;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;



public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		//cfg.useGL20 = true;
		//initialize(new libGDXGame(), cfg);
		
		//admob

		View gameView = initializeForView(new libGDXGame(), true);
		RelativeLayout layout = new RelativeLayout(this);
	    layout.addView(gameView);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		AdView adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-1008481061910472/5992717317");
	    adView.setAdSize(AdSize.BANNER); // Put in your secret key here
	    adView.loadAd(new AdRequest.Builder().build());
	    RelativeLayout.LayoutParams adParams = 
	            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
	                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.addView(adView, adParams);
        setContentView(layout);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

}