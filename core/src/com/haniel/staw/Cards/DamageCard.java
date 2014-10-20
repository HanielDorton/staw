package com.haniel.staw.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.haniel.staw.GameScreen;

public class DamageCard extends Card{
	@SuppressWarnings("unused")
	private int card;
	boolean active = true;
	
	public DamageCard(GameScreen g, int card) {
		super(g, card);
		this.card = card;
		setNameAndText(card);
		sortCardText();
		getTexture(name);
	}
	
	private void setNameAndText(int c) {
		switch (c) {
			case 1: {
				this.name = "Stunned Helmsman";
				this.cardText = "The next time you attack, do not roll any attack dice. Then flip this card face down.";
				break;
			}
			case 2: {
				this.name = "Console Fire";
				this.cardText = "At the start of the Combat Phase, roll 1 attack die. On a (hit) result, suffer 1 damage. Action: Flip this card face down.";
				break;
			}
			case 3: {
				this.name = "Communications Failure";
				this.cardText = "Starting the round after you receive this card, treat you Captain's Skill value as 0";
				break;
			}
			case 4: {
				this.name = "Engine Room Fire";
				this.cardText = "Treat all Turn maneuvers ((Left Turn) or (Right Turn)) as Red Maneuvers.";
				break;
			}
			case 5: {
				this.name = "Damaged Sensor Array";
				this.cardText = "You cannot perform the actions listed on your Action Bar. Action: Roll 1 attack die. On a (hit) result, flip this card face down.";
				break;
			}
			case 6: {
				this.name = "Direct Hit";
				this.cardText = "Suffer 1 additional damage and then flip this card face down.";
				break;
			}
			case 7: {
				this.name = "Injured Captain";
				this.cardText = "You cannot use your Captain's text or any of your (Talent) upgrades.";
				break;
			}
			case 8: {
				this.name = "Minor Explosion";
				this.cardText = "Immediately roll 1 attack die. On a (hit) result, suffer 1 damage. Then flip this card face down.";
				break;
			}
			case 9: {
				this.name = "Minor Hull Breach";
				this.cardText = "After executing a Red Maneuver, roll 1 attack die. On a (hit) result, suffer 1 damage.";
				break;
			}
			case 10: {
				this.name = "Warp Core Breach";
				this.cardText = "During the Planning Phase, roll 1 attack die. On a (Critical) result, your ship is destroyed. Action: For the rest of the game, you may ignore the first paragraph on all Warp Core Breach Cards, but you cannot choose a Maneuver with a number greater than 1";
				break;
			}
			case 11: {
				this.name = "Munitions Failure";
				this.cardText = "Immediately choose 1 of your (Weapon) Upgrades and discard it. Then flip this card face down.";
				break;
			}
			case 12: {
				this.name = "Structural Damage";
				this.cardText = "Reduce your Agility value by 1 (to a minimum of “0”). Action: Roll 1 attack die. On a (hit) result, flip this card face down.";
				break;
			}
			case 13: {
				this.name = "Jostled Navigator";
				this.cardText = "Whenever you execute a Maneuver that causes you to overlap either another ship or an Obstacle Token, suffer 1 damage.";
				break;
			}
			case 14: {
				this.name = "Power Disruption";
				this.cardText = "Immediately receive 1 Auxiliary Power Token. Then flip this card face down.";
				break;
			}
			default: {
				//15
				this.name = "Weapons Malfunction";
				this.cardText = "Reduce your Primary Weapon value by 1 (to a minimum of “0”). Action: Roll 1 attack die. On a (Hit) or (Critical) result, flip this card face down.";
				break;
			}
		}
	}
	
	private void sortCardText() {
		for (int c = startingCharacter; c < startingCharacter + cardText.length(); c++ ) {
			if (c < cardText.length()) {
				currenText1 += cardText.charAt(c);
			}
			if (c + lineLength < cardText.length()) {
				currenText2 += cardText.charAt(c + lineLength);
			}
			if (c  + (lineLength * 2) < cardText.length()) {
				currenText3 += cardText.charAt(c  + (lineLength * 2));
			}
			if (c  + (lineLength * 3) < cardText.length()) {
				currenText4 += cardText.charAt(c  + (lineLength * 3));
			}
			if (c  + (lineLength * 4) < cardText.length()) {
				currenText5 += cardText.charAt(c  + (lineLength * 4));
			}
			if (c  + (lineLength * 5) < cardText.length()) {
				currenText6 += cardText.charAt(c  + (lineLength * 5));
			}
			if (c  + (lineLength * 6) < cardText.length()) {
				currenText7 += cardText.charAt(c  + (lineLength * 6));
			}
		}
	}
	
	private void getTexture(String Name) {
		if (g.checkForFile("DamageCards/" + name + ".png")) {
			this.texture = new Texture(g.downloadFile("DamageCards/" + name + ".png"));
			this.textureLoaded = true;
		}
	}	
	
	public void setupCardActions() {
		TextButton buttonFlipOver = new TextButton("Flip Card Over", g.skin);
		buttonFlipOver.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (active) {
					if (g.playSounds) g.quickbeep.play();
					g.addAction(name + " Flipped Over");
					active = false;
				}
				else {
					if (g.playSounds) g.error.play();
					g.addAction("Error: " + name + " already flipped over");
				}
				
			}
		});
		actionButtons.add(buttonFlipOver);
	}

}
