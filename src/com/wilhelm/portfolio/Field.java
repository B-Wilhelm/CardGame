package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.util.ArrayList;

public class Field {
	private ArrayList<Card> play;
	private final int[] fieldCoord = {0,0,10,10,0,0};
	
	public Field() {
		play = new ArrayList<Card>();
	}
	
	// Setters
	private void setPlay(ArrayList<Card> play) { this.play = play; }
	
	// Getters
	public ArrayList<Card> getPlay() { return play; }
	
	// Other Methods
	
}