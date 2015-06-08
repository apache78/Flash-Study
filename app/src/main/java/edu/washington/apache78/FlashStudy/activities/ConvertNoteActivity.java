package edu.washington.apache78.FlashStudy.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import edu.washington.apache78.FlashStudy.R;

public class ConvertNoteActivity extends ActionBarActivity {

    private EditText contentField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_note);
        Intent receivedContent  = getIntent();
        String receivedTxt = receivedContent.getExtras().getString("noteContent");

        contentField = (EditText) findViewById(R.id.noteContentBody);
        contentField.setText(receivedTxt);

        String textStr[] = receivedTxt.split("[\r\n][\n]+");

        for(int i=0; i<textStr.length; i++){
            Log.i("====", textStr[i]);
        }


        Toast.makeText(this,String.valueOf(textStr.length), Toast.LENGTH_LONG).show();


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
    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
}
