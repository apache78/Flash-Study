package edu.washington.apache78.FlashStudy.models;

/**
 * Created by Stanley on 6/5/2015.
 */
public class Card implements Comparable<Card>, Cloneable {
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

	@Override
	public int compareTo(Card another) {
		return term.compareTo(another.term);
	}

	@Override
	public Object clone() {
		return new Card(term, definition);
	}
}
