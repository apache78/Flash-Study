package edu.washington.apache78.FlashStudy.includes.parsers;

import java.io.InputStream;

import edu.washington.apache78.FlashStudy.models.*;

/**
 * Created by Stanley on 6/5/2015.
 */
public abstract class NoteParser {
	public enum SuccessCode {
		OK,
		PARTIAL
	};

	protected String content = null;
	protected Note note = null;

	public NoteParser() { }

	public NoteParser(String content) {
		this.content = content;
	}

	public static String getResultDescription(SuccessCode resultCode) {
		switch(resultCode) {
			case OK:
				return "Note successfully parsed!";
			case PARTIAL:
				return "Though there were some errors, note has been parsed!";
			default:
				return "Unknown error";
		}
	}

	public Note getNote() {
		return note;
	}

	public abstract SuccessCode parse(String content) throws NoteParserException;
}
