package edu.washington.apache78.FlashStudy.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.io.*;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NotesManager;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteParser;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteParserException;
import edu.washington.apache78.FlashStudy.includes.parsers.NoteTxtParser;
import edu.washington.apache78.FlashStudy.models.*;

public class ConvertNoteActivity extends ActionBarActivity {
	private TextView contentField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert_note);
		Intent receivedContent  = getIntent();

		final String noteContent = receivedContent.getExtras().getString("noteContent");
		contentField = (TextView)findViewById(R.id.noteContentBody);
		contentField.setText(noteContent);
/*import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.models.Card;

public class ConvertNoteActivity extends ActionBarActivity {

    TextView contentField;
    EditText noteName;
    Button getFlashCards;

    String filename;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_note);
        Intent receivedContent  = getIntent();
        String receivedTxt = receivedContent.getExtras().getString("noteContent");

        contentField = (TextView) findViewById(R.id.noteContentBody);
        noteName = (EditText) findViewById(R.id.noteName);
        getFlashCards = (Button) findViewById(R.id.getFlashCards);

        contentField.setText(receivedTxt);

        String textStr[] = receivedTxt.split("[\r\n][\n]+");

        ArrayList<Card> cardList = new ArrayList<>();

        for(String card:textStr){

            String cardLineString = card.replaceAll("\\r\\n|\\r|\\n", " ");
            String[] cardData=cardLineString.split("\\:", 2);
            Card cardObject = new Card(cardData[0],cardData[1]);
            cardList.add(cardObject);
            //Log.i("===",cardObject.getTerm()+": "+cardObject.getDefinition());

        }


        //Turning cardList into JSON object
        JSONObject NoteObjectJSON = new JSONObject();
        try {

            NoteObjectJSON.put("noteName",noteName.getText());

            JSONArray jsonArray = new JSONArray();


            for(Card singleCard:cardList){
                JSONObject cardObjectJSON = new JSONObject();
                cardObjectJSON.put("term", singleCard.getTerm());
                cardObjectJSON.put("definition", singleCard.getDefinition());
                jsonArray.put(cardObjectJSON);

            }
            NoteObjectJSON.put("cards", jsonArray);
            Log.i("===", NoteObjectJSON.toString());



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Saving JSONobject string internally

        filename = noteName.getText().toString();
        jsonString = NoteObjectJSON.toString();
        FileOutputStream outputStream;

        getFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createCacheFile(ConvertNoteActivity.this, filename, jsonString);
                Toast.makeText(ConvertNoteActivity.this, "Successfully saved "+filename, Toast.LENGTH_LONG).show();
            }
        });





        //Toast.makeText(this, String.valueOf(textStr.length), Toast.LENGTH_LONG).show();*/

        ((Button)findViewById(R.id.getFlashCards)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    NoteParser parser = new NoteTxtParser();
                    NoteParser.SuccessCode code = parser.parse(noteContent);
                    Note note = parser.getNote();
                    note.title = ((EditText)findViewById(R.id.noteName)).getText().toString();
                    NotesManager.getInstance().addNote(note);

                    if(code == NoteParser.SuccessCode.OK) {
                        Toast.makeText(ConvertNoteActivity.this, "File successfully parsed!", Toast.LENGTH_SHORT).show();
                    } else if(code == NoteParser.SuccessCode.PARTIAL) {
                        Toast.makeText(ConvertNoteActivity.this, "Although there were some error, file has been successfully parsed!", Toast.LENGTH_SHORT).show();
                    }
                } catch(NoteParserException e) {
                    Toast.makeText(ConvertNoteActivity.this, "Parsing failed", Toast.LENGTH_SHORT).show();
                }


                ConvertNoteActivity.this.finish();
                //createCacheFile(ConvertNoteActivity.this, filename, jsonString);
                //Toast.makeText(ConvertNoteActivity.this, "Successfully saved "+filename, Toast.LENGTH_LONG).show();
            }
        });


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

    public File createCacheFile(Context context, String fileName, String json) {
        File cacheFile = new File(context.getFilesDir(), fileName);
        try {
            FileWriter fw = new FileWriter(cacheFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();

            // on exception null will be returned
            cacheFile = null;
        }

        return cacheFile;
    }

    public String readFile(File file) {
        String fileContent = "";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((currentLine = br.readLine()) != null) {
                fileContent += currentLine + '\n';
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();

            // on exception null will be returned
            fileContent = null;
        }
        return fileContent;
    }
}
