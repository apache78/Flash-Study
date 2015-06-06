package edu.washington.apache78.FlashStudy.includes.parsers;

import java.util.*;
import java.util.regex.*;

import edu.washington.apache78.FlashStudy.models.*;

/**
 * Created by Stanley on 6/5/2015.
 */
public class NoteTxtParser extends NoteParser {

	@Override
	public SuccessCode parse(String content) throws NoteParserException {
		Scanner s = new Scanner(content);
		note = new Note();

		//get note title
		String[] tokens = s.nextLine().split(":", 2);
		if(tokens.length != 2 || !tokens[0].trim().toLowerCase().equals("title")) {
			throw new NoteParserException();
		}
		note.setTitle(tokens[1].trim());

		//get note description
		tokens = s.nextLine().split(":", 2);
		if(tokens.length != 2 || !tokens[0].trim().toLowerCase().equals("description")) {
			throw new NoteParserException();
		}
		note.setTitle(tokens[1].trim());

		//we should have a blank line here
		if(!s.nextLine().equals("")) {
			throw new NoteParserException();
		}

		//parse vocab:definition
		boolean containsInvalidInput = false;
		while(s.hasNextLine()) {
			tokens = s.nextLine().split(":", 2);
			if(tokens.length != 2) {
				containsInvalidInput = true;
				continue;
			}

			//add term
			note.addCard(tokens[0], tokens[1]);
		}

		return containsInvalidInput ? SuccessCode.PARTIAL : SuccessCode.OK;
	}
}
