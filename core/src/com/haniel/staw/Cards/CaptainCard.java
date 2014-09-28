package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.XmlReader.Element;

public class CaptainCard extends Card{
	
	private int skill;
	//private boolean fleetCaptain = false;
	private List<Card> talents = new ArrayList<Card>();

	public CaptainCard(Element element) {
		super(element);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("Skill")) this.skill = Integer.parseInt((element.getChildByName("Skill")).getText());
			//if (text.equals("FleetCaptain")) fleetCaptain = true;
		}
		parseTalents(element);
	}
	
	private void parseTalents(Element element) {
		for (int i = 0; i< element.getChildCount(); i++) {
			if (element.getChild(i).getName().equals("EliteTalent")) {
				talents.add(new Card(element.getChild(i)));			
			}
		}
		
	}
	public List<Card> getTalents() {
		return talents;
	}

	public int getSkill() {
		return skill;
	}

}
