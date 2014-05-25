package com.inhuasoft.autoregsip;

import java.io.IOException;
//import java.net.CookieStore;
import org.apache.http.client.CookieStore;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener,
		OnClickListener {

	Button btnlogin,btnadd;

	//EditText editText;
	SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	editText = (EditText) findViewById(R.id.txtedit);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		btnlogin = (Button) findViewById(R.id.button1);
		btnlogin.setOnClickListener(this);
		btnadd = (Button) findViewById(R.id.adduser);
		btnadd.setOnClickListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		StringBuilder sb = new StringBuilder();
		sb.append("  x �� ");
		sb.append(values[0]);
		sb.append("  y �� ");
		sb.append(values[1]);
		sb.append("  z �� ");
		sb.append(values[2]);

		// editText.setText(sb.toString());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	
	class MyThread1 extends Thread {  
       
        public void run() {  
        	String login_ok ="8010210925-35110-444-10984-4122-322463124";
    		String httpUrl = "http://sip.inhuasoft.cn/tools/users/user_management/user_management.php?action=add_verify&id=";
    		HttpPost request = new HttpPost(httpUrl);
    		HttpClient httpClient = new DefaultHttpClient();
    		List<NameValuePair> params = new ArrayList<NameValuePair>();
    		params.add(new BasicNameValuePair("uname", "user2"));
    		params.add(new BasicNameValuePair("email", "371816210@163.com"));
    		params.add(new BasicNameValuePair("alias", "user1"));
    		params.add(new BasicNameValuePair("domain", "115.28.9.71"));
    		params.add(new BasicNameValuePair("alias_type", "dbaliases"));
    		params.add(new BasicNameValuePair("passwd", "user2"));
    		params.add(new BasicNameValuePair("confirm_passwd", "user2"));
    		HttpResponse response;
    		myApplication   appCookie = ((myApplication)getApplication());   
    		((AbstractHttpClient) httpClient).setCookieStore(appCookie.getCookie());
			// appCookie.setCookie(cookies);
    		try {
    			HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
    			request.setEntity(entity);
    			response = httpClient.execute(request);


    			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
    				String str = EntityUtils.toString(response.getEntity());
    			    String strmd5 = MD5.getMD5(str);
    				//System.out.println("response:" + str);
    				if(str.contains("is already a valid user"))
    				{
    					System.out.println("=================is already a valid user");
    				}
    				if(str.contains("Total Records"))
    				{
    					System.out.println("=================Total Records:");
    				}
    				System.out.println(" login in add user");
    			  /* if (strmd5.equals(login_ok)) {
   
    					System.out.println("��¼�ɹ���");
    					CookieStore cookies=((AbstractHttpClient)httpClient).getCookieStore();
    					((AbstractHttpClient) httpClient).setCookieStore(cookies);
    					//Toast.makeText(getApplicationContext(), " login success ", Toast.LENGTH_SHORT).show();
    				} else {
    					// �û���¼ʧ��
    					System.out.println("��¼ʧ�ܣ�");
    					//Toast.makeText(getApplicationContext(), " login fail ", Toast.LENGTH_SHORT).show();
    				}*/
    			} else {
    				System.out.println("����ʧ�ܣ�");
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    }  
	
	
	
	
	class MyThread extends Thread {  
	       
        public void run() {  
        	String login_ok ="8010210925-35110-444-10984-4122-322463124";
    		String httpUrl = "http://sip.inhuasoft.cn/login.php";
    		HttpPost request = new HttpPost(httpUrl);
    		HttpClient httpClient = new DefaultHttpClient();
    		List<NameValuePair> params = new ArrayList<NameValuePair>();
    		params.add(new BasicNameValuePair("name", "admin"));
    		params.add(new BasicNameValuePair("password", "admin"));
    		HttpResponse response;

    		try {
    			HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
    			request.setEntity(entity);
    			response = httpClient.execute(request);

    			// ����״̬Ϊ200����÷��صĽ��
    			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
    				String str = EntityUtils.toString(response.getEntity());
    			    String strmd5 = MD5.getMD5(str);
    				System.out.println("response:" + strmd5);
    				if (strmd5.equals(login_ok)) {
    					// �û���¼�ɹ�
    					System.out.println("��¼�ɹ���");
    	
    					 CookieStore cookies=((AbstractHttpClient)httpClient).getCookieStore();
    				     myApplication   appCookie = ((myApplication)getApplication());   
    					//((AbstractHttpClient) httpClient).setCookieStore(cookies);
    					 appCookie.setCookie(cookies);
    					//Toast.makeText(getApplicationContext(), " login success ", Toast.LENGTH_SHORT).show();
    				} else {
    					// �û���¼ʧ��
    					System.out.println("��¼ʧ�ܣ�");
    					//Toast.makeText(getApplicationContext(), " login fail ", Toast.LENGTH_SHORT).show();
    				}
    			} else {
    				System.out.println("����ʧ�ܣ�");
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    } 
	
	
	
	
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.button1)//login
		{
		  MyThread myThread = new MyThread();
	      myThread.start();
		}
		if (v.getId() == R.id.adduser) {
		  MyThread1 myThread1 = new MyThread1();
		  myThread1.start();
		}
	}

}
