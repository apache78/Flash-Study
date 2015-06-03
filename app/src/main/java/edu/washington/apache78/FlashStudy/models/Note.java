package edu.washington.apache78.FlashStudy.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ShijirHome on 6/3/15.
 */
public class Note {
    private String title;
    private String description;
    private HashMap<String, String> cards;

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

    public void addCard(String term, String definition){
        cards.put(term, definition);
    }

    public void editCard(String term, String definition){
        cards.put(term, definition);
    }
}
