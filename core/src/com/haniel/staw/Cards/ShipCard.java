package com.haniel.staw.Cards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;


public class ShipCard extends Card{
	
	private int attack, defense, shields;
	private String shipActions, shipUpgrades, firingArc, shipClass;
	private List<Card> upgrades = new ArrayList<Card>();
	private List<DamageCard> damageCards = new ArrayList<DamageCard>();
	private ManueverCard manueverCard;
	private boolean hasManuevers = false;
	int auxTokens = 0;
	private int disabledShields = 0;
	protected ArrayList<TextButton> shipConditionButtons = new ArrayList<TextButton>();
	protected TextButton buttonShipCondition, buttonMoreConditions, buttonLessConditions;
	protected boolean focusedShipConditions = false;
	private ArrayList<String> shipLog = new ArrayList<String>();
	
	public ShipCard(Element ship, final GameScreen g, Fleet f, ShipCard nullShip) {
		super(ship, g, f, nullShip);
		this.skill = 0;
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
			
			else if (text.equals("Flagship")) {
				try {
					if ((ship.getChildByName("Flagship")).getText().equals("True")) {
						f.resourceLoaded = true;
						Element root = ship.getParent().getParent();
						Array<Element> res = root.getChildrenByName("Resource");
						if (res.size > 0) {
							upgrades.add(new Resource(res.get(0), g, f, this));
							for (int x = 0; x< res.get(0).getChildCount(); x++) {
								String resText = res.get(0).getChild(x).getName();
								if (resText.equals("AttackDice")) {
									this.attack += Integer.parseInt((res.get(0).getChildByName("AttackDice")).getText());
								}
								if (resText.equals("DefenseDice")) {
									this.defense += Integer.parseInt((res.get(0).getChildByName("DefenseDice")).getText());
								}
								if (resText.equals("Hull")) {
									this.hull += Integer.parseInt((res.get(0).getChildByName("Hull")).getText());
								}
								if (resText.equals("Shields")) {
									this.shields += Integer.parseInt((res.get(0).getChildByName("Shields")).getText());
								}
							}
						}
					}
				}
				catch (Exception e) {
				}
			}
			
		}
		if (unique.equals("No")) name = shipClass;
		getUpgrades(ship);
		try {
			loadTexture();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		setupFactionLevel();
		if (!(shipClass.equals("Nor Class Orbital Space Station"))) {
			this.manueverCard = new ManueverCard(faction, shipClass, g);
			hasManuevers = true;
		}

		
		setupShipConditionsButtons();
		buttonMoreConditions = new TextButton("More...", g.skin);
		buttonMoreConditions.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				startingActionButton += actionButtonsPerScreen;
				showShipConditions();
			}
		});
		
		buttonLessConditions = new TextButton("Back", g.skin);
		buttonLessConditions.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				startingActionButton -= actionButtonsPerScreen;
				showShipConditions();
			}
		});
	}
	
	private void setupFactionLevel() {
		if (faction.equals("Federation")) this.factionLevel = 2;
		else if (faction.equals("Klingon")) this.factionLevel = 4;
		else if (faction.equals("Romulan")) this.factionLevel = 6;
		else if (faction.equals("Dominion")) this.factionLevel = 8;
		else if (faction.equals("Borg")) this.factionLevel = 10;
		else if (faction.equals("Species 8472")) this.factionLevel = 12;
		else if (faction.equals("Kazon")) this.factionLevel = 14;
		else if (faction.equals("Bajoran")) this.factionLevel = 16;
		else if (faction.equals("Ferengi")) this.factionLevel = 18;
		else if (faction.equals("Vulcan")) this.factionLevel = 20;
		else if (faction.equals("Independent")) this.factionLevel = 22;
		else if (faction.equals("Mirror Universe")) this.factionLevel = 24;
		else this.factionLevel = 26;		
	}
	
	private void loadTexture() throws IOException {
		if (!textureLoaded) {

			if (g.checkForFile(faction + "/" + name + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + name + ".png"));
				this.textureLoaded = true;
			}	
			else if (g.checkForFile(faction + "/" + name + " " + shipClass + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + name + " " + shipClass + ".png"));
				this.textureLoaded = true;
			}	
			else if (g.checkForFile(faction + "/" + shipClass + " " + shipUpgrades + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + shipClass + " " + shipUpgrades + ".png"));
				this.textureLoaded = true;
			}	
			else if (g.checkForFile(faction + "/" + shipClass + " " + source + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + shipClass + " " + source + ".png"));
				this.textureLoaded = true;
			}

			else if (g.checkForFile(faction + "/" + shipClass + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + shipClass + ".png"));
				textureLoaded = true;
			}
		}
	}

	private void getUpgrades(Element ship) {
		// go through all children of ship and if any are cards (captain, tech, ...)
		// make new Card and add to ships upgrades array
		for (int i = 0; i< ship.getChildCount(); i++) {
			String text = ship.getChild(i).getName();
			if (text.equals("Captain")) {
				upgrades.add(0, new CaptainCard(ship.getChild(i), g, f, this));
			}
			if (text.equals("Admiral")) {
				upgrades.add(new AdmiralCard(ship.getChild(i), g, f, this));
			}
			if (text.equals("Crewmen")) {
				for(int c = 0; c < (ship.getChild(i)).getChildCount(); c++) {
					upgrades.add(new Card((ship.getChild(i)).getChild(c), g, f, this));
				}
			}
			if (text.equals("Weapons")) {
				for(int w = 0; w < (ship.getChild(i)).getChildCount(); w++) {
					upgrades.add(new WeaponCard((ship.getChild(i)).getChild(w), g, f, this));
				}
			}
			if (text.equals("Technology")) {
				for(int t = 0; t < (ship.getChild(i)).getChildCount(); t++) {
					upgrades.add(new Card((ship.getChild(i)).getChild(t), g, f, this));
				}
			}
			if (text.equals("BorgTechnology")) {
				for(int t = 0; t < (ship.getChild(i)).getChildCount(); t++) {
					upgrades.add(new Card((ship.getChild(i)).getChild(t), g, f, this));
				}
			}
		}
		//this just adds admiral bonus to captain skill if both are in the fleet
		int addSkill = 0;
		for (Card card : upgrades) {
			if (card instanceof AdmiralCard) {
				addSkill += card.skill;
			}
		}
		for (Card card : upgrades) {
			if (card instanceof CaptainCard) {
				card.skill += addSkill;
			}
		}
		
	}
	
	public void setupShipConditionsButtons() {
		
		TextButton buttonShipLog = new TextButton("Ship Log", g.skin);
		buttonShipLog.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				viewShipLog();
			}
		});
		shipConditionButtons.add(buttonShipLog);
		

		
		TextButton buttonDestroyShield = new TextButton("Destroy Shield", g.skin);
		buttonDestroyShield.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (shields > 0) {
					if (g.playSounds) g.quickbeep.play();
					shields -= 1;
					addAction(" - " + f.name + " " + name + ": Shield Destroyed. Remaining Shields: " + shields);					
				} else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " + f.name + " " + name + " has no shields");
				}
				
			}
		});
		shipConditionButtons.add(buttonDestroyShield);
		

		
		TextButton buttonDamageHull = new TextButton("Damage Hull", g.skin);
		buttonDamageHull.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (hull > 0) {
					if (g.playSounds) g.quickbeep.play();
					hull -= 1;
					addAction(" - " + f.name + " " + name + ": Hull Damaged. Remaining hull " + hull);
					if (hull == 0 ) addAction(" - " + f.name + " " + name + ": destroyed"); 
				}
				else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " +f.name + " " + name + " has no hull");
				}
			}
		});
		shipConditionButtons.add(buttonDamageHull);
		
		TextButton buttonCritical = new TextButton("Receive Critical", g.skin);
		buttonCritical.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (hull > 0) {
					if (g.playSounds) g.quickbeep.play();
					hull -= 1;
					addAction(" - " + f.name + " " + name + ": Receive Critical. Remaining Hull: " + hull);
					damageCards.add(g.damageDeck.getDamageCard());
					if (hull == 0 ) addAction(" - " + f.name + " " + name + ": destroyed");
				}
				else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " +f.name + " " + name + " has 0 hull");
				}
			}
		});
		shipConditionButtons.add(buttonCritical);
		
		TextButton buttonAddHull = new TextButton("Repair Hull", g.skin);
		buttonAddHull.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				hull += 1;
				addAction(" - " + f.name + " " + name + ": Repair Hull. Remaining Hull: " + hull);
			}
		});
		shipConditionButtons.add(buttonAddHull);
		
		TextButton buttonAddShield = new TextButton("Add Shield", g.skin);
		buttonAddShield.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				shields += 1;
				addAction(" - " + f.name + " " + name + ": Add Shield. Remaining Shields: " + shields);
			}
		});
		shipConditionButtons.add(buttonAddShield);
		
		TextButton buttonDisableShield = new TextButton("Disable Shield", g.skin);
		buttonDisableShield.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (shields > 0) {
					if (g.playSounds) g.quickbeep.play();
					addAction(" - " + f.name + " " + name + ": Disable Shield");
					shields -= 1;
					disabledShields += 1;
				} else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " + f.name + " " + name + " has no shields");
				}
			}
		});
		shipConditionButtons.add(buttonDisableShield);
		
		TextButton buttonUndisableShield = new TextButton("Undisable Shield", g.skin);
		buttonUndisableShield.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (disabledShields > 0) {
					if (g.playSounds) g.quickbeep.play();
					addAction(" - " + f.name + " " + name + ": Undisable Shield");
					shields += 1;
					disabledShields -= 1;
				} else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " + f.name + " " + name + " has no disabled shields");
				}
			}
		});
		shipConditionButtons.add(buttonUndisableShield);
	}
	
	public void setupCardActions() {
		TextButton buttonAddAux = new TextButton("Add Auxilary Token", g.skin);
		buttonAddAux.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				auxTokens += 1;
				addAction(" - " + f.name + " " + name + ": Add Aux Token. Current Aux Tokens: " + auxTokens);
			}
		});
		actionButtons.add(buttonAddAux);
		
		TextButton buttonRemoveAux = new TextButton("Remove Auxilary Token", g.skin);
		buttonRemoveAux.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (auxTokens > 0) {
					if (g.playSounds) g.quickbeep.play();
					auxTokens -= 1;
					addAction(" - " + f.name + " " + name + ": Remove Aux Token. Current aux Tokens: " + auxTokens);
				} else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " + f.name + " " + name + " has no Aux Tokens");
				}
			}
		});
		actionButtons.add(buttonRemoveAux);
		
		TextButton buttonScan = new TextButton("Scan", g.skin);
		buttonScan.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": Scan");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonScan);
		
		TextButton buttonEvade = new TextButton("Evade", g.skin);
		buttonEvade.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " +  name + ": Evade");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonEvade);
		
		TextButton buttonTL = new TextButton("Target Lock", g.skin);
		buttonTL.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": Target Lock");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonTL);
		
		TextButton buttonBS = new TextButton("BattleStations", g.skin);
		buttonBS.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": BattleStations");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonBS);
		
		TextButton buttonCloak = new TextButton("Cloak", g.skin);
		buttonCloak.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (shields > 0) {
					if (g.playSounds) g.quickbeep.play();
					addAction(" - " + f.name + " " + name + ": Disabled Shields and Cloaked.");
					if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
					disabledShields += shields;
					shields = 0;
				}else {
					if (g.playSounds) g.error.play();
					addAction(" - Error: " + f.name + " " + name + " has no shields");
				}
			}
		});
		actionButtons.add(buttonCloak);
		
		TextButton buttonSensorEcho = new TextButton("Sensor Echo", g.skin);
		buttonSensorEcho.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": Sensor Echo");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonSensorEcho);
		
		TextButton buttonRegen = new TextButton("Regenerate", g.skin);
		buttonRegen.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": Regenerate");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
			}
		});
		actionButtons.add(buttonRegen);
		
		TextButton buttonShipAction = new TextButton("Ship Action", g.skin);
		buttonShipAction.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				addAction(" - " + f.name + " " + name + ": Ship Action");
				if (auxTokens > 0) addAction("Warning: Ship has Aux tokens");
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
		Iterator<DamageCard> iter = damageCards.iterator(); 
		while(iter.hasNext()) {
			DamageCard d = iter.next();
			if (d.active) d.displayCard(g);
			else iter.remove();
		}
		for (Card upgrade : upgrades) {
			upgrade.displayCard(g);
			if (upgrade instanceof CaptainCard) {
				for (Card elite : ((CaptainCard) upgrade).getTalents()) {
					elite.displayCard(g);
				}
			}
		}
	}
	
	public void focusCard() {
		focusedShipConditions = false;
		buttonShipCondition = new TextButton("Ship Condition", g.skin);
		buttonShipCondition.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				onFocusedScreen = false;
				g.centerTable.clear();
				if (g.playSounds) g.quickbeep.play();
				startingActionButton = 0;
				showShipConditions();
				
			}
		});
		g.centerTable.add(buttonShipCondition).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(500)).padBottom(g.resizeY(140));
		g.centerTable.row();

		
		super.focusCard();
	}
	
	public void showShipConditions() {
		g.centerTable.clear();
		focusedShipConditions = true;
		int tempI = 0;
		for (int i = startingActionButton; i < startingActionButton + actionButtonsPerScreen; i++) {
			if (i < shipConditionButtons.size()) {
				if (i % 2 == 0) {
					g.centerTable.add(shipConditionButtons.get(i)).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
				}
				else {
					g.centerTable.add(shipConditionButtons.get(i)).width(g.buttonWidth).height(g.buttonHeight);
					g.centerTable.row().padTop(g.resizeY(30));
				}
				
			}
			tempI = i;			
		}
		if (!(tempI % 2 == 0)) {
			g.centerTable.row().padTop(g.resizeY(30));
		}
		if (startingActionButton + actionButtonsPerScreen < shipConditionButtons.size() && startingActionButton > 0) {
			g.centerTable.add(buttonLessConditions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
			g.centerTable.add(buttonMoreConditions).width(g.buttonWidth).height(g.buttonHeight);
		} else if (startingActionButton > 0) {
			g.centerTable.add(buttonLessConditions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
		} else if (startingActionButton + actionButtonsPerScreen < shipConditionButtons.size()) {
			g.centerTable.add(buttonMoreConditions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
		}
	}
	
	public void focusCardDetails() {
		if (!focusedShipConditions) {
			if (!focusCardActions) {
				startingPixels = g.resizeY(390);
				g.game.font.draw(g.game.batch, name + " - " + uniqueString + " | " + shipClass + " | " + firingArc, xLine, startingPixels);
				startingPixels -= newLine;
				g.game.font.draw(g.game.batch, "Attack: " +  attack + " | Agility: " + defense + " | Hull: " + hull + " | Shields: " + shields, xLine, startingPixels);
				startingPixels -= newLine;
				g.game.font.draw(g.game.batch, "Disabled Shields: " + disabledShields + " | Auxilary Tokens: " + auxTokens , xLine, startingPixels);
				startingPixels -= newLine;
				g.game.font.draw(g.game.batch, shipActions + " | " + shipUpgrades, xLine, startingPixels);
				super.focusCardDetails();
			}			
		}
	}
	
	public void setSkill() {
		this.skill = 0;
		for (Card c: upgrades) {
			if (c instanceof CaptainCard) {
				this.skill = c.skill;
			}
			if (c instanceof AdmiralCard) {
				if (!(c.isAdmiral))this.skill = c.captSkill;
			}
		}
		
	}
	public void addAction(String action) {
		g.addAction(action);
		shipLog.add(action);
	}
	
	public void viewShipLog() {
		g.resetCardsandSideButtons();
		g.centerTable.clear();
		String[] stringLog = new String[ shipLog.size() ];
		shipLog.toArray(stringLog);
		g.scrollPaneList.setItems(stringLog);
		g.scrollPane = new ScrollPane(g.scrollPaneList, g.skin);
		g.centerTable.add(g.scrollPane).width(g.resizeX(600)).height(g.resizeY(278));
	}

}

