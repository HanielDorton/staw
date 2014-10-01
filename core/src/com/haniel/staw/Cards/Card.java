package com.haniel.staw.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Assets;
import com.haniel.staw.GameScreen;


public class Card {
	
	// Card is extended for Ship, Captain, Admiral and Weapons
	// Generic cards are Techs, Talents and Crew
	
	protected String cardType, name, unique, source, cardText, faction;
	protected int cost;
	protected boolean disabled = false;
	protected boolean discardedByUse = false;
	protected boolean discardByOpponent = false;
	protected Texture texture;
	protected boolean textureLoaded = false;
	
	public Card(Element element){
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
		loadTexture();
	}
	
	public Card(String faction, String shipClass){
		//used for manuever cards
	}

	
	private void loadTexture() {

		if (Assets.manager.isLoaded(faction + "/" + name + " " + source + ".png")) {
			this.texture = Assets.manager.get(faction + "/" + name + " " + source + ".png", Texture.class);
			this.textureLoaded = true;
		}
		else if (Assets.manager.isLoaded(faction + "/" + name + ".png")) {
			this.texture = Assets.manager.get(faction + "/" + name + ".png", Texture.class);
			this.textureLoaded = true;
		}
		else {
			System.out.println("----");
			System.out.println("Unable to Load: " + name);
			System.out.println("Source: " + source);
			System.out.println("CardText: " + cardText);
			System.out.println("PointCost: " + cost);
		}
	}

	public void displayCard(GameScreen g) {
		g.addCards(this);
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

}
