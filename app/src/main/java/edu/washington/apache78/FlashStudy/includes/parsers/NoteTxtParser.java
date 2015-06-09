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
		//note is a class member. It can be retrieved through getNote()
		note = new Note();
		Scanner s = new Scanner(content);

		//parse vocab:definition
		boolean containsInvalidInput = false;
		while(s.hasNextLine()) {
            String line = s.nextLine();
            if(line.equals("")) {
                //skip line
                continue;
            }

			String[] tokens = line.split(":", 2);
			if(tokens.length != 2) {
				containsInvalidInput = true;
				continue;
			}

			//read in more lines
			StringBuilder sbDef = new StringBuilder(tokens[1]);
			while(s.hasNextLine()) {
				String nextLine = s.nextLine();
				if(!nextLine.equals("")) {
					sbDef.append(nextLine + "\n");
				} else {
					//we're moving on to the next term:def set
					break;
				}
			}

			//add term
			note.addCard(tokens[0], sbDef.toString());
		}

		//did we add any cards?
		if(note.getCards().size() == 0) {
			return SuccessCode.EMPTY;
		}

		return containsInvalidInput ? SuccessCode.PARTIAL : SuccessCode.OK;
	}
}
