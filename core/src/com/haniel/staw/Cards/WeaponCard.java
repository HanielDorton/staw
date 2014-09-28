package com.haniel.staw.Cards;

import com.badlogic.gdx.utils.XmlReader.Element;

public class WeaponCard extends Card{
	
	private int attack;
	private String range;

	public WeaponCard(Element element) {
		super(element);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("AttackDice")) this.attack = Integer.parseInt((element.getChildByName("AttackDice")).getText());
			else if (text.equals("Range")) this.range = (element.getChildByName("Range")).getText();
		}
	}

}
