package com.haniel.staw.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.haniel.staw.Assets;


public class ManueverCard extends Card{
	
	public ManueverCard(String faction, String shipClass){
		super(faction, shipClass);
		if (Assets.manager.isLoaded(faction + "/" + shipClass + " Manuevers.png")) {
			this.texture = Assets.manager.get(faction + "/" + shipClass + " Manuevers.png", Texture.class);
			this.textureLoaded = true;
		} else {
			System.out.println("Unable to load manuever card:" + shipClass);
		}
	}
}