package com.haniel.staw;

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
		//other resources first
		manager.load("Star Trek_ TNG USS-Enterprise D Bridge Background Ambience.mp3", Music.class);
		manager.load("backgroundpanel.png", Texture.class);
		manager.load("error.wav", Sound.class);
		manager.load("highpitch.wav", Sound.class);
		manager.load("noeffect.mp3", Sound.class);
		manager.load("quickbeep.mp3", Sound.class);
		manager.load("doublebeep.mp3", Sound.class);
		
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
		manager.load("Klingon/B’Rel-Class Manuevers.png", Texture.class);
		manager.load("Klingon/D7-Class Manuevers.png", Texture.class);
		manager.load("Klingon/D7-Class.png", Texture.class);
		manager.load("Klingon/IKS B'Moth.png", Texture.class);
		manager.load("Klingon/B’Rel-Class.png", Texture.class);
		manager.load("Klingon/IKS ChTang.png", Texture.class);
		manager.load("Klingon/IKS Gr'oth.png", Texture.class);
		manager.load("Klingon/IKS Koraga.png", Texture.class);
		manager.load("Klingon/IKS Kronos One.png", Texture.class);
		manager.load("Klingon/IKS Maht-H'A.png", Texture.class);
		manager.load("Klingon/Negh'Var.png", Texture.class);
		manager.load("Klingon/IKS Somraw.png", Texture.class);
		manager.load("Klingon/K'T'Inga Class Dominion War Month 2 LE.png", Texture.class);
		manager.load("Klingon/K'T'Inga Class Manuevers.png", Texture.class);
		manager.load("Klingon/K'T'Inga Class.png", Texture.class);
		manager.load("Klingon/K'Vort Class Manuevers.png", Texture.class);
		manager.load("Klingon/K'Vort Class.png", Texture.class);
		manager.load("Klingon/Negh'Var Class Manuevers.png", Texture.class);
		manager.load("Klingon/Negh'Var Class.png", Texture.class);
		manager.load("Klingon/Raptor Class Manuevers.png", Texture.class);
		manager.load("Klingon/Raptor Class.png", Texture.class);
		manager.load("Klingon/Vor'cha Class Manuevers.png", Texture.class);
		manager.load("Klingon/Vor'cha Class.png", Texture.class);
		
		//Klingon Captains
		manager.load("Klingon/Chang.png", Texture.class);
		manager.load("Klingon/Gorkon.png", Texture.class);
		manager.load("Klingon/Gowron.png", Texture.class);
		manager.load("Klingon/Klingon Captain.png", Texture.class);
		manager.load("Klingon/K'Nera.png", Texture.class);
		manager.load("Klingon/Koloth.png", Texture.class);
		manager.load("Klingon/Krell.png", Texture.class);
		manager.load("Klingon/Kurn.png", Texture.class);
		manager.load("Klingon/Martok LE Ch'Tang Pack.png", Texture.class);
		manager.load("Klingon/Martok.png", Texture.class);
		manager.load("Klingon/Nu'Daq.png", Texture.class);
		manager.load("Klingon/Somraw Commander.png", Texture.class);
		manager.load("Klingon/Worf.png", Texture.class);
		
		//Klingon Elite
		manager.load("Klingon/Defense Condition One.png", Texture.class);
		manager.load("Klingon/In'Cha.png", Texture.class);
		manager.load("Klingon/Klingon Honor.png", Texture.class);
		manager.load("Klingon/Once More Unto The Breach.png", Texture.class);
		manager.load("Klingon/Qapla'.png", Texture.class);
		manager.load("Klingon/Sabotage.png", Texture.class);
		
		//Klingon Crew		
		manager.load("Klingon/Alexander.png", Texture.class);
		manager.load("Klingon/Bu'Kah.png", Texture.class);
		manager.load("Klingon/Drex.png", Texture.class);
		manager.load("Klingon/Kerla.png", Texture.class);
		manager.load("Klingon/Klag.png", Texture.class);
		manager.load("Klingon/Klingon Boarding Party.png", Texture.class);
		manager.load("Klingon/Konmel.png", Texture.class);
		manager.load("Klingon/Korax.png", Texture.class);
		manager.load("Klingon/Kunivas.png", Texture.class);
		manager.load("Klingon/N'Garen.png", Texture.class);
		manager.load("Klingon/Stex.png", Texture.class);
		manager.load("Klingon/Synon.png", Texture.class);
		manager.load("Klingon/T'Kar.png", Texture.class);
		
		//Klingon Weapons
		manager.load("Klingon/Barrage of Fire.png", Texture.class);
		manager.load("Klingon/Concussive Charges.png", Texture.class);
		manager.load("Klingon/Magnetic Pulse.png", Texture.class);
		manager.load("Klingon/Photon Torpedoes Collective OP Blind Booster.png", Texture.class);
		manager.load("Klingon/Photon Torpedoes IKS Kronos One Koranga Expansion.png", Texture.class);
		manager.load("Klingon/Photon Torpedoes IKS GrOth Expansion.png", Texture.class);
		manager.load("Klingon/Photon Torpedoes NeghVah Expansion.png", Texture.class);
		manager.load("Klingon/Photon Torpedoes Starter Set.png", Texture.class);
		
		//Klingon Tech
		manager.load("Klingon/Advanced Weapon System.png", Texture.class);
		manager.load("Klingon/EM Pulse.png", Texture.class);
		manager.load("Klingon/Projected Stasis Field.png", Texture.class);
		manager.load("Klingon/Secondary Shield Emitters.png", Texture.class);
		manager.load("Klingon/Shockwave.png", Texture.class);
		manager.load("Klingon/Tactical Sensors.png", Texture.class);
		manager.load("Klingon/Tractor Beam.png", Texture.class);
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

