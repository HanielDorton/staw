package com.haniel.staw.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.haniel.staw.GameScreen;


public class ManueverCard extends Card{
	
	public ManueverCard(String faction, String shipClass, GameScreen g){
		super(faction, shipClass, g);
		if (g.checkForFile(faction + "/" + shipClass + " Manuevers.png")) {
			this.texture = new Texture(g.downloadFile(faction + "/" + shipClass + " Manuevers.png"));
			this.textureLoaded = true;
		} else {
			System.out.println("Unable to load manuever card:" + shipClass);
		}
	}
	
	public void focusCard() {
		g.currentCards.clear();
		g.resetSideButtons();
		g.focusedCard.add(this);
	}
	
	public void focusCardDetails() {
		//blank override cause there are no details to show.
	}
}