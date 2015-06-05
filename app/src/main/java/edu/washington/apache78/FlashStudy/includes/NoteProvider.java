package edu.washington.apache78.FlashStudy.includes;

import edu.washington.apache78.FlashStudy.includes.parsers.*;
import edu.washington.apache78.FlashStudy.includes.sources.*;
import edu.washington.apache78.FlashStudy.models.*;

/**
 * Created by Stanley on 6/5/2015.
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

	public void fetch(final Callback cb) {
		noteSource.fetch(new NoteSource.Callback() {
			@Override
			public void failed(NoteSource.ErrorCode errorCode) {
				cb.sourceFailed(errorCode);
			}

			@Override
			public void success(String content) {
				//content downloaded, let's try to parse it
				for(Class<NoteParser> Parser : parsers) {
					try {
						//let's try to parse the content with all the parsers we have
						NoteParser parser = Parser.newInstance();
						NoteParser.SuccessCode successCode = parser.parse(content);
						Note note = parser.getNote();

						//call callback
						cb.success(note, successCode);
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					} catch (NoteParserException e) {

					}
				}
			}
		});
	}

	public interface Callback {
		public void sourceFailed(NoteSource.ErrorCode errorCode);
		public void parserFailed(NoteParser.ErrorCode errorCode);
		public void success(Note note, NoteParser.SuccessCode parserResult);
	}
}