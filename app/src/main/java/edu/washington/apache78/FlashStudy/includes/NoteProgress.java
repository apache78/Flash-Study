package edu.washington.apache78.FlashStudy.includes;

import java.io.Serializable;
import java.util.*;

import edu.washington.apache78.FlashStudy.models.*;

/**
 * This class keeps track of the progress of the user after the user starts the flash card viewing.
 * Rather than passing tons of variables around fragments through addArguments(), i figured it's more
 * efficient to save everything in a class and just pass this class object around.
 * To initialize this class, you'll need to provide the note this class is going to use.
 */
public class NoteProgress implements Serializable {
	public enum Vocab {
		Term, Definition
	}

	//the note we're going to do flash card on
	private Note note;

	//the list of cards from the note
	//we keep this copy so the cards don't change due to updates or other factors
	private ArrayList<Card> cards;

	//the current index of the card
	private int currentCardIndex;

	//the current vocabulary we're on, same as cards.at(currentCardIndex)
	private Card currentCard;

	public NoteProgress(Note note) {
		this(note, Vocab.Term);
	}

	public NoteProgress(Note note, Vocab frontShow) {
		this.note = note;
		this.cards = note.getCards();

		currentCardIndex = 0;
		currentCard = cards.get(0);
	}

	//returns the current card we're working on
	public Card getCurrentCard() {
		return (Card)currentCard.clone();
	}

	//returns the number of the current card we're on, starting from one
	public int getCurrentCardNo() {
		return currentCardIndex + 1;
	}

	//get the total number of cards in this note
	public int getCardsCount() {
		return cards.size();
	}

	//if this is the first card in the note
	public boolean isFirstCard() {
		return currentCardIndex == 0;
	}

	//if this is the last card in the note
	public boolean isLastCard() {
		return currentCardIndex < cards.size();
	}

	//advance the current card pointer to the next card
	//if no more cards exist in the note, this function returns false
	//otherwise it returns true
	//you can get the card by calling getCurrentCard()
	public boolean nextCard() {
		if(isLastCard()) {
			return false;
		}

		currentCard = cards.get(++ currentCardIndex);
		return true;
	}

	//goes back to the previous card in the note
	//if this is already the first card, this function returns false
	//otherwise it reutrn true
	//you can get the card by calling getCurrentCard()
	public boolean previousCard() {
		if(isFirstCard()) {
			return false;
		}

		currentCard = cards.get(-- currentCardIndex);
		return true;
	}
}
