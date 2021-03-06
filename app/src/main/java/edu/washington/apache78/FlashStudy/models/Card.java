package edu.washington.apache78.FlashStudy.models;

import org.json.*;

/**
 * Created by Stanley on 6/5/2015.
 */
public class Card implements Comparable<Card>, Cloneable {
	//since this is a model, we'll set these attributes to public
	public String term;
	public String definition;

	//mainly for comparason purposes
	public Card(String term) {
		this(term, "");
	}

	public Card(String term, String definition) {
		this.term = term;
		this.definition = definition;
	}

	public Card(JSONObject jsonObj) {
		try {
			term = jsonObj.getString("term");
			definition = jsonObj.getString("definition");
		} catch (JSONException e) {
			term = "";
			definition = "";
		}
	}

	@Override
	public int compareTo(Card another) {
		return term.compareTo(another.term);
	}

	//JSONObject.toString returns string representation of the json
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("term", term);
			jo.put("definition", definition);
		} catch (JSONException e) {}
		return jo;
	}

	@Override
	public Object clone() {
		return new Card(term, definition);
	}
}