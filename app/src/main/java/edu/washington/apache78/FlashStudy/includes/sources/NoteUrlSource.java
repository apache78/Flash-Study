package edu.washington.apache78.FlashStudy.includes.sources;

import java.io.*;
import java.net.*;

/**
 * Created by Stanley on 6/5/2015.
 */
public class NoteUrlSource extends NoteSource {
	private String url;

	public NoteUrlSource(String url) {
		this.url = url;
	}

	public void fetch(final Callback cb) {
		Runnable asyncDownload = new Runnable() {
			public void run() {
				InputStream is = null;
				try {
					//create stream
					URL u = new URL(url);
					is = u.openStream();
					DataInputStream dis = new DataInputStream(is);

					//download data
					StringBuilder sb = new StringBuilder();
					int len;
					byte[] buffer = new byte[1024];
					while ((len = dis.read(buffer)) > 0) {
						sb.append(new String(buffer), 0, len);
					}

					//call callback
					cb.success(sb.toString());
				} catch (MalformedURLException mue) {
					cb.failed(ErrorCode.INVALID_PATH);
				} catch (IOException ioe) {
					cb.failed(ErrorCode.CONTENT_UNAVAILABLE);
				} finally {
					try {
						is.close();
					} catch (Exception e) { }
				}
			}
		};

		Thread t = new Thread(asyncDownload);
		t.start();
	}
}
