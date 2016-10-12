package com.sticky.game;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.sticky.game.StickyDemo;

import java.util.logging.Handler;

public class AndroidLauncher extends AndroidApplication implements AdHandler{
	private static final String TAG = "AndroidLauncher";
	private final int SHOW_BANNERS =1;
	private final int HIDE_BANNERS =0;
	protected AdView adView;
	protected AdView adView2;


	android.os.Handler handler = new android.os.Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case SHOW_BANNERS:
					adView.setVisibility(View.VISIBLE);
					adView2.setVisibility(View.VISIBLE);
					break;
				case HIDE_BANNERS:
					adView.setVisibility(View.GONE);
					adView2.setVisibility(View.GONE);
					break;
			}
		}
	};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new StickyDemo(this), config);
        layout.addView(gameView);

		adView = new AdView(this);
		adView2 = new AdView(this);

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad Loaded");
			}


		});
		adView2.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad Loaded");
			}});

		adView.setAdSize(AdSize.SMART_BANNER);
		adView2.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7317989219230415/5025117280");
		adView2.setAdUnitId("ca-app-pub-7317989219230415/7853512489");

		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("B93A865B75CB8E8F6D1B9B40B39DF2BA");
		RelativeLayout.LayoutParams adParams2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


		layout.addView(adView, adParams);
		layout.addView(adView2, adParams2);
		adView.loadAd(builder.build());
		adView2.loadAd(builder.build());
		setContentView(layout);

	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_BANNERS : HIDE_BANNERS);
	}


}
