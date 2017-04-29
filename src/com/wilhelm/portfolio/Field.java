package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.util.ArrayList;

public class Field {
	private ArrayList<Card> play;
	private final int[] fieldCoord = {0,0,10,10,0,0};
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
	
	// Getters
	public ArrayList<Card> getPlay() { return play; }
	public ArrayList<Player> getPlayers() { return p; }
	
	// Other Methods
	
}