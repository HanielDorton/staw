package com.haniel.staw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static AssetManager manager = new AssetManager();
	/*
	 * OK EVERYTHING IS SORTED BY FACTION
	 * THEN CARD TYPE
	 * THEN ALPHABETICALLY
	 * DON'T ADD ANYTHING OTHERWISE ELSE IT'LL BE A BITCH LATER
	 * KEEP FACTIONS ALPHABETICAL AS WELL
	 * CARD TYPE ALWAYS GOES SHIP-ADMIRAL-CAPTAIN-ELITE-CREW-WEAP-TECH-BORG
	 */
	
	
	public static void queueLoading(){
		manager.load("tng_bridge_2.mp3", Music.class);
		manager.load("backgroundpanel.png", Texture.class);
		manager.load("error.wav", Sound.class);
		manager.load("touchcard.mp3", Sound.class);
		manager.load("noeffect.mp3", Sound.class);
		manager.load("quickbeep.mp3", Sound.class);
		manager.load("doublebeep.mp3", Sound.class);
		manager.load("openscreen.mp3", Sound.class);
		manager.load("alert23.mp3", Sound.class);
		manager.load("CardBorder.png", Texture.class);
		
		for (int i = 0; i < 12; i ++) {
			manager.load("shutdown-" + i + ".png", Texture.class);
		}
	}
	
    public static boolean update() {
        return manager.update();
    }

}

