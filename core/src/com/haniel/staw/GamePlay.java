package com.haniel.staw;

import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.ShipCard;

public class GamePlay {
	private GameScreen g;
	
	public GamePlay(GameScreen g) {
		this.g = g;
	}
	
	public void sortCaptains() {
		
	}
	
	public void resetShips() {
		for (Fleet f: g.fleets) {
			for (Card ship: f.ships) {
				if (ship instanceof ShipCard) {
					ship.actionPhaseCompleted = false;
					ship.attackPhaseCompleted = false;
				}
			}
		}
	}
	
	private void sortCaptainsForActions() {
		
	}
	
	private void sortCaptainsForAttack() {
		
	}

}
