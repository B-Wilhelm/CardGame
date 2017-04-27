package com.wilhelm.portfolio;

public class Card {
	private int cost, val, effectVal;
	private String type, effect;
	
	public Card(int cost, int val, int effectVal, String type, String effect) {
		this.cost = cost;
		this.val = val;
		this.effectVal = effectVal;
		this.type = type;
		this.effect = effect;
	}
	
	// Setters
	private void setCost(int cost) { this.cost = cost; }
	private void setVal(int val) { this.val = val; }
	private void setEffectVal(int effectVal) { this.effectVal = effectVal; }
	private void setType(String type) { this.type = type; }
	private void setEffect(String effect) { this.effect = effect; }
	
	// Getters
	public int getCost() { return cost; }
	public int getVal() { return val; }
	public int getEffectVal() { return effectVal; }
	
	// Other Methods
	
}