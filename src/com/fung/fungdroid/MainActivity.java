package com.fung.fungdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.fung.droid.http.HttpCache;
import com.fung.droid.http.HttpCache.HttpCacheListener;
import com.fung.droid.http.HttpResponse;

public class MainActivity extends Activity {

	private HttpCache httpCache = new HttpCache(MainActivity.this);
	//private HttpCache httpCache = CacheManager.getHttpCache(MainActivity.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//http://www.trinea.cn/android/android-http-cache/
				httpCache.httpGet("www.baidu.com", new HttpCacheListener() {
					 
				    protected void onPreGet() {
				        // do something like show progressBar before httpGet, runs on the UI thread 
				    }
				 
				    protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
				        // do something like show data after httpGet, runs on the UI thread 
				        if (httpResponse != null) {
				            // get data success
				           // setText(httpResponse.getResponseBody());
				        	Log.d("tag",httpResponse.getResponseBody());
				        } else {
				            // get data fail
				        }
				    }
				});
				//Intent intent = new Intent(MainActivity.this, AdapterViewActivity.class);
				//startActivity(intent);
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
