package com.haniel.staw.Cards;

import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;

public class WeaponCard extends Card{
	
	private int attack;
	private String range;

	public WeaponCard(Element element, GameScreen g, Fleet f) {
		super(element, g, f);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("AttackDice"))  {
				try {
					this.attack = Integer.parseInt((element.getChildByName("AttackDice")).getText());
				}
				catch (Exception e) {
					this.attack = 0;
				}
			}
			else if (text.equals("Range")) {
				try {
					this.range = (element.getChildByName("Range")).getText();
				}
				catch (Exception e) {
					this.range = "";
				}
			}
		}
	}
	public void focusCardDetails() {
		if (!focusCardActions) {
			startingPixels = g.resizeY(330);
			g.game.font.draw(g.game.batch, "Attack: " + attack, xLine, startingPixels);
			startingPixels -= newLine;
			g.game.font.draw(g.game.batch, "Range: " + range, xLine, startingPixels);
		}
		super.focusCardDetails();		
	}

}
