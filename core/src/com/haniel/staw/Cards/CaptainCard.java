package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;

public class CaptainCard extends Card{
	private List<Card> talents = new ArrayList<Card>();

	public CaptainCard(Element element,GameScreen g, Fleet f, ShipCard ship) {
		super(element, g, f, ship);
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
			else if (text.equals("FleetCaptain")) {
				if (element.getChildByName("FleetCaptain").getText().equals("True")) {
					f.resourceLoaded = true;
					Element root = element.getParent().getParent().getParent();
					Array<Element> res = root.getChildrenByName("Resource");
					if (res.size > 0) {
						talents.add(new Resource(res.get(0), g, f, ship));
						for (int x = 0; x< res.get(0).getChildCount(); x++) {
							String resText = res.get(0).getChild(x).getName();
							if (resText.equals("Skill")) {
								this.skill += Integer.parseInt((res.get(0).getChildByName("Skill")).getText());
							}
						}
					}					
				}
			}
		}
		parseTalents(element);
		if (name.equals("Valdore")) this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " Captain.png"));
	}
	
	private void parseTalents(Element element) {
		for (int i = 0; i< element.getChildCount(); i++) {
			if (element.getChild(i).getName().equals("EliteTalent")) {
				talents.add(new Card(element.getChild(i), g, f, ship));			
			}
		}
		
	}
	
	public void setupCardActions() {
				
		TextButton buttonIncreaseSkill = new TextButton("Increase Skill", g.skin);
		buttonIncreaseSkill.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				skill += 1;
				ship.addAction(" - " + f.name + " " + ship.name + " " + name + " skill increased to " + skill);
				
			}
		});
		actionButtons.add(buttonIncreaseSkill);
		
		TextButton buttonDecreaseSkill = new TextButton("Decrease Skill", g.skin);
		buttonDecreaseSkill.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				skill -= 1;
				ship.addAction(" - " + f.name + " " + ship.name + " " + name + " skill decreased to " + skill);

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
