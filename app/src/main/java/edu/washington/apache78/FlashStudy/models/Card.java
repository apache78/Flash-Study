package edu.washington.apache78.FlashStudy.models;

/**
<<<<<<< HEAD
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

	@Override
	public int compareTo(Card another) {
		return term.compareTo(another.term);
	}

	@Override
	public Object clone() {
		return new Card(term, definition);
	}
}
