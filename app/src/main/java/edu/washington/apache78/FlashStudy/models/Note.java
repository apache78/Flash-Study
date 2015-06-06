package edu.washington.apache78.FlashStudy.models;

import java.util.ArrayList;

/**
 * Created by ShijirHome on 6/3/15.
 */
public class Note implements Cloneable, Comparable<Note> {
	private String title;
	private String description;
	private ArrayList<Card> cards = new ArrayList<Card>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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


	@Override
	public Object clone() {
		Note note = new Note();
		note.title = title;
		note.description = description;
		note.cards = getCards();			//this clones our cards
		return note;
	}

	@Override
	public int compareTo(Note o) {
		return title.compareTo(o.title);
	}
}
