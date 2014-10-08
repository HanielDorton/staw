package com.haniel.staw;

import java.util.ArrayList;

import com.haniel.staw.Cards.DamageCard;


public class DamageDeck {
	private GameScreen g;
	private ArrayList<Integer> damageDeck = new ArrayList<Integer>();
	
	public DamageDeck(GameScreen g) {
		this.g = g;
	}
	
	private void createDeck() {
		for (int i = 1; i < 16; i ++) {
			damageDeck.add(i);
			damageDeck.add(i);
		}
		//add extra direct hits and wcb
		damageDeck.add(6);
		damageDeck.add(6);
		damageDeck.add(10);
		java.util.Collections.shuffle(damageDeck);
	}
	
	public DamageCard getDamageCard() {
		if (damageDeck.size() == 0) {
			createDeck();
		}
		int c = damageDeck.get(0);
		damageDeck.remove(0);
		return new DamageCard(g, c);		
	}
}
