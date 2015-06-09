package edu.washington.apache78.FlashStudy.models;

import org.json.*;

import java.util.ArrayList;

/**
 * Created by ShijirHome on 6/3/15.
 */

public class Note implements Cloneable, Comparable<Note> {
	//since this is a model, we'll set these attributes to public
	public String title;

	private ArrayList<Card> cards = new ArrayList<Card>();

	public boolean containCard(String term) {
		return cards.contains(new Card(term));
	}

	public boolean addCard(String term, String definition) {
		if(containCard(term)) {
			return false;
		}

		cards.add(new Card(term, definition));
		return true;
	}

	public boolean editCard(String term, String definition){
		int i = cards.indexOf(new Card(term, definition));
		if(i >= 0) {
			cards.get(i).definition = definition;
			return true;
		}
		return false;
	}

	public ArrayList<Card> getCards() {
		return (ArrayList<Card>)cards.clone();
	}

	//JSONObject.toString returns string representation of the json
	public JSONObject toJSON() {
		JSONObject noteObject = new JSONObject();
		JSONArray cardsObject = new JSONArray();
		try {
			noteObject.put("title", title);

			//insert cards
			for(Card card : cards) {
				cardsObject.put(card.toJSON());
			}

			//put cards in note
			noteObject.put("cards", cardsObject);
		} catch (JSONException e) { }
		return noteObject;
	}

	@Override
	public Object clone() {
		Note note = new Note();
		note.title = title;
		note.cards = getCards();			//this clones our cards
		return note;
	}

	@Override
	public int compareTo(Note o) {
		return title.compareTo(o.title);
	}
}
