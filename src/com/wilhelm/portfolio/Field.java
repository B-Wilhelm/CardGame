package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.util.ArrayList;

public class Field {
	private ArrayList<Card> play;
	private ArrayList<Player> p = new ArrayList<Player>();
	private final String PLAYER_1 = "Brett";
	private final String PLAYER_2 = "TJ";
	
	public Field() {
		play = new ArrayList<Card>();
		p.add(new Player(1, PLAYER_1, 10));
		p.add(new Player(2, PLAYER_2, 10));
		
		for(int i = 0; i < 3; i++) {
			play.add(new Card());
		}
	}
	
	// Setters
	private void setPlay(ArrayList<Card> play) { this.play = play; }
	private void setPlayers() { this.p = p; }
	
	// Getters
	public ArrayList<Card> getPlay() { return play; }
	public ArrayList<Player> getPlayers() { return p; }
	
	// Other Methods
	protected void swapPlayers() {
		Card temp;
		
		temp = play.get(0);
		play.set(0, play.get(2));
		play.set(2, temp);
	}
}