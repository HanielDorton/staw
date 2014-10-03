package com.haniel.staw.Cards;

import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.GameScreen;

public class WeaponCard extends Card{
	
	private int attack;
	private String range;

	public WeaponCard(Element element, GameScreen g) {
		super(element, g);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("AttackDice")) this.attack = Integer.parseInt((element.getChildByName("AttackDice")).getText());
			else if (text.equals("Range")) this.range = (element.getChildByName("Range")).getText();
		}
	}

}
