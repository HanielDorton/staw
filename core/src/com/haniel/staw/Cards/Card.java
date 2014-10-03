package com.haniel.staw.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.GameScreen;


public class Card {
	
	// Card is extended for Ship, Captain, Admiral and Weapons
	// Generic cards are Techs, Talents and Crew
	
	protected String cardType = "";
	protected String name = "";
	protected String unique = "";
	protected String source = "";
	protected String cardText = "";
	protected String faction = "";
	protected int cost;
	protected boolean disabled = false;
	protected boolean discardedByUse = false;
	protected boolean discardByOpponent = false;
	protected Texture texture;
	protected boolean textureLoaded = false;
	private Rectangle rect;
	protected GameScreen g;
	private int newLine;
	private int newTextLine;
	private float xLine;
	private float startingPixels;
	private String uniqueString = "";
	private int startingCharacter = 0;
	private int lineLength = 70;
	private String currenText1 = "";
	private String currenText2 = "";
	private String currenText3 = "";
	private String currenText4 = "";
	private String currenText5 = "";
	private String currenText6 = "";
	
	
	public Card(Element element, GameScreen g){
		this.g = g;
		newLine = g.resizeY(30);
		newTextLine = g.resizeY(18);
		xLine = g.resizeX(400);
		this.cardType = element.getName();
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();			
			if (text.equals("Name")) this.name = (element.getChildByName("Name")).getText().replace(".", "").replace("’",  "");
			else if (text.equals("Source")) this.source = (element.getChildByName("Source")).getText().replace(".", "").replace(" / ", " ").replace("/", " ");
			else if (text.equals("CardText")) this.cardText = (element.getChildByName("CardText")).getText();
			else if (text.equals("Faction")) this.faction = (element.getChildByName("Faction")).getText();
			else if (text.equals("Unique")) this.unique = (element.getChildByName("Unique")).getText();
			else if (text.equals("PointCost")) this.cost = Integer.parseInt((element.getChildByName("PointCost")).getText());
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
		}
	}
	
	public Card(String faction, String shipClass, GameScreen g){
		this.g = g;
		this.rect = new Rectangle(0, 0, g.resizeX(200), g.resizeY(278));
	}

	
	private void loadTexture() {

		if (Gdx.files.internal(faction + "/" + name + " " + source + ".png").exists()) {
			this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " " + source + ".png"));
			this.textureLoaded = true;
		}
		else if (Gdx.files.internal(faction + "/" + name + ".png").exists()) {
			this.texture = new Texture((faction + "/" + name + ".png"));
			this.textureLoaded = true;
		}
		else {
			/*
			System.out.println("----");
			System.out.println("Unable to Load: " + name);
			System.out.println("Source: " + source);
			System.out.println("CardText: " + cardText);
			System.out.println("PointCost: " + cost);
			*/
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
		g.currentCards.clear();
		g.focusedCard.clear();
		g.startingCard = 0;
		g.activeShip = 500;
		g.resetSideButtons();
		g.focusedCard.add(this);
	}
	
	public void focusCardDetails() {
		// name cardType, faction, cardtext, source
		startingCharacter = 0;
		startingPixels = g.resizeY(270);
		g.game.font.draw(g.game.batch, name + " - " + uniqueString, xLine, startingPixels);
		startingPixels -= newLine;
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
		g.game.font.draw(g.game.batch, faction + " " + cardType + " from " + source, xLine, startingPixels);
	}

}
