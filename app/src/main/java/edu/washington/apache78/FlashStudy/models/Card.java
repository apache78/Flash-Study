package edu.washington.apache78.FlashStudy.models;

/**
 * Created by ShijirHome on 6/7/15.
 */
public class Card {
    private String term;
    private String definition;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
