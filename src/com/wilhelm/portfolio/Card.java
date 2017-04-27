package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

public class Card {
	private int cost, val;
	private String name, type, effect;

	public Card(String name, String type, int cost, int val) {
		this.name = name;	// Args
		this.type = type;
		this.cost = cost;
		this.effect = null;
		this.val = val;
	}
	
	public Card(String name, String type, int cost, String effect, int val) {
		this.name = name;	// Args
		this.type = type;
		this.cost = cost;
		this.effect = effect;
		this.val = val;
	}
	
	// Setters
	private void setName(String name) { this.name = name; }
	private void setType(String type) { this.type = type; }
	private void setCost(int cost) { this.cost = cost; }
	private void setEffect(String effect) { this.effect = effect; }
	private void setVal(int val) { this.val = val; }
	
	// Getters
	public String getName() { return name; }
	public String getType() { return type; }
	public int getCost() { return cost; }
	public String getEffect() { return effect; }
	public int getVal() { return val; }
	
	// Other Methods
	@Override
	public String toString() {
		String output = "Name: " + name + "\n" + "Type: " + type + "\n" + "Cost: " + cost + "\n";
		if(effect != null) { output += "Effect: " + effect + "\n"; }
		output += "Value: " + val;
		return output;
	}
}