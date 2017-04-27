package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Field {
	private ArrayList<Card> play;
	
	public Field() {
		play = new ArrayList<Card>();
	}
	
	// Setters
	private void setPlay(ArrayList<Card> play) { this.play = play; }
	
	// Getters
	public ArrayList<Card> getPlay() { return play; }
	
	// Other Methods
	
}