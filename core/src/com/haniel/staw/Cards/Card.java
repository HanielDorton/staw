package com.haniel.staw.Cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;


public class Card {
	
	// Card is extended for Ship, Captain, Admiral and Weapons
	// Generic cards are Techs, Talents and Crew
	
	protected String cardType;
	public String name;
	protected String unique;
	protected String source;
	protected String cardText;
	protected String faction;
	protected int cost;
	public boolean disabled = false;
	public boolean discardedByUse = false;
	public boolean discardedByOpponent = false;
	protected Texture texture;
	protected boolean textureLoaded = false;
	private Rectangle rect;
	protected GameScreen g;
	protected int newLine;
	protected int newTextLine;
	protected float xLine;
	protected float startingPixels;
	protected String uniqueString = "";
	protected int startingCharacter = 0;
	protected int lineLength = 80;
	protected String currenText1 = "";
	protected String currenText2 = "";
	protected String currenText3 = "";
	protected String currenText4 = "";
	protected String currenText5 = "";
	protected String currenText6 = "";
	protected String currenText7 = "";
	protected Button buttonActions;
	protected boolean focusCardActions = false;
	protected ArrayList<TextButton> actionButtons = new ArrayList<TextButton>();
	protected int startingActionButton = 0;
	protected int actionButtonsPerScreen = 4;
	protected TextButton buttonMoreActions, buttonLessActions;
	public boolean onFocusedScreen = false;
	public int skill;
	public Fleet f;
	public int factionLevel;
	public String ship;
	public int hull;
	
	public Card(Element element, final GameScreen g, Fleet f, String ship){
		this.ship = ship;
		this.f = f;
		this.g = g;
		newLine = g.resizeY(30);
		newTextLine = g.resizeY(18);
		xLine = g.resizeX(335);
		this.cardType = element.getName();
		setupCardActions();
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();			
			if (text.equals("Name")) this.name = (element.getChildByName("Name")).getText().replace(".", "").replace("’",  "");
			else if (text.equals("Source")) {
				try {
					this.source = (element.getChildByName("Source")).getText().replace(".", "").replace(" / ", " ").replace("/", " ");
				}
				catch (Exception n) {
					this.source = "";
				}
			}
			else if (text.equals("CardText")) {
				try {
					this.cardText = (element.getChildByName("CardText")).getText();
				}
				catch (Exception e) {
					this.cardText = "";
				}
			}
			else if (text.equals("Faction")) {
				try {
					this.faction = (element.getChildByName("Faction")).getText();
				}
				catch (Exception e) {
					this.faction = "";
				}
			}
			else if (text.equals("Unique")) {
				try {
					this.unique = (element.getChildByName("Unique")).getText();
				}
				catch (Exception e) {
					this.unique = "";
				}
			}
			else if (text.equals("PointCost")) {
				try {
					this.cost = Integer.parseInt((element.getChildByName("PointCost")).getText());
				}
				catch (Exception e) {
					this.cost = 0;
				}
			}
		}
		if (unique != null) {
			if (unique.equals("Yes")) uniqueString = "Unique";
			else uniqueString ="Generic";
		}
		loadTexture();
		this.rect = new Rectangle(0, 0, g.resizeX(200), g.resizeY(278));
		if (cardText == null) cardText = "";
		for (int c = startingCharacter; c < startingCharacter + lineLength; c++ ) {
			if (c < cardText.length()) {
				currenText1 += cardText.charAt(c);
			}
			if (c + lineLength < cardText.length()) {
				currenText2 += cardText.charAt(c + lineLength);
			}
			if (c  + (lineLength * 2) < cardText.length()) {
				currenText3 += cardText.charAt(c  + (lineLength * 2));
			}
			if (c  + (lineLength * 3) < cardText.length()) {
				currenText4 += cardText.charAt(c  + (lineLength * 3));
			}
			if (c  + (lineLength * 4) < cardText.length()) {
				currenText5 += cardText.charAt(c  + (lineLength * 4));
			}
			if (c  + (lineLength * 5) < cardText.length()) {
				currenText6 += cardText.charAt(c  + (lineLength * 5));
			}
			if (c  + (lineLength * 6) < cardText.length()) {
				currenText7 += cardText.charAt(c  + (lineLength * 6));
			}
		}
		
