package edu.washington.apache78.FlashStudy.includes;

import edu.washington.apache78.FlashStudy.includes.parsers.*;
import edu.washington.apache78.FlashStudy.includes.sources.*;
import edu.washington.apache78.FlashStudy.models.*;

/**
 * This class serves as the main entrance for downloading/retrieving a note file and parsing it.
 * To use this class, you'll need to initialize a NoteSource. With that NoteSource, this class will then
 * fetch the note file and try to parse it with every parser we have available. You can get feedback to the
 * downloading of parsing tasks by passing in a creating a callback listerner in fetch().
 */
public class NoteProvider {
	private final NoteSource noteSource;

	//all available parsers
	private final Class<NoteParser>[] parsers = new Class[]{
			NoteTxtParser.class
	};

	public NoteProvider(NoteSource source) {
		this.noteSource = source;
	}

	//the function that does everything.
	public void fetch(final Callback cb) {
		noteSource.fetch(new NoteSource.Callback() {
			@Override
			public void failed(NoteSource.ErrorCode errorCode) {
				cb.sourceFailed(errorCode);
			}

			@Override
			public void success(String content) {
				//content downloaded, let's try to parse it
				//let's try to parse the content with all the parsers we have
				for(Class<NoteParser> Parser : parsers) {
					try {
						NoteParser parser = Parser.newInstance();
						NoteParser.SuccessCode successCode = parser.parse(content);
						Note note = parser.getNote();

						//add note to our notes collection
						NotesManager.getInstance().addNote(note);

						//call callback
						cb.success(note, successCode);
						return;

					//if a parser fails, goes on to the next
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					} catch (NoteParserException e) { }
				}

				//if all parsers fail, we don't know how to parse this file
				cb.parserFailed();
			}
		});
	}

	public static class Callback {
		public void sourceFailed(NoteSource.ErrorCode errorCode) { }
		public void parserFailed() { }
		public void success(Note note, NoteParser.SuccessCode parserResult) { }
	}
}
