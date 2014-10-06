package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
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
			if (text.equals("AttackDice")) {
					try {
					this.attack = Integer.parseInt((ship.getChildByName("AttackDice")).getText());
				}
				catch (Exception e) {
					this.attack = 0;
				}
			}
			else if (text.equals("DefenseDice")) {		
				try {
					this.defense = Integer.parseInt((ship.getChildByName("DefenseDice")).getText());
				}
				catch (Exception e) {
					this.defense = 0;
				}
			}
			else if (text.equals("Hull"))  {
				try {
					this.hull = Integer.parseInt((ship.getChildByName("Hull")).getText());
				}
				catch (Exception e) {
					this.hull = 0;
				}
			}
			else if (text.equals("Shields")) {
				try {
					this.shields = Integer.parseInt((ship.getChildByName("Shields")).getText());
				}
				catch (Exception e) {
					this.shields = 0;
				}
			}
			else if (text.equals("Actions")) {
				try {
					this.shipActions = (ship.getChildByName("Actions")).getText();
				}
				catch (Exception e) {
					this.shipActions = "";
				}
			}
			else if (text.equals("Upgrades")) {
				try {
					this.shipUpgrades = (ship.getChildByName("Upgrades")).getText();
				}
				catch (Exception e) {
					this.shipUpgrades = "";
				}
			}
			else if (text.equals("FiringArc")) {
				try {
					this.firingArc = (ship.getChildByName("FiringArc")).getText();
				}
				catch (Exception e) {
					this.firingArc = "";
				}
			}
			else if (text.equals("Class")) {
				try {
					this.shipClass = (ship.getChildByName("Class")).getText().replace("�", "");
				}
				catch (Exception e) {
					this.shipClass = "";
				}
			}
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
	
	public void setupCardActions() {
		TextButton buttonAddAux = new TextButton("Add Auxilary Power", g.skin);
		buttonAddAux.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Add Auxilary Power");
			}
		});
		actionButtons.add(buttonAddAux);
		
		TextButton buttonRemoveAux = new TextButton("Remove Auxilary Power", g.skin);
		buttonRemoveAux.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Remove Auxilary Power");
			}
		});
		actionButtons.add(buttonRemoveAux);
		
		TextButton buttonScan = new TextButton("Scan", g.skin);
		buttonScan.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Scan");
			}
		});
		actionButtons.add(buttonScan);
		
		TextButton buttonEvade = new TextButton("Evade", g.skin);
		buttonEvade.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Evade");
			}
		});
		actionButtons.add(buttonEvade);
		
		TextButton buttonTL = new TextButton("Target Lock", g.skin);
		buttonTL.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Target Lock");
			}
		});
		actionButtons.add(buttonTL);
		
		TextButton buttonBS = new TextButton("BattleStations", g.skin);
		buttonBS.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("BattleStations");
			}
		});
		actionButtons.add(buttonBS);
		
		TextButton buttonCloak = new TextButton("Cloak", g.skin);
		buttonCloak.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Cloak");
			}
		});
		actionButtons.add(buttonCloak);
		
		TextButton buttonSensorEcho = new TextButton("Sensor Echo", g.skin);
		buttonSensorEcho.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Sensor Echo");
			}
		});
		actionButtons.add(buttonSensorEcho);
		
		TextButton buttonRegen = new TextButton("Regenerate", g.skin);
		buttonRegen.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Regenerate");
			}
		});
		actionButtons.add(buttonRegen);
		
		TextButton buttonShipAction = new TextButton("Ship Action", g.skin);
		buttonShipAction.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Ship Action");
			}
		});
		actionButtons.add(buttonShipAction);
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
	
	public void focusCardDetails() {
		if (!focusCardActions) {
			startingPixels = g.resizeY(360);
			g.game.font.draw(g.game.batch, shipClass + " | " + firingArc, xLine, startingPixels);
			startingPixels -= newLine;
			g.game.font.draw(g.game.batch, "Attack: " +  attack + " | Agility: " + defense + " | Hull: " + hull + " | Shields: " + shields, xLine, startingPixels);
			startingPixels -= newLine;
			g.game.font.draw(g.game.batch, shipActions + " | " + shipUpgrades, xLine, startingPixels);
		}
		super.focusCardDetails();		
	}
}

