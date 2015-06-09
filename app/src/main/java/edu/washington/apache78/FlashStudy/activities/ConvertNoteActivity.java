package edu.washington.apache78.FlashStudy.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.models.Card;

public class ConvertNoteActivity extends ActionBarActivity {

    TextView contentField;
    EditText noteName;
    Button getFlashCards;

    String filenameString;
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
        final String format = ".json";
        filenameString = noteName.getText().toString();
        jsonString = NoteObjectJSON.toString();


        getFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    File SDCardRoot = Environment.getExternalStorageDirectory();
                    File myFile = new File(SDCardRoot+"/FlashStudy","data.json");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fOut);
                    myOutWriter.append(jsonString);
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(),
                            "Done writing SD",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }





            }
        });





        //Toast.makeText(this, String.valueOf(textStr.length), Toast.LENGTH_LONG).show();


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
