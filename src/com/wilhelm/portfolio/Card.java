package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

public class Card {
	private int hp, val;
	private String name, type, effect, owner;
	
	public Card() {
		this.name = "";	// Args
		this.type = "";
		this.hp = 0;
		this.effect = "";
		this.val = 0;
		this.owner = "";
	}

	public Card(String name, String type, int hp, int val, String owner) {
		this.name = name;	// Args
		this.type = type;
		this.hp = hp;
		this.effect = "";
		this.val = val;
		this.owner = owner;
	}
	
	public Card(String name, String type, int hp, String effect, int val, String owner) {
		this.name = name;	// Args
		this.type = type;
		this.hp = hp;
		this.effect = effect;
		this.val = val;
		this.owner = owner;
	}
	
	// Setters
//	private void setName(String name) { this.name = name; }
//	private void setType(String type) { this.type = type; }
//	private void setCost(int hp) { this.hp = hp; }
//	private void setEffect(String effect) { this.effect = effect; }
//	private void setVal(int val) { this.val = val; }
//	private void setOwner(int owner) { this.owner = owner+""; }
	
	// Getters
	public String getName() { return name; }
	public String getType() { return type; }
	public int getCost() { return hp; }
	public String getEffect() { return effect; }
	public int getVal() { return val; }
	public String getOwner() { return owner; }
	
	// Other Methods
	protected void emptyCard() {
		this.name = "";	// Args
		this.type = "";
		this.hp = 0;
		this.effect = "";
		this.val = 0;
		this.owner = "";
	}
	
	protected void takeHit() {
		hp--;
		val--;
		if(hp <= 0) { emptyCard(); }
		if(val <= 0) { val = 0; }
	}
	
	@Override
	public String toString() {
		String output = "Name: " + name + "\n" + "Type: " + type + "\n" + "Cost: " + hp + "\n";
		if(effect != null) { output += "Effect: " + effect + "\n"; }
		output += "Value: " + val + "\n" + "Owner: " + owner;
		return output;
	}
}