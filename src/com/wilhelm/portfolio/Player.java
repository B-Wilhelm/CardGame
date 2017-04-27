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
		this.hand = new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		this.discard = new ArrayList<Card>();
	}
	
	// Setters
	private void setLife(int life) { this.life = life; }
	private void setName(String name) { this.name = name; }
	private void setHand(ArrayList<Card> hand) { this.hand = hand; }
	private void setDeck(ArrayList<Card> deck) { this.deck = deck; }
	private void setDiscard(ArrayList<Card> discard) { this.discard = discard; }

	// Getters
	public int getLife() { return life; }
	public String getName() { return name; }
	public ArrayList<Card> getHand() { return hand; }
	public ArrayList<Card> getDeck() { return deck; }
	public ArrayList<Card> getDiscard() { return discard; }
	
	// Other Methods
	
}