		buttonMoreActions = new TextButton("More...", g.skin);
		buttonMoreActions.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				startingActionButton += actionButtonsPerScreen;
				showActions();
			}
		});
		
		buttonLessActions = new TextButton("Back", g.skin);
		buttonLessActions.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				startingActionButton -= actionButtonsPerScreen;
				showActions();
			}
		});
		
	}
	
	public Card(GameScreen g, int card) {
		//this is for damage cards only
		this.g = g;
		this.rect = new Rectangle(0, 0, g.resizeX(200), g.resizeY(278));
		newLine = g.resizeY(30);
		newTextLine = g.resizeY(18);
		xLine = g.resizeX(335);
		setupCardActions();
	}
	
	public Card(String faction, String shipClass, GameScreen g){
		//this is for manuever cards only
		this.g = g;
		this.rect = new Rectangle(0, 0, g.resizeX(200), g.resizeY(278));
		setupCardActions();
	}
	
	public void setupCardActions() {
		TextButton buttonUse = new TextButton("Use Card Action", g.skin);
		buttonUse.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (!(disabled || discardedByUse || discardedByOpponent)) {
					if (g.playSounds) g.quickbeep.play();
					g.addAction(" - " + f.name + " " + ship + " " + "use Upgrade: " + name);
				} else {
					if (g.playSounds) g.error.play();
					g.addAction(" - Error: " + f.name + " " + ship + " " + name + " cannot be used");
				}
			}
		});
		actionButtons.add(buttonUse);
		
		TextButton buttonDisable = new TextButton("Toggle Disable", g.skin);
		buttonDisable.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (!(discardedByUse || discardedByOpponent)) {
					if (g.playSounds) g.quickbeep.play();
					if (disabled) {
						g.addAction(" - " + f.name + " " + ship + " " + name + " undisabled");
						disabled = false;
					} else {
						g.addAction(" - " + f.name + " " + ship + " " + name + " disabled");
						disabled = true;
					}
				}
				else {
					if (g.playSounds) g.error.play();
					g.addAction(" - Error: " + f.name + " " + ship + " " + name + " cannot be used");
				}
			}
		});
		actionButtons.add(buttonDisable);
		
		TextButton buttonDiscard = new TextButton("Discard by Owner", g.skin);
		buttonDiscard.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (!(discardedByUse || discardedByOpponent)) {
					if (g.playSounds) g.quickbeep.play();
					g.addAction(" - " + f.name + " " + ship + " " + name + " discarded by owner");
					discardedByUse = true;
				}
				else {
					if (g.playSounds) g.error.play();
					g.addAction(" - Error: " + f.name + " " + ship + " " + name + " cannot be used");
				}
			}
		});
		actionButtons.add(buttonDiscard);
		
		TextButton buttonStolen = new TextButton("Card Stolen/Discarded", g.skin);
		buttonStolen.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (!(discardedByUse || discardedByOpponent)) {
					if (g.playSounds) g.quickbeep.play();
					g.addAction(" - " + f.name + " " + ship + " " + name + " Stolen/Discarded by opponent");
					discardedByOpponent = true;
				} else {
					if (g.playSounds) g.error.play();
					g.addAction(" - Error: " + f.name + " " + ship + " " + name + " cannot be used");
				}
			}
		});
		actionButtons.add(buttonStolen);
		
		TextButton buttonReturnToPlay = new TextButton("Return to Play", g.skin);
		buttonReturnToPlay.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (discardedByUse || discardedByOpponent) {
					if (g.playSounds) g.quickbeep.play();
					g.addAction(" - " + f.name + " " + ship + " " + name + " Returned To Play");
					discardedByOpponent = false;
					discardedByUse = false;
				} else {
					if (g.playSounds) g.error.play();
					g.addAction( " - Error: " + f.name + " " + ship + " " + name + " already in play");
				}
			}
		});
		actionButtons.add(buttonReturnToPlay);
	}

	
	private void loadTexture() {

		if (Gdx.files.internal(faction + "/" +  name + " " + source + ".png").exists()) {
			this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " " + source + ".png"));
			this.textureLoaded = true;
		}
		else if (Gdx.files.internal(faction + "/" + name + ".png").exists()) {
			this.texture = new Texture((faction + "/" + name + ".png"));
			this.textureLoaded = true;
		}
	}

	public void displayCard(GameScreen g) {
		g.currentCards.add(this);
	}
	public String getName() {
		if (name == null) return "Unable to access Name";
		return name;
	}
	public String getCardText() {
		if (cardText == null) return "";
		return cardText;
	}
	public Texture getTexture() {
		return texture;
	}
	public boolean hasTexture() {
		return textureLoaded;
	}
	public Rectangle getRect() {
		return rect;
	}

	public void focusCard() {
		onFocusedScreen = true;
		focusCardActions = false;
		g.currentCards.clear();
		g.focusedCard.clear();
		g.startingCard = 0;
		g.resetSideButtons();
		g.focusedCard.add(this);
		
		buttonActions = new TextButton("Actions", g.skin);
		buttonActions.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				onFocusedScreen = false;
				g.centerTable.clear();
				if (g.playSounds) g.quickbeep.play();
				startingActionButton = 0;
				showActions();
			}
		});
		
		if (this instanceof ShipCard) {
			g.centerTable.add(buttonActions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(500));
		} else {
			g.centerTable.add(buttonActions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(500)).padTop(g.resizeY(200));
		}
	}
	
	public void focusCardDetails() {
		// name cardType, faction, cardtext, source
		if (!focusCardActions) {
			startingCharacter = 0;
			if (!(this instanceof ShipCard)) {
				startingPixels = g.resizeY(390);
				g.game.font.draw(g.game.batch, name + " - " + uniqueString, xLine, startingPixels);
			}
			startingPixels = g.resizeY(270);
			g.game.font.draw(g.game.batch, currenText1, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText2, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText3, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText4, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText5, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText6, xLine, startingPixels);
			startingPixels -= newLine;
			g.game.font.draw(g.game.batch, currenText7, xLine, startingPixels);
			if (source != null) g.game.font.draw(g.game.batch, source, xLine, startingPixels);
		}
	}
	
	public void showActions() {
		g.centerTable.clear();
		focusCardActions = true;
		int tempI = 0;
		for (int i = startingActionButton; i < startingActionButton + actionButtonsPerScreen; i++) {
			if (i < actionButtons.size()) {
				if (i % 2 == 0) {
					g.centerTable.add(actionButtons.get(i)).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
				}
				else {
					g.centerTable.add(actionButtons.get(i)).width(g.buttonWidth).height(g.buttonHeight);
					g.centerTable.row().padTop(g.resizeY(30));
				}
				
			}
			tempI = i;			
		}
		if (!(tempI % 2 == 0)) {
			g.centerTable.row().padTop(g.resizeY(30));
		}
		if (startingActionButton + actionButtonsPerScreen < actionButtons.size() && startingActionButton > 0) {
			g.centerTable.add(buttonLessActions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
			g.centerTable.add(buttonMoreActions).width(g.buttonWidth).height(g.buttonHeight);
		} else if (startingActionButton > 0) {
			g.centerTable.add(buttonLessActions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
		} else if (startingActionButton + actionButtonsPerScreen < actionButtons.size()) {
			g.centerTable.add(buttonMoreActions).width(g.buttonWidth).height(g.buttonHeight).padLeft(g.resizeX(260)).padRight(g.resizeX(50));
		}
	}
	
	// void functions only used for shipcard so don't have to call (ShipCard) on gameplay actions
	
	public void setSkill() {
		
	}

}
