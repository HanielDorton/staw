package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.GameScreen;

public class CaptainCard extends Card{
	
	private int skill;
	//private boolean fleetCaptain = false;
	private List<Card> talents = new ArrayList<Card>();

	public CaptainCard(Element element,GameScreen g) {
		super(element, g);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("Skill")) {
				try {
					this.skill = Integer.parseInt((element.getChildByName("Skill")).getText());
				}
				catch (Exception e) {
					this.skill = 0;
				}
			}
			//if (text.equals("FleetCaptain")) fleetCaptain = true;
		}
		parseTalents(element);
		if (name.equals("Valdore")) this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " Captain.png"));
	}
	
	private void parseTalents(Element element) {
		for (int i = 0; i< element.getChildCount(); i++) {
			if (element.getChild(i).getName().equals("EliteTalent")) {
				talents.add(new Card(element.getChild(i), g));			
			}
		}
		
	}
	
	public void setupCardActions() {
				
		TextButton buttonIncreaseSkill = new TextButton("Increase Skill", g.skin);
		buttonIncreaseSkill.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Capt Skill Increase");
			}
		});
		actionButtons.add(buttonIncreaseSkill);
		
		TextButton buttonDecreaseSkill = new TextButton("Decrease Skill", g.skin);
		buttonDecreaseSkill.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				System.out.println("Capt Skill Decrease");
			}
		});
		actionButtons.add(buttonDecreaseSkill);
		
		super.setupCardActions();
	}
	public List<Card> getTalents() {
		return talents;
	}

	public int getSkill() {
		return skill;
	}
	
	
	public void focusCardDetails() {
		if (!focusCardActions) {
			startingPixels = g.resizeY(300);
			g.game.font.draw(g.game.batch, "Skill: " +  skill, xLine, startingPixels);
		}
		super.focusCardDetails();		
	}

}
