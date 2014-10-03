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
		manager.load("tng_bridge_2.mp3", Music.class);
		manager.load("backgroundpanel.png", Texture.class);
		manager.load("error.wav", Sound.class);
		manager.load("highpitch.wav", Sound.class);
		manager.load("noeffect.mp3", Sound.class);
		manager.load("quickbeep.mp3", Sound.class);
		manager.load("doublebeep.mp3", Sound.class);
		manager.load("openscreen.mp3", Sound.class);
		manager.load("alert23.mp3", Sound.class);
		manager.load("CardBorder.png", Texture.class);
		
		for (int i = 0; i < 12; i ++) {
			manager.load("shutdown-" + i + ".png", Texture.class);
		}
		/*
		
		//Bajoran Ships
		manager.load("Bajoran/Akorem.png", Texture.class);
		manager.load("Bajoran/Bajoran Scout Ship Manuevers.png", Texture.class);
		manager.load("Bajoran/Bajoran Scout Ship.png", Texture.class);
		
		//Bajoran Captains
		manager.load("Bajoran/Kira Nerys OP6 Prize.png", Texture.class);
		manager.load("Bajoran/Tahna Los.png", Texture.class);
		
		//Bajoran Elite
		manager.load("Bajoran/Blockade.png", Texture.class);
		manager.load("Bajoran/I Am Kohn-Ma.png", Texture.class);
		
		//Bajoran Crew		
		manager.load("Bajoran/Day Kannu.png", Texture.class);
		manager.load("Bajoran/Li Nalas.png", Texture.class);
		
		//Bajoran Weapons
		
		//Bajoran Tech
		

		/*
		
		
		//Dominion Ships
		manager.load("Dominion/2nd Division Cruiser.png", Texture.class);
		manager.load("Dominion/3rd Wing Patrol Ship.png", Texture.class);
		manager.load("Dominion/4th Division Battleship.png", Texture.class);
		manager.load("Dominion/5th Wing Patrol Ship.png", Texture.class);
		manager.load("Dominion/Breen Battle Cruiser Tech,Weapon,Weapon,Crew.png", Texture.class);
		manager.load("Dominion/Breen Battle Cruiser Weapon,Weapon,Weapon,Crew.png", Texture.class);
		manager.load("Dominion/Breen Battle Cruiser Manuevers.png", Texture.class);
		manager.load("Dominion/Cardassian Galor Class Manuevers.png", Texture.class);
		manager.load("Dominion/Cardassian Galor Class.png", Texture.class);
		manager.load("Dominion/Cardassian Keldon Class Manuevers.png", Texture.class);
		manager.load("Dominion/Cardassian Keldon Class.png", Texture.class);
		manager.load("Dominion/Gor Portas.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Attack Ship Manuevers.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Attack Ship.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Battle Cruiser Manuevers.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Battle Cruiser.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Battleship Manuevers.png", Texture.class);
		manager.load("Dominion/Jem'Hadar Battleship.png", Texture.class);
		manager.load("Dominion/Koranak.png", Texture.class);
		manager.load("Dominion/Kraxon.png", Texture.class);
		manager.load("Dominion/Rav Laerst.png", Texture.class);

		
		
		//Dominion Captains
		manager.load("Dominion/Breen Captain.png", Texture.class);
		manager.load("Dominion/Gelnon.png", Texture.class);
		manager.load("Dominion/Gul Damar.png", Texture.class);
		manager.load("Dominion/Gul Danar.png", Texture.class);
		manager.load("Dominion/Gul Dukat.png", Texture.class);
		manager.load("Dominion/Gul Evek.png", Texture.class);
		manager.load("Dominion/Gul Ranor.png", Texture.class);
		manager.load("Dominion/Keevan.png", Texture.class);
		manager.load("Dominion/Luaran.png", Texture.class);
		manager.load("Dominion/Sar.png", Texture.class);
		manager.load("Dominion/Toth Gor.png", Texture.class);
		manager.load("Dominion/Thot Pran.png", Texture.class);
		manager.load("Dominion/Weyoun 2nd Division Battle Cruiser Expansion.png", Texture.class);
		manager.load("Dominion/Weyoun 4th Division Battleship.png", Texture.class);
		manager.load("Dominion/Weyoun 5th Wing Patrol Ship Expansion.png", Texture.class);
		
		//Dominion Elite
		manager.load("Dominion/Captured Intelligence.png", Texture.class);
		manager.load("Dominion/First Strike.png", Texture.class);
		manager.load("Dominion/Invaluable Advice.png", Texture.class);
		manager.load("Dominion/Ketracell-White.png", Texture.class);
		manager.load("Dominion/Unnecessary Bloodshed.png", Texture.class);
		
		//Dominion Crew		
		manager.load("Dominion/Amat'Igan.png", Texture.class);
		manager.load("Dominion/Boheeka.png", Texture.class);
		manager.load("Dominion/Breen Aide.png", Texture.class);
		manager.load("Dominion/Breen Guards.png", Texture.class);
		manager.load("Dominion/Elim Garak DS9 GenCon CrewPack.png", Texture.class);
		manager.load("Dominion/Glinn Telle.png", Texture.class);
		manager.load("Dominion/Ikat'Ika.png", Texture.class);
		manager.load("Dominion/Ixtana'Rax.png", Texture.class);
		manager.load("Dominion/Kudak'Etan.png", Texture.class);
		manager.load("Dominion/Lamat'Ukan.png", Texture.class);
		manager.load("Dominion/Omet'Iklan.png", Texture.class);
		manager.load("Dominion/Remata'Klan.png", Texture.class);
		manager.load("Dominion/Seskal.png", Texture.class);
		manager.load("Dominion/Toman'Torax.png", Texture.class);
		manager.load("Dominion/Virak'Kara.png", Texture.class);
		
		//Dominion Weapons
		manager.load("Dominion/Dorsal Weapons Array.png", Texture.class);
		manager.load("Dominion/Energy Dissipator.png", Texture.class);
		manager.load("Dominion/Enhanced Weaponry.png", Texture.class);
		manager.load("Dominion/Forward Weapons Grid.png", Texture.class);
		manager.load("Dominion/Phased Polaron Beam 4th Division Battleship 2nd Division Cruiser.png", Texture.class);
		manager.load("Dominion/Phased Polaron Beam 5th Wing Patrol Ship Expansion.png", Texture.class);
		manager.load("Dominion/Photon Torpedoes 2nd Division Cruiser.png", Texture.class);
		manager.load("Dominion/Photon Torpedoes 4th Division Battleship.png", Texture.class);
		manager.load("Dominion/Photon Torpedoes Collective OP Blind Booster.png", Texture.class);
		manager.load("Dominion/Photon Torpedoes Gor Portas 5th Wing Patrol Ship.png", Texture.class);
		manager.load("Dominion/Volley of Torpedoes.png", Texture.class);
		
		//Dominion Tech
		manager.load("Dominion/Antiproton Scan.png", Texture.class);
		manager.load("Dominion/Cloaking Device.png", Texture.class);
		manager.load("Dominion/Cold Storage Units.png", Texture.class);
		manager.load("Dominion/EM Pulse.png", Texture.class);
		manager.load("Dominion/Ion Thrusters.png", Texture.class);
		manager.load("Dominion/Long Range Tachyon Scan.png", Texture.class);
		manager.load("Dominion/Sensor Array.png", Texture.class);
		manager.load("Dominion/Shroud.png", Texture.class);
		manager.load("Dominion/Suicide Attack.png", Texture.class);
		manager.load("Dominion/Tetryon Emissions.png", Texture.class);
		
		
		//Federation Ships
		manager.load("Federation/Aerie Class Manuevers.png", Texture.class);
		manager.load("Federation/Aerie Class.png", Texture.class);
		manager.load("Federation/Constellation Class Manuevers.png", Texture.class);
		manager.load("Federation/Constellation  Class Manuevers.png", Texture.class);
		manager.load("Federation/Constellation Class.png", Texture.class);
		manager.load("Federation/Constitution Class Manuevers.png", Texture.class);
		manager.load("Federation/Constitution Class.png", Texture.class);
		manager.load("Federation/Constitution Refit Class Manuevers.png", Texture.class);
		manager.load("Federation/Constitution Refit Class.png", Texture.class);
		manager.load("Federation/Deep Space 9.png", Texture.class);
		manager.load("Federation/Defiant Class Manuevers.png", Texture.class);
		manager.load("Federation/Defiant Class.png", Texture.class);
		manager.load("Federation/Enterprise NX-01.png", Texture.class);
		manager.load("Federation/Excelsior Class Manuevers.png", Texture.class);
		manager.load("Federation/Excelsior Class.png", Texture.class);
		manager.load("Federation/Federation NX Class Manuevers.png", Texture.class);
		manager.load("Federation/Federation NX Class.png", Texture.class);
		manager.load("Federation/Galaxy Class Manuevers.png", Texture.class);
		manager.load("Federation/Galaxy Class.png", Texture.class);
		manager.load("Federation/Intrepid Class Manuevers.png", Texture.class);
		manager.load("Federation/Intrepid Class.png", Texture.class);
		manager.load("Federation/Miranda Class Manuevers.png", Texture.class);
		manager.load("Federation/Miranda Class.png", Texture.class);
		manager.load("Federation/Nebula Class Manuevers.png", Texture.class);
		manager.load("Federation/Nebula Class.png", Texture.class);
		manager.load("Federation/Nova Class.png", Texture.class);
		manager.load("Federation/Nova Class Manuevers.png", Texture.class);
		manager.load("Federation/Saber Class Manuevers.png", Texture.class);
		manager.load("Federation/Saber Class.png", Texture.class);
		manager.load("Federation/USS Enterprise-D.png", Texture.class);
		manager.load("Federation/USS Defiant.png", Texture.class);
		manager.load("Federation/USS Enterprise Constitution Class.png", Texture.class);
		manager.load("Federation/USS Enterprise Constitution Refit Class.png", Texture.class);
		manager.load("Federation/USS Equinox.png", Texture.class);
		manager.load("Federation/USS Excelsior.png", Texture.class);
		manager.load("Federation/USS Raven.png", Texture.class);
		manager.load("Federation/USS Reliant.png", Texture.class);
		manager.load("Federation/USS Stargazer.png", Texture.class);
		manager.load("Federation/USS Sutherland.png", Texture.class);
		manager.load("Federation/USS Voyager.png", Texture.class);
		manager.load("Federation/USS Yeager.png", Texture.class);
		
		//Federation Captains
		manager.load("Federation/Benjamin Maxwell.png", Texture.class);
		manager.load("Federation/Benjamin Sisko DS9 GenCon CrewPack.png", Texture.class);
		manager.load("Federation/Benjamin Sisko USS Defiant Expansion.png", Texture.class);
		manager.load("Federation/Chakotay.png", Texture.class);
		manager.load("Federation/Christopher Pike.png", Texture.class);
		manager.load("Federation/Clark Terrell.png", Texture.class);
		manager.load("Federation/Data OP4 Sutherland Prize.png", Texture.class);
		manager.load("Federation/Federation Captain.png", Texture.class);
		manager.load("Federation/Hikaru Sulu USS Excelsior Expansion.png", Texture.class);
		manager.load("Federation/J Hayes.png", Texture.class);
		manager.load("Federation/James T Kirk.png", Texture.class);
		manager.load("Federation/Jean-Luc Picard Starter Set.png", Texture.class);
		manager.load("Federation/Jean-Luc Picard USS Stargazer.png", Texture.class);
		manager.load("Federation/Jonathan Archer.png", Texture.class);
		manager.load("Federation/Kathryn Janeway.png", Texture.class);
		manager.load("Federation/Maxwell Burke.png", Texture.class);
		manager.load("Federation/Mr Spock USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Rudolph Ransom.png", Texture.class);
		manager.load("Federation/Styles.png", Texture.class);
		manager.load("Federation/William T Riker Starter Set.png", Texture.class);
		manager.load("Federation/Will Decker.png", Texture.class);

		
		//Federation Elite
		manager.load("Federation/Attack Pattern Delta.png", Texture.class);
		manager.load("Federation/Attack Pattern Omega.png", Texture.class);
		manager.load("Federation/Cheat Death.png", Texture.class);
		manager.load("Federation/Cochrane Deceleration Maneuver.png", Texture.class);
		manager.load("Federation/Corbomite Maneuver.png", Texture.class);
		manager.load("Federation/Disobey Orders.png", Texture.class);
		manager.load("Federation/Engage.png", Texture.class);
		manager.load("Federation/Feint.png", Texture.class);
		manager.load("Federation/Full Alert.png", Texture.class);
		manager.load("Federation/Picard Maneuever.png", Texture.class);
		manager.load("Federation/Preemptive Strike.png", Texture.class);
		manager.load("Federation/Red Alert.png", Texture.class);
		manager.load("Federation/Sacrifice.png", Texture.class);
		manager.load("Federation/Self-Destruct Sequence.png", Texture.class);
		manager.load("Federation/Tactical Alert.png", Texture.class);
		manager.load("Federation/The Needs of the Many.png", Texture.class);
		
		//Federation Crew		
		manager.load("Federation/B'Elanna Torres.png", Texture.class);
		manager.load("Federation/Charles Tucker III.png", Texture.class);
		manager.load("Federation/Christopher Hobson.png", Texture.class);
		manager.load("Federation/Data Starter Set.png", Texture.class);
		manager.load("Federation/Dmitri Valtane.png", Texture.class);
		manager.load("Federation/Elizabeth Shelby.png", Texture.class);
		manager.load("Federation/Emergency Medical Hologram.png", Texture.class);
		manager.load("Federation/Geordi LaForge Starter Set.png", Texture.class);
		manager.load("Federation/Harry Kim.png", Texture.class);
		manager.load("Federation/Hikaru Sulu USS Enterprise Expansion.png", Texture.class);
		manager.load("Federation/Hikaru Sulu USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Hoshi Sato.png", Texture.class);
		manager.load("Federation/Ilia.png", Texture.class);
		manager.load("Federation/Jack Crusher.png", Texture.class);
		manager.load("Federation/Jadzia Dax.png", Texture.class);
		manager.load("Federation/Janice Rand.png", Texture.class);
		manager.load("Federation/Julian Bashir.png", Texture.class);
		manager.load("Federation/Kyle.png", Texture.class);
		manager.load("Federation/Leonard McCoy USS Enterprise Expansion.png", Texture.class);
		manager.load("Federation/Leonard McCoy USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Lojour.png", Texture.class);
		manager.load("Federation/Malcom Reed.png", Texture.class);
		manager.load("Federation/Marla Gilmore.png", Texture.class);
		manager.load("Federation/Miles O'Brien Starter Set.png", Texture.class);
		manager.load("Federation/Miles O'Brien USS Defiant Expansion.png", Texture.class);
		manager.load("Federation/Montgomery Scott USS Enterprise Expansion.png", Texture.class);
		manager.load("Federation/Montgomery Scott USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Mr Spock USS Enterprise Expansion.png", Texture.class);
		manager.load("Federation/Noah Lessing.png", Texture.class);
		manager.load("Federation/Nyota Uhuru USS Enterprise Expansion.png", Texture.class);
		manager.load("Federation/Nyota Uhuru USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Pavel Checkov USS Enterprise Refit Expansion.png", Texture.class);
		manager.load("Federation/Pavel Chekov USS Reliant Expansion.png", Texture.class);
		manager.load("Federation/Phlox.png", Texture.class);
		manager.load("Federation/Red Shirt.png", Texture.class);
		manager.load("Federation/Reginald Barclay.png", Texture.class);
		manager.load("Federation/Saavik.png", Texture.class);
		manager.load("Federation/Seven of Nine.png", Texture.class);
		manager.load("Federation/T'Pol.png", Texture.class);
		manager.load("Federation/The Doctor.png", Texture.class);
		manager.load("Federation/Tom Paris.png", Texture.class);
		manager.load("Federation/Travis Mayweather.png", Texture.class);
		manager.load("Federation/Tuvok.png", Texture.class);
		manager.load("Federation/Worf Starter Set.png", Texture.class);
		manager.load("Federation/Worf USS Defiant Expansion.png", Texture.class);
		
		
		//Federation Weapons
		manager.load("Federation/Aft Phase Cannon.png", Texture.class);
		manager.load("Federation/Antimatter Mines.png", Texture.class);
		manager.load("Federation/Photon Torpedoes Collective OP Blind Booster USS Enterprise Refit.png", Texture.class);
		manager.load("Federation/Photon Torpedoes USS Defiant, Voyager and Starter.png", Texture.class);
		manager.load("Federation/Photon Torpedoes USS Enterprise Reliant Excelsior Expansion.png", Texture.class);
		manager.load("Federation/Photonic Torpedoes.png", Texture.class);
		manager.load("Federation/Quantum Torpedoes.png", Texture.class);
		manager.load("Federation/Secondary Torpedo Launcher.png", Texture.class);
		manager.load("Federation/Tactical Station.png", Texture.class);
		manager.load("Federation/Transphasic Torpedoes.png", Texture.class);
		
		//Federation Tech
		manager.load("Federation/Ablative Generator.png", Texture.class);
		manager.load("Federation/Bio-Neural Circuitry.png", Texture.class);
		manager.load("Federation/Cloaking Device.png", Texture.class);
		manager.load("Federation/Enhanced Hull Plating.png", Texture.class);
		manager.load("Federation/High Energy Sensor Sweep.png", Texture.class);
		manager.load("Federation/Multi-Adaptive Shields.png", Texture.class);
		manager.load("Federation/Navigational Deflector.png", Texture.class);
		manager.load("Federation/Positron Beam.png", Texture.class);
		manager.load("Federation/Reinforced Structural Integrity.png", Texture.class);
		manager.load("Federation/Secondary Impusle Reactor.png", Texture.class);
		manager.load("Federation/Transwarp Drive.png", Texture.class);
		
		
		//Ferengi Ships
		manager.load("Ferengi/Krayton.png", Texture.class);
		manager.load("Ferengi/D'Kora Class.png", Texture.class);
		manager.load("Ferengi/D'Kora Class Manuevers.png", Texture.class);
		
		//Ferengi Captains
		manager.load("Ferengi/Daimon Tog.png", Texture.class);
		manager.load("Ferengi/Ferengi Captain.png", Texture.class);
		
		//Ferengi Elite
		manager.load("Ferengi/Conditional Surrender.png", Texture.class);
		
		//Ferengi Crew		
		manager.load("Ferengi/Quark.png", Texture.class);
		manager.load("Ferengi/Farek.png", Texture.class);
		
		//Ferengi Weapons
		manager.load("Ferengi/Missile Launchers.png", Texture.class);
		
		//Ferengi Tech
		manager.load("Ferengi/EM Pulse.png", Texture.class);
		
		//Independent Ships
		manager.load("Independent/Gavroche.png", Texture.class);
		manager.load("Independent/Gorn Starship.png", Texture.class);
		manager.load("Independent/Gorn Raider Manuevers.png", Texture.class);
		manager.load("Independent/Maquis Raider Manuevers.png", Texture.class);
		manager.load("Independent/Maquis Raider.png", Texture.class);
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
		manager.load("Klingon/BRel-Class Manuevers.png", Texture.class);
		manager.load("Klingon/D7-Class Manuevers.png", Texture.class);
		manager.load("Klingon/D7-Class.png", Texture.class);
		manager.load("Klingon/IKS B'Moth.png", Texture.class);
		manager.load("Klingon/BRel-Class.png", Texture.class);
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

		
		
		//Romulan Ships
		manager.load("Romulan/PWB Aj'Rmr.png", Texture.class);
		manager.load("Romulan/Dderidex Tech,Weapon,Weapon,Crew.png", Texture.class);
		manager.load("Romulan/Dderidex Tech,Weapon,Crew,Crew.png", Texture.class);
		manager.load("Romulan/Dderidex Manuevers.png", Texture.class);
		manager.load("Romulan/IRW Gal Gath'Thong.png", Texture.class);
		manager.load("Romulan/IRW Khazara.png", Texture.class);
		manager.load("Romulan/IRW Praetus.png", Texture.class);
		manager.load("Romulan/IRW Valdore.png", Texture.class);
		manager.load("Romulan/IRW Vorta Vor.png", Texture.class);
		manager.load("Romulan/RIS Apnex.png", Texture.class);
		manager.load("Romulan/RIS Vo.png", Texture.class);
		manager.load("Romulan/Romulan Bird of Prey 1412113113968.png", Texture.class);
		manager.load("Romulan/Romulan Bird of Prey 1412113123776 Manuevers.png", Texture.class);
		manager.load("Romulan/Romulan Bird of Prey 1412113123776.png", Texture.class);
		manager.load("Romulan/Romulan Bird of Prey Manuevers.png", Texture.class);
		manager.load("Romulan/Romulan Bird of Prey.png", Texture.class);
		manager.load("Romulan/Romulan Science Vessel Manuevers.png", Texture.class);
		manager.load("Romulan/Romulan Science Vessel.png", Texture.class);
		manager.load("Romulan/Romulan Scout Vessel Manuevers.png", Texture.class);
		manager.load("Romulan/Romulan Scout Vessel.png", Texture.class);
		manager.load("Romulan/Valdore Manuevers.png", Texture.class);
		manager.load("Romulan/Valdore.png", Texture.class);
		
		//Romulan Captains
		manager.load("Romulan/Alidar Jarok.png", Texture.class);
		manager.load("Romulan/Donatra.png", Texture.class);
		manager.load("Romulan/Letant.png", Texture.class);
		manager.load("Romulan/Livianna Charvanek.png", Texture.class);
		manager.load("Romulan/Mirok.png", Texture.class);
		manager.load("Romulan/Romulan Captain.png", Texture.class);
		manager.load("Romulan/Romulan Commander.png", Texture.class);
		manager.load("Romulan/Tomalak.png", Texture.class);
		manager.load("Romulan/Toreth.png", Texture.class);
		manager.load("Romulan/Valdore Captain.png", Texture.class);
		
		//Romulan Elite
		manager.load("Romulan/All Forward Disruptor Banks.png", Texture.class);
		manager.load("Romulan/Counter Attack.png", Texture.class);
		manager.load("Romulan/Decoy.png", Texture.class);
		manager.load("Romulan/Direct Command.png", Texture.class);
		manager.load("Romulan/Double Back.png", Texture.class);
		manager.load("Romulan/Invasion Plans.png", Texture.class);
		manager.load("Romulan/Massacre.png", Texture.class);
		
		//Romulan Crew		
		manager.load("Romulan/Bochra.png", Texture.class);
		manager.load("Romulan/Centurion.png", Texture.class);
		manager.load("Romulan/N'Vek.png", Texture.class);
		manager.load("Romulan/Parem.png", Texture.class);
		manager.load("Romulan/Romulan Officer.png", Texture.class);
		manager.load("Romulan/Romulan Pilot.png", Texture.class);
		manager.load("Romulan/Selok.png", Texture.class);
		manager.load("Romulan/Tactical Officer.png", Texture.class);
		manager.load("Romulan/Tal.png", Texture.class);
		manager.load("Romulan/T'Rul.png", Texture.class);
		manager.load("Romulan/Varel.png", Texture.class);
		
		//Romulan Weapons
		manager.load("Romulan/Additional Weapons Array.png", Texture.class);
		manager.load("Romulan/Nuclear Missiles.png", Texture.class);
		manager.load("Romulan/Nuclear Warhead.png", Texture.class);
		manager.load("Romulan/Photon Torpedoes.png", Texture.class);
		manager.load("Romulan/Plasma Torpedoes Collective OP Blind Booster.png", Texture.class);
		manager.load("Romulan/Plasma Torpedoes IRW Gal Gath'Thong.png", Texture.class);
		manager.load("Romulan/Plasma Torpedoes IRW Valdore Starter Set.png", Texture.class);
		
		//Romulan Tech
		manager.load("Romulan/Advanced Cloaking.png", Texture.class);
		manager.load("Romulan/Artificial Quantum Singularity.png", Texture.class);
		manager.load("Romulan/Cloaked Mines.png", Texture.class);
		manager.load("Romulan/Interphase Generator.png", Texture.class);
		manager.load("Romulan/Muon Feedback Wave.png", Texture.class);
		manager.load("Romulan/Nullifier Core.png", Texture.class);
		manager.load("Romulan/Polarized Hull Plating.png", Texture.class);
		manager.load("Romulan/Ultritium Explosives.png", Texture.class);
		
		


		
		
		//Klingon Ships
		
		//Klingon Captains
		
		//Klingon Elite
		
		//Klingon Crew		
		
		//Klingon Weapons
		
		//Klingon Tech
		
		
	*/	
	}
	
    public static boolean update() {
        return manager.update();
    }

}

