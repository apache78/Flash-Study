package edu.washington.apache78.FlashStudy.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ShijirHome on 6/3/15.
 */
public class Note {
    private String title;
    private String content;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Note(String content) {
        this.content = content;
    }
    public Note(){
        this.title = "";
        this.content="";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return content;
    }

    public void setDescription(String content) {
        this.content = content;
    }

    public void addCard(Card card){
        cards.add(card);
    }


}
