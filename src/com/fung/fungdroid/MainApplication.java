package com.fung.fungdroid;

import com.fung.droid.http.HttpCache;

import android.app.Application;

public class MainApplication extends  Application{
	private HttpCache httpCache ;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		if(httpCache==null)
			{
				httpCache= new HttpCache(this);
			}
	}

	
}
