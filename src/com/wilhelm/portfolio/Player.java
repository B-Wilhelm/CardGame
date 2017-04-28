package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player {
	private String name;
	private int pNum, life;
	private final String CARD_LIST_PREFIX = "decks/deck";
	private final String CARD_LIST_SUFFIX = ".txt";
	private ArrayList<Card> hand;
	private ArrayList<Card> deck;
	private ArrayList<Card> unused;
	
	public Player(int pNum, String name, int life) {
		this.pNum = pNum;
		this.name = name;	// Args
		this.life = life;
		this.hand = new ArrayList<Card>();	// Defaults
		this.deck = new ArrayList<Card>();
		this.unused = new ArrayList<Card>();
		readCards();	// Other Methods
	}
	
	// Setters
	private void setPlayerNum(int pNum) { this.pNum = pNum; }
	private void setLife(int life) { this.life = life; }
	private void setName(String name) { this.name = name; }
	private void setHand(ArrayList<Card> hand) { this.hand = hand; }
	private void setDeck(ArrayList<Card> deck) { this.deck = deck; }
	private void setDiscard(ArrayList<Card> unused) { this.unused = unused; }

	// Getters
	public int getPlayerNum() { return pNum; }
	public int getLife() { return life; }
	public String getName() { return name; }
	public ArrayList<Card> getHand() { return hand; }
	public ArrayList<Card> getDeck() { return deck; }
	public ArrayList<Card> getDiscard() { return unused; }
	
	// Other Methods
	private void readCards() {
		Path p = Paths.get(CARD_LIST_PREFIX + pNum + CARD_LIST_SUFFIX);
		Charset c = Charset.forName("US-ASCII");
		String[] parts = {"","","","","",""};	// Size 6
		int i, iMod;
		
		try (BufferedReader r = Files.newBufferedReader(p, c)) {
			iMod = 0;
		    String line = null;
		    for(i = 0; (line = r.readLine()) != null; i++) {
		    	iMod = i%5;
		    	parts[iMod] = line;
		    	if(iMod == 4) {
		    		if(parts[1].equals("Main")) {	// Checks for null effect attribute; Prevents "Main" cards from displaying a null effect
		    			deck.add(new Card(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[4])));
		    		}
		    		else {
		    			deck.add(new Card(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], Integer.parseInt(parts[4])));
		    		}
		    		r.readLine();
		    	}
		    }
		    if(iMod != 4) {
		    	throw new Exception("File line count is incorrect");
		    }
		} catch (Exception x) {
		    System.err.format("Exception: %s%n", x);
		}
	}
}
