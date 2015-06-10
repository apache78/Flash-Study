package edu.washington.apache78.FlashStudy.includes;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import edu.washington.apache78.FlashStudy.models.Note;

/**
 * The class that manages all the notes in this program.
 * This is also a singleton class that can called from everywhere.
 */
public class NotesManager {
	static String FILENAME = "cards.db";

	//singleton
	private static NotesManager instance = new NotesManager();
	public static NotesManager getInstance(){
		return instance;
	}

	//instance methods
	private Context context = null;
	private List<Note> notes = new LinkedList<Note>();
	public NotesManager() { }

	public boolean addNote(Note note) {
		if(notes.contains(note)) {
			return false;
		}

		notes.add((Note)note.clone());
		saveNotes();
		return true;
	}

	public boolean deleteNote(Note note) {
		if(!notes.contains(note)) {
			return false;
		}

		notes.remove(note);
		return true;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void saveNotes() {
		File file = new File(context.getFilesDir(), FILENAME);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return;
			}
		}

		//create our json
		JSONArray jsonArray = new JSONArray();
		for(Note note : notes) {
			jsonArray.put(note.toJSON());
		}

		//write to file
		file.setWritable(true);
		try {
			FileOutputStream os = new FileOutputStream(file, false);
			os.write(jsonArray.toString().getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadNotes() {
		File file = new File(context.getFilesDir(), FILENAME);
		if(!file.exists()) {
			return;
		}

		//read from file
		file.setReadable(true);
		try {
			String jsonString = new Scanner(new File(context.getFilesDir(), FILENAME)).useDelimiter("\\A").next();
			JSONArray jsonArray = new JSONArray(jsonString);
			for (int i = 0; i < jsonArray.length(); ++i) {
				JSONObject obj = jsonArray.getJSONObject(i);
				notes.add(new Note(obj));
			}
		} catch (IOException e) {
			return;
		} catch (JSONException e) {
			return;
		}
	}

	public List<Note> getNotes() {
		List<Note> ret = new LinkedList<Note>();
		for(Note note : notes) {
			ret.add((Note)note.clone());
		}
		return ret;
	}
}