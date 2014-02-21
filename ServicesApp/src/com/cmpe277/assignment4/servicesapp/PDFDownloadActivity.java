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

public class PDFDownloadActivity extends Activity implements
		View.OnClickListener {

	private Button bDownload;
	private EditText pdf1, pdf2, pdf3, pdf4, pdf5;
	DownloadDataService dataService;
	Intent intent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfdownload);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		bDownload = (Button) findViewById(R.id.bDownload);
		pdf1 = (EditText) findViewById(R.id.editView1);
		pdf2 = (EditText) findViewById(R.id.editView2);
		pdf3 = (EditText) findViewById(R.id.editView3);
		pdf4 = (EditText) findViewById(R.id.editView4);
		pdf5 = (EditText) findViewById(R.id.editView5);

		pdf1.setText("http://www.sjsu.edu/map/docs/SJSU_campus_map.pdf");
		pdf2.setText("http://www.sjsu.edu/wll/2013-14_AY_Calendar.pdf");
		pdf3.setText("http://www.sjsu.edu/registrar/docs/CR_NC_Audit.pdf");
		pdf4.setText("http://www.sjsu.edu/registrar/docs/Instructor_Drops_Procedure.pdf");
		pdf5.setText("http://www.sjsu.edu/isystems/docs/SJSU_campus_map.pdf");

		bDownload.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		try {
			URL[] urls = { new URL(pdf1.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString()),
					new URL(pdf2.getText().toString()),
					new URL(pdf3.getText().toString()),
					new URL(pdf4.getText().toString()),
					new URL(pdf5.getText().toString())};

			dataService.downloadFiles(urls, ".pdf");
			Log.i("PDFDownloadActivity_AutoCreate", "Download Complete");
			Toast.makeText(this, "PDFDownloadActivity : Download Complete",
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
		bindService(intent, connection, Context.BIND_AUTO_CREATE); //Context.BIND_ADJUST_WITH_ACTIVITY
		//startService(intent);
		Toast.makeText(getBaseContext(), "Download Service Started",
				Toast.LENGTH_SHORT).show();
		Log.i("PDFDownloadActivity_AutoCreate", "Download Service Started");

	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(connection);
		Toast.makeText(getBaseContext(), "Download Service Stopped",
				Toast.LENGTH_SHORT).show();
		Log.i("PDFDownloadActivity_AutoCreate", "Download Service Stopped");

	}

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName,
				IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			dataService = binder.getService();
			Toast.makeText(getBaseContext(), "Download Service Connected",
					Toast.LENGTH_SHORT).show();
			Log.i("PDFDownloadActivity_AutoCreate",
					"Download Service Connected");
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			Toast.makeText(getBaseContext(), "Download Service Disconnected",
					Toast.LENGTH_SHORT).show();
			Log.i("PDFDownloadActivity_AutoCreate",
					"Download Service Disconnected");
		}
	};

}
