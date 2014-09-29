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
	 * CARD TYPE ALWAYS GOES SHIP-ADMIRAL-CAPTAIN-ELITE-CREW-WEAP-TECH-BORG
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
		
		//Independent Ships
		manager.load("Independent/Gavroche.png", Texture.class);
		manager.load("Independent/Gorn Starship.png", Texture.class);
		manager.load("Independent/Gorn Raider Manuevers.png", Texture.class);
		manager.load("Independent/Maquis Raider Manuevers.png", Texture.class);
		manager.load("Independent/S'Gorn.png", Texture.class);
		
		//Independent Captains
		manager.load("Independent/Drone.png", Texture.class);
		manager.load("Independent/Gorn Captain.png", Texture.class);
		manager.load("Independent/Gorn Commander.png", Texture.class);
		manager.load("Independent/Hugh.png", Texture.class);
		manager.load("Independent/Lore.png", Texture.class);
		manager.load("Independent/Magnus Hansen.png", Texture.class);
		manager.load("Independent/Michael Eddington.png", Texture.class);
		manager.load("Independent/Khan Singh USS Reliant Expansion.png", Texture.class);
		manager.load("Independent/Khan Singh Gen Con Promo.jpg", Texture.class);

		//Independent Elite
		manager.load("Independent/Diversionary Tactics.png", Texture.class);
		manager.load("Independent/Faked Messages.png", Texture.class);
		manager.load("Independent/Hijack.png", Texture.class);
		manager.load("Independent/I Stab at Thee.png", Texture.class);
		manager.load("Independent/Reserch Mission.png", Texture.class);
		manager.load("Independent/Superior Intellect.png", Texture.class);
		
		//Independent Crew		
		manager.load("Independent/Crosis.png", Texture.class);
		manager.load("Independent/Cyrano Jones.png", Texture.class);
		manager.load("Independent/Erin Hansen.png", Texture.class);
		manager.load("Independent/Follower of Khan.png", Texture.class);		
		manager.load("Independent/Gorn Pilot.png", Texture.class);
		manager.load("Independent/Goval.png", Texture.class);
		manager.load("Independent/Joachim.png", Texture.class);
		manager.load("Independent/Lon Suder.png", Texture.class);
		manager.load("Independent/Odo.png", Texture.class);
		manager.load("Independent/Sakonna.png", Texture.class);
		
		//Independent Weapons
		manager.load("Independent/Focused Particle Beam.png", Texture.class);
		
		//Independent Tech
		manager.load("Independent/Impulse Overload.png", Texture.class);
		manager.load("Independent/Jammed Communications.png", Texture.class);
		manager.load("Independent/Vic Fontaine.png", Texture.class);	
		
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

