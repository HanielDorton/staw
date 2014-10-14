package com.haniel.staw;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.ShipCard;

public class GamePlay {
	private GameScreen g;
	public ArrayList<Card> sortedShips = new ArrayList<Card>();
	private boolean started = false;
	private int round = 1;
	boolean actionPhase = true;
	
	public GamePlay(GameScreen g) {
		this.g = g;
	}
	
	
	private void sortShipsForNewRound(boolean actionPhase) {
		sortedShips.clear();
		for (Fleet fleet: g.fleets) {
			for (Card ship : fleet.ships) {
				if (ship instanceof ShipCard) {
					if (ship.hull > 0) {
						ship.setSkill();
						addShipToSortedArray(ship, actionPhase);
					}
				}
				
			}
		}		
	}
	
	public void resortShips() {
		
		ArrayList<Card> temp = new ArrayList<Card>(sortedShips);
		sortedShips.clear();
		for (Card ship: temp) {
			if (ship.hull > 0) {
				ship.setSkill();
				addShipToSortedArray(ship, actionPhase);
			}
		}
	}
	
	private void addShipToSortedArray(Card ship, boolean actionPhase) {
		boolean done = false;
		if (actionPhase) {
			for (int i = 0; i < sortedShips.size(); i++) {
				if (!done) {
					if (ship.skill < sortedShips.get(i).skill) {
						sortedShips.add(i, ship);
						done = true;
					}
					else if (ship.skill == sortedShips.get(i).skill) {
						if (ship.factionLevel < sortedShips.get(i).factionLevel) {
							sortedShips.add(i, ship);
							done = true;			
						}
					}
				}
			}
		}
		else {
			for (int i = 0; i < sortedShips.size(); i++) {
				if (!done) {
					if (ship.skill > sortedShips.get(i).skill) {
						sortedShips.add(i, ship);
						done = true;
					}
					else if (ship.skill == sortedShips.get(i).skill) {
						if (ship.factionLevel < sortedShips.get(i).factionLevel) {
							sortedShips.add(i, ship);
							done = true;			
						}
					}
				}
			}
		}
		if (!done) {
			sortedShips.add(ship);
		}
	}
	
	public void next() {
		if (!started) {
			started = true;
			g.buttonNextShip.remove();
			g.buttonNextShip = new TextButton("Next Ship", g.skin);
			g.buttonNextShip.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					if (g.playSounds) g.doubleBeep.play();
					g.gamePlay.next();
				}
			});
			g.resetBottomMenu();
			ArrayList<String> l = new ArrayList<String>();
			g.rounds.add(l);
			g.addAction("Round " + round + ": Action Phase");
			sortShipsForNewRound(actionPhase);
		}
		else {
			if (sortedShips.size() == 0) {
				newRound();
			}
			else {
				resortShips();
			}			
		}
		g.currentLog = g.rounds.size()-1;
		if (actionPhase) sortedShips.get(0).addAction(sortedShips.get(0).f.name + " - " + sortedShips.get(0).name + " Round " + round + " - Action Phase:");
		else sortedShips.get(0).addAction(sortedShips.get(0).f.name + " - " + sortedShips.get(0).name + " Round " + round + " - Attack Phase:");
		sortedShips.get(0).viewShipLog();
		sortedShips.remove(0);
		
		
		
	}
	
	private void newRound() {
		ArrayList<String> l = new ArrayList<String>();
		g.rounds.add(l);
		
		if (actionPhase) {
			actionPhase = false;
			sortShipsForNewRound(actionPhase);
			g.addAction("Round " + round + ": Attack Phase");
		}
		else {
			round +=1;
			actionPhase = true;
			sortShipsForNewRound(actionPhase);
			g.addAction("Round " + round + ": Action Phase");
		}
	}
	

}