package com.wilhelm.portfolio;

public class Test {

	public static void main(String[] args) {
		Player p = new Player(1, "Boi", 2000);
		
		for(Card c: p.getDeck()) {
			System.out.println(c.toString() + "\n");
		}
	}
}