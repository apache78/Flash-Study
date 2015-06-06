package edu.washington.apache78.FlashStudy.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.*;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteParser;
import edu.washington.apache78.FlashStudy.includes.sources.*;
import edu.washington.apache78.FlashStudy.models.Note;


public class UploadActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);

		((Button)findViewById(R.id.downloadBtn)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NoteProvider provider = new NoteProvider(new NoteUrlSource("http://54.219.16.236/vocab.txt"));
				provider.fetch(new NoteProvider.Callback() {
					@Override
					public void sourceFailed(final NoteSource.ErrorCode errorCode) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(UploadActivity.this, NoteSource.getResultDescription(errorCode), Toast.LENGTH_SHORT).show();
							}
						});
					}

					@Override
					public void parserFailed() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(UploadActivity.this, "We're unable to parse the note file!", Toast.LENGTH_SHORT).show();
							}
						});
					}

					@Override
					public void success(Note note, NoteParser.SuccessCode parserResult) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(UploadActivity.this, "Works!", Toast.LENGTH_SHORT).show();
							}
						});
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_upload, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
