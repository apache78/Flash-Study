package edu.washington.apache78.FlashStudy.includes;

import java.util.*;

import edu.washington.apache78.FlashStudy.models.Note;

/**
 * The class that manages all the notes in this program.
 * This is also a singleton class that can called from everywhere.
 */
public class NotesManager {
	//singleton
	private static NotesManager instance = new NotesManager();
	public static NotesManager getInstance(){
		return instance;
	}

	//instance methods
	private List<Note> notes = new LinkedList<Note>();
	public NotesManager() { }

	public boolean addNote(Note note) {
		if(notes.contains(note)) {
			return false;
		}

		notes.add((Note)note.clone());
		return true;
	}

	public boolean deleteNote(Note note) {
		if(!notes.contains(note)) {
			return false;
		}

		notes.remove(note);
		return true;
	}

	public void saveNotes() {
		//save notes to android db
	}

	public void loadNotes() {
		//load notes from android
	}

	public List<Note> getNotes() {
		List<Note> ret = new LinkedList<Note>();
		for(Note note : notes) {
			ret.add((Note)note.clone());
		}
		return ret;
	}
}
