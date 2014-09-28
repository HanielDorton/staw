package com.haniel.staw;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static AssetManager manager = new AssetManager();
	/*
	 * OK EVERYTHING IS SORTED BY FACTION
	 * THEN CARD TYPE
	 * THEN ALPHABETICALLY
	 * DON'T ADD ANYTHING OTHERWISE ELSE IT'LL BE A BITCH LATER
	 * KEEP FACTIONS ALPHABETICAL AS WELL
	 * BUT CARD TYPE ALWAYS GOES SHIP-ADMIRAL-CAPTAIN-ELITE-CREW-WEAP-TECH-BORG
	 */
	
	
	public static void queueLoading(){
		//other resources first
		manager.load("Star Trek_ TNG USS-Enterprise D Bridge Background Ambience.mp3", Music.class);
		
		//Dominion Ships
		manager.load("Dominion/3rd Wing Patrol Ship.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Attack Ship.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Attack Ship Manuevers.png", Texture.class);
		
		//Dominion Captains
		manager.load("Dominion/Gul Damar.png", Texture.class);
		//Dominion Elite
		manager.load("Dominion/First Strike.png", Texture.class);
		//Dominion Crew		
		manager.load("Dominion/Seskal.png", Texture.class);
		//Dominion Weapons
		manager.load("Dominion/Photon Torpedoes Collective OP Blind Booster.png", Texture.class);
		//Dominion Tech
		manager.load("Dominion/Ion Thrusters.png", Texture.class);
		
		
		
		//Federation Ships-- ship named, then generic, then manuevers
		manager.load("Federation/USS Yeager.png", Texture.class);
		manager.load("Federation/Saber Class.png", Texture.class);
		manager.load("Federation/Saber Class Manuevers.png", Texture.class);
		//Federation Captains
		manager.load("Federation/Benjamin Maxwell.png", Texture.class);
		//Federation Elite
		manager.load("Federation/Preemptive Strike.png", Texture.class);
		//Federation Crew		
		manager.load("Federation/Elizabeth Shelby.png", Texture.class);
		manager.load("Federation/Reginald Barclay.png", Texture.class);
		//Federation Weapons
		manager.load("Federation/Photon Torpedoes Collective OP Blind Booster USS Enterprise Refit.png", Texture.class);
		//Federation Tech
		
				
		//Klingon Ships
		manager.load("Klingon/IKS B'Moth.png", Texture.class);
		manager.load("Klingon/K'T'Inga Class.png", Texture.class);
		manager.load("Klingon/K'T'Inga Class Manuevers.png", Texture.class);
		//Klingon Captains
		manager.load("Klingon/K'Nera.png", Texture.class);
		//Klingon Elite
		manager.load("Klingon/Qapla'.png", Texture.class);
		//Klingon Crew		
		manager.load("Klingon/Kunivas.png", Texture.class);
		//Klingon Weapons
		manager.load("Klingon/Photon Torpedoes Collective OP Blind Booster.png", Texture.class);
		//Klingon Tech
		manager.load("Klingon/Tritium Intermix.png", Texture.class);
		
		
		
		
		//Klingon Ships
		
		//Klingon Captains
		
		//Klingon Elite
		
		//Klingon Crew		
		
		//Klingon Weapons
		
		//Klingon Tech
		
		
		
	}
	
    public static boolean update() {
        return manager.update();
    }

}

