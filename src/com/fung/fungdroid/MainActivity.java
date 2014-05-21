package com.fung.fungdroid;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.fung.droid.http.HttpCache;
import com.fung.droid.http.HttpCache.HttpCacheListener;
import com.fung.droid.http.HttpResponse;
import com.fung.droid.util.JSONUtils;
import com.fung.droid.util.StringUtils;

public class MainActivity extends Activity {
	HttpCache httpCache ;
	 
	//private HttpCache httpCache = CacheManager.getHttpCache(MainActivity.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  httpCache = new HttpCache(MainActivity.this);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//http://www.trinea.cn/android/android-http-cache/
				httpCache.httpGet("http://www.800pharm.com/shop/m/cgList.html", new HttpCacheListener() {
					 
				    protected void onPreGet() {
				        // do something like show progressBar before httpGet, runs on the UI thread 
				    }
				 
				    protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
				        // do something like show data after httpGet, runs on the UI thread 
				        if (httpResponse != null) {
				            // get data success
				           // setText(httpResponse.getResponseBody());
				        	
				        	 if (StringUtils.isEmpty(httpResponse.getResponseBody())) {
				        		 Log.d(CNSTS.TAG,"empty result..");
				             }else
				             {
				            	 
				            	 try {
				            		 JSONArray  array=new JSONArray(httpResponse.getResponseBody());
				            		 for(int i=0 ; i< array.length();i++)
				            		 {
				            			 Log.d(CNSTS.TAG,JSONUtils.getString(array.getJSONObject(i), "cg_id", "0")+
				            					 JSONUtils.getString(array.getJSONObject(i), "title", "t")
				            					 );
				            			 
				            		 }
				            		
					             } catch (JSONException e) {
					                     e.printStackTrace();
				               }
				             }
				        	
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
