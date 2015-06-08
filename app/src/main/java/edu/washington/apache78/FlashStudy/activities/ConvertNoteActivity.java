package edu.washington.apache78.FlashStudy.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteParser;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteParserException;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteTxtParser;
import edu.washington.apache78.FlashStudy.models.*;

public class ConvertNoteActivity extends ActionBarActivity {

	private EditText contentField;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert_note);
		Intent receivedContent  = getIntent();

		String noteContent = receivedContent.getExtras().getString("noteContent");
		contentField = (EditText) findViewById(R.id.noteContentBody);
		contentField.setText(noteContent);

		try {
			NoteParser parser = new NoteTxtParser();
			NoteParser.SuccessCode code = parser.parse(noteContent);
			Note note = parser.getNote();

			if(code == NoteParser.SuccessCode.OK) {
				Toast.makeText(this, "File successfully parsed!", Toast.LENGTH_SHORT).show();
			} else if(code == NoteParser.SuccessCode.PARTIAL) {
				Toast.makeText(this, "Although there were some error, file has been successfully parsed!", Toast.LENGTH_SHORT).show();
			}
		} catch(NoteParserException e) {
			Toast.makeText(this, "Parsing failed", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_convert_note, menu);
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
