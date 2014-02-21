package com.cmpe277.assignment4.servicesapp;

import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmpe277.assignment4.servicesapp.DownloadDataService.LocalBinder;

public class ImageDownloadActivity extends Activity implements
		View.OnClickListener {
	private Button bDownload;
	private EditText image1, image2, image3;
	DownloadDataService dataService;
	Intent intent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imagedownload);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		bDownload = (Button) findViewById(R.id.bDownload);
		image1 = (EditText) findViewById(R.id.editView1);
		image2 = (EditText) findViewById(R.id.editView2);
		image3 = (EditText) findViewById(R.id.editView3);

		image1.setText("http://blogs.sjsu.edu/today/files/2014/02/Bloom_homescreen-2ky67ll.jpg");
		image2.setText("http://blogs.sjsu.edu/today/files/2013/11/Today_Inpost_Gray_112513-1m1hxzq.jpg");
		image3.setText("http://www.sjsu.edu/sjsuhome/pics/julia-curry-rodriguez-021314.jpg");
		bDownload.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		try {
			URL[] urls = { new URL(image1.getText().toString()),
					new URL(image2.getText().toString()),
					new URL(image3.getText().toString()) };

			dataService.downloadFiles(urls, ".jpg");
			Log.i("ImageDownloadActivity_AutoCreate", "Download Complete");
			Toast.makeText(this, "ImageDownloadActivity : Download Complete",
					Toast.LENGTH_SHORT).show();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onStart() {
		super.onStart();
		intent = new Intent(this, DownloadDataService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		//	startService(intent);
		Toast.makeText(getBaseContext(), "Download Service Strated",
				Toast.LENGTH_SHORT).show();
		Log.i("ImageDownloadActivity_AutoCreate", "Download Service Strated");

	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(connection);
		Toast.makeText(getBaseContext(), "Download Service Stopped",
				Toast.LENGTH_SHORT).show();
		Log.i("ImageDownloadActivity_AutoCreate", "Download Service Stopped");

	}

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName,
				IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			dataService = binder.getService();
			Toast.makeText(getBaseContext(), "Download Service Connected",
					Toast.LENGTH_SHORT).show();
			Log.i("ImageDownloadActivity_AutoCreate",
					"Download Service Connected");
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			Toast.makeText(getBaseContext(), "Download Service Disconnected",
					Toast.LENGTH_SHORT).show();
			Log.i("ImageDownloadActivity_AutoCreate",
					"Download Service Disconnected");
		}
	};
}
