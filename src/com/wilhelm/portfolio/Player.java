package com.wilhelm.portfolio;

import java.util.ArrayList;

public class Player {
	private String name;
	private int life;
	private ArrayList<Card> hand;
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	
	public Player(String name, int life) {
		this.name = name;
		this.life = life;
	}
	
	// Setters
	private void setLife(int life) { this.life = life; }
	private void setName(String name) { this.name = name; }
	
	// Getters
	public int getLife() { return life; }
	public String getName() { return name; }
	
	// Other Methods
	
}
