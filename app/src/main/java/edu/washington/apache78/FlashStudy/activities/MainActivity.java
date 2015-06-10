package edu.washington.apache78.FlashStudy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NotesManager;
import edu.washington.apache78.FlashStudy.models.Note;

public class MainActivity extends ActionBarActivity {
    String[] Terms = new String[]{"FUCK", "ANDROID", "STUDIO"};
    Button driveButton;
    ListView CardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        NotesManager flashcards = NotesManager.getInstance();
        List<Note> FlashCards = flashcards.getNotes();
        Terms = new String[FlashCards.size()];
        for(int i = 0; i<FlashCards.size();i++){
            Terms[i] = FlashCards.get(i).title;
        }



        CardList = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Terms);
        CardList.setAdapter(items);
        // Enable Local Datastore.

        driveButton = (Button) findViewById(R.id.driveButton);

        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driveAuthenticate = new Intent(MainActivity.this, RetrieveContentsWithProgressDialogActivity.class);
                startActivity(driveAuthenticate);
            }
        });


        CardList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(MainActivity.this, FlashCardActivity.class);
                String selected = (String) (CardList.getItemAtPosition(position));
                next.putExtra("SELECTED", selected);
                startActivity(next);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
