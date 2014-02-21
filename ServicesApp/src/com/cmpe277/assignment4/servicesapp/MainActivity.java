package com.cmpe277.assignment4.servicesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button bClose, bPdf, bText, bImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bPdf = (Button) findViewById(R.id.bPdf);
		bPdf.setOnClickListener(this);

		bImg = (Button) findViewById(R.id.bImg);
		bImg.setOnClickListener(this);

		bText = (Button) findViewById(R.id.bText);
		bText.setOnClickListener(this);

		bClose = (Button) findViewById(R.id.bClose);
		bClose.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bPdf:
			Intent intent = new Intent(
					"com.cmpe277.assignment4.servicesapp.PDFDownloadActivity");
			startActivity(intent);
			break;

		case R.id.bImg:
			Intent intent1 = new Intent(
					"com.cmpe277.assignment4.servicesapp.ImageDownloadActivity");
			startActivity(intent1);
			break;

		case R.id.bText:
			Intent intent2 = new Intent(
					"com.cmpe277.assignment4.servicesapp.TextDownloadActivity");
			startActivity(intent2);
			break;

		case R.id.bClose:
			finish();
			break;

		default:
			break;
		}

	}

}
