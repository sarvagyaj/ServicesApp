package com.cmpe277.assignment4.servicesapp;

import java.net.MalformedURLException;
import java.net.URL;

import com.cmpe277.assignment4.servicesapp.DownloadDataService.LocalBinder;

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

public class TextDownloadActivity extends Activity implements
		View.OnClickListener {
	private Button bDownload;
	private EditText text1, text2, text3, text4, text5;
	DownloadDataService dataService;
	Intent intent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textdownload);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		bDownload = (Button) findViewById(R.id.bDownload);
		text1 = (EditText) findViewById(R.id.editView1);
		text2 = (EditText) findViewById(R.id.editView2);
		text3 = (EditText) findViewById(R.id.editView3);
		text4 = (EditText) findViewById(R.id.editView4);
		text5 = (EditText) findViewById(R.id.editView5);

		text1.setText("http://www.sjsu.edu/robots.txt");
		text2.setText("http://www.sjsu.edu/faculty/masucci/sportdeath.txt‎");
		text3.setText("http://www.met.sjsu.edu/wx/mosfile2.txt‎");
		text4.setText("http://as.sjsu.edu/asgov/board_info.txt‎");
		text5.setText("http://www.met.sjsu.edu/wx/mosfile.txt‎");

		bDownload.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		try {
			URL[] urls = { new URL(text1.getText().toString()),
					new URL(text2.getText().toString()),
					new URL(text3.getText().toString()),
					new URL(text4.getText().toString()),
					new URL(text5.getText().toString()) };

			dataService.downloadFiles(urls, ".txt");
			Log.i("TextDownloadActivity_AutoCreate", "Download Complete");
			Toast.makeText(this, "TextDownloadActivity : Download Complete",
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
		Toast.makeText(getBaseContext(), "Download Service Started",
				Toast.LENGTH_SHORT).show();
		Log.i("TextDownloadActivity_AutoCreate", "Download Service Started");

	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(connection);
		Toast.makeText(getBaseContext(), "Download Service Stopped",
				Toast.LENGTH_SHORT).show();
		Log.i("TextDownloadActivity_AutoCreate", "Download Service Stopped");

	}

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName,
				IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			dataService = binder.getService();
			Toast.makeText(getBaseContext(), "Download Service Connected",
					Toast.LENGTH_SHORT).show();
			Log.i("TextDownloadActivity_AutoCreate",
					"Download Service Connected");
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			Toast.makeText(getBaseContext(), "Download Service Disconnected",
					Toast.LENGTH_SHORT).show();
			Log.i("TextDownloadActivity_AutoCreate",
					"Download Service Disconnected");
		}
	};

}
