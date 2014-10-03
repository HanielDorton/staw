package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.GameScreen;


public class ShipCard extends Card{
	
	private int attack, defense, hull, shields;
	private String shipActions, shipUpgrades, firingArc, shipClass;
	private List<Card> upgrades = new ArrayList<Card>();
	private ManueverCard manueverCard;
	private boolean hasManuevers = false;

	public ShipCard(Element ship, GameScreen g) {
		super(ship, g);
		for (int i = 0; i< ship.getChildCount(); i++) {
			String text = ship.getChild(i).getName();
			if (text.equals("AttackDice")) this.attack = Integer.parseInt((ship.getChildByName("AttackDice")).getText());
			else if (text.equals("DefenseDice")) this.defense = Integer.parseInt((ship.getChildByName("DefenseDice")).getText());
			else if (text.equals("Hull")) this.hull = Integer.parseInt((ship.getChildByName("Hull")).getText());
			else if (text.equals("Shields")) this.shields = Integer.parseInt((ship.getChildByName("Shields")).getText());
			else if (text.equals("Actions")) this.shipActions = (ship.getChildByName("Actions")).getText();
			else if (text.equals("Upgrades")) this.shipUpgrades = (ship.getChildByName("Upgrades")).getText();
			else if (text.equals("FiringArc")) this.firingArc = (ship.getChildByName("FiringArc")).getText();
			else if (text.equals("Class")) this.shipClass = (ship.getChildByName("Class")).getText().replace("’", "");
		}
		if (unique.equals("No")) name = shipClass;
		getUpgrades(ship);
		loadTexture();
		if (!(shipClass.equals("Nor Class Orbital Space Station"))) {
			this.manueverCard = new ManueverCard(faction, shipClass, g);
			hasManuevers = true;
		}
	}
	
	private void loadTexture() {
		if (!textureLoaded) {

			if (Gdx.files.internal(faction + "/" + name + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + name + ".png"));
				this.textureLoaded = true;
			}	
			else if (Gdx.files.internal(faction + "/" + name + " " + shipClass + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " " + shipClass + ".png"));
				this.textureLoaded = true;
			}	
			else if (Gdx.files.internal(faction + "/" + shipClass + " " + shipUpgrades + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + shipClass + " " + shipUpgrades + ".png"));
				this.textureLoaded = true;
			}	
			else if (Gdx.files.internal(faction + "/" + shipClass + " " + source + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + shipClass + " " + source + ".png"));
				this.textureLoaded = true;
			}

			else if (Gdx.files.internal(faction + "/" + shipClass + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + shipClass + ".png"));
				textureLoaded = true;
			}
			else{
				/*
				System.out.println("----");
				System.out.println("Unable to load Ship: " + name);
				System.out.println("Class: " + shipClass);
				*/
			}

		}
		
		
	}

	private void getUpgrades(Element ship) {
		// go through all children of ship and if any are cards (captain, tech, ...)
		// make new Card and add to ships upgrades array
		for (int i = 0; i< ship.getChildCount(); i++) {
			String text = ship.getChild(i).getName();
			if (text.equals("Captain")) {
				upgrades.add(new CaptainCard(ship.getChild(i), g));
			}
			if (text.equals("Crewmen")) {
				for(int c = 0; c < (ship.getChild(i)).getChildCount(); c++) {
					upgrades.add(new Card((ship.getChild(i)).getChild(c), g));
				}
			}
			if (text.equals("Weapons")) {
				for(int w = 0; w < (ship.getChild(i)).getChildCount(); w++) {
					upgrades.add(new WeaponCard((ship.getChild(i)).getChild(w), g));
				}
			}
			if (text.equals("Technology")) {
				for(int t = 0; t < (ship.getChild(i)).getChildCount(); t++) {
					upgrades.add(new WeaponCard((ship.getChild(i)).getChild(t), g));
				}
			}
			if (text.equals("BorgTechnology")) {
				for(int t = 0; t < (ship.getChild(i)).getChildCount(); t++) {
					upgrades.add(new Card((ship.getChild(i)).getChild(t), g));
				}
			}
		}
		
	}
	public List<Card> getUpgrades() {
		return upgrades;
	}
	

	public void displayShip(GameScreen g){
		g.currentCards.add(this);
		if (hasManuevers) g.currentCards.add(manueverCard);
		for (Card upgrade : upgrades) {
			upgrade.displayCard(g);
			if (upgrade instanceof CaptainCard) {
				for (Card elite : ((CaptainCard) upgrade).getTalents()) {
					elite.displayCard(g);
				}
			}
		}
	}	
}

