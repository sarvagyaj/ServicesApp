package com.cmpe277.assignment4.servicesapp;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class DownloadDataService extends Service {

	private static FileOutputStream fileOutputStream;
	private final IBinder binder = new LocalBinder();

	public class LocalBinder extends Binder {
		DownloadDataService getService() {
			return DownloadDataService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public void downloadFiles(URL[] urls, String fileType) {

		Log.i("Directory",
				Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
		try {
			int fileNumber = 0;
			for (URL url : urls) {
				fileNumber++;
				InputStream inputStream = url.openStream();
				DataInputStream dataInputStream = new DataInputStream(
						inputStream);
				byte[] byteBuffer = new byte[1024];
				if (".pdf".equalsIgnoreCase(fileType)) {
					fileOutputStream = getBaseContext().openFileOutput(
							fileNumber + ".pdf", Context.MODE_PRIVATE);
					Log.i("PDFDownloadActivity", "Writing FileNumber "
							+ fileNumber + " from " + url.toString());
				} else if (".jpg".equalsIgnoreCase(fileType)) {
					fileOutputStream = getBaseContext().openFileOutput(
							fileNumber + ".jpg", Context.MODE_PRIVATE);
					Log.i("ImageDownloadActivity", "Writing  FileNumber "
							+ fileNumber + " from " + url.toString());
				} else if (".txt".equalsIgnoreCase(fileType)) {
					fileOutputStream = getBaseContext().openFileOutput(
							fileNumber + ".text", Context.MODE_PRIVATE);
					Log.i("TextDownloadActivity", "Writing  FileNumber "
							+ fileNumber + " from " + url.toString());
				} else {
					Log.i("Wrong input", "File extension is not supported");
				}
				int bytesRead = 0;
				while ((bytesRead = dataInputStream.read(byteBuffer)) > 0) {
					fileOutputStream.write(byteBuffer, 0, bytesRead);
				}
				fileOutputStream.close();

			}
		} catch (MalformedURLException e) {
			Log.e("Error", "Url is malformed", e);
		} catch (IOException e) {
			Log.e("Error", "IO Error occurred during file writing", e);
		} catch (SecurityException e) {
			Log.e("Error", "Security Error Occurred", e);
		}

	}
}
