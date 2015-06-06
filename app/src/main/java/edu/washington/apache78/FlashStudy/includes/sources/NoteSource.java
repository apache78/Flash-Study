package edu.washington.apache78.FlashStudy.includes.sources;

/**
 * This is an abstract class served to provide common functionalities for retrieving note files.
 */
public abstract class NoteSource {
	public enum ErrorCode {
		OK,
		INVALID_PATH,
		CONTENT_UNAVAILABLE
	};

	public static String getResultDescription(ErrorCode errorCode) {
		switch(errorCode) {
			case OK:
				return "No Error";
			case INVALID_PATH:
				return "Invalid URL/Path";
			case CONTENT_UNAVAILABLE:
				return "Unable to retrieve content from URL/Path";
			default:
				return "Unknown Error";
		}
	}

	public abstract void fetch(Callback cb);

	public static class Callback {
		public void failed(ErrorCode errorCode) {}
		public void success(String content) {}
	}
}
