package com.inhuasoft.autoregsip;

import org.apache.http.client.CookieStore;

import android.app.Application;

public class myApplication extends Application {

	 private CookieStore cookies;  
	    public CookieStore getCookie(){   
	        return cookies;
	    }
	    public void setCookie(CookieStore cks){
	        cookies = cks;
	    }
	
}
