package edu.washington.apache78.FlashStudy;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by apache78 on 5/31/2015.
 */
public class FlashCardApp extends Application implements FlashCardRepository {
	@Override
	public void onCreate() {
		super.onCreate();

		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_key));
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();

	}
}
