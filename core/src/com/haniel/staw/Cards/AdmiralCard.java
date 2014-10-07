package com.haniel.staw.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.GameScreen;

public class AdmiralCard extends Card{
	
	private int captSkill = 0;
	private int skill;
	private boolean isAdmiral = true;

	public AdmiralCard(Element element, GameScreen g) {
		super(element, g);
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("Skill")) {
				try {
					String s = "";
					s += (element.getChildByName("Skill")).getText().charAt(1);
					this.skill = Integer.parseInt(s);
				}
				catch (Exception e) {
					this.skill = 0;
				}
			}
		}
		this.textureLoaded = false;
		if (Gdx.files.internal(faction + "/" + name + ".png").exists()) {
			this.texture = new Texture((faction + "/" + name + ".png"));
			this.textureLoaded = true;
		}
		getCaptSkill();
		setupAdmiralButton();
	}

	private void getCaptSkill() {
		if (name.equals("Adm James T Kirk")) {
			captSkill = 8;
		}
		else if (name.equals("Adm Gul Dukat")) {
			captSkill = 8;
		}
		else if (name.equals("Adm Maxwell Forrest")) {
			captSkill = 4;
		}
	}
	
	public void toggleAdmCapt() {
		textureLoaded = false;
		if (isAdmiral) {
			isAdmiral = false;
			if (Gdx.files.internal(faction + "/" + name + " - Captain.png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + name + " - Captain.png"));
				this.textureLoaded = true;
			}			
		}
		else {
			isAdmiral = true;
			if (Gdx.files.internal(faction + "/" + name + ".png").exists()) {
				this.texture = new Texture(Gdx.files.internal(faction + "/" + name + ".png"));
				this.textureLoaded = true;
			}	
		}
	}
	public void setupAdmiralButton() {
		TextButton buttonToggleAdm = new TextButton("Toggle Adm/Capt", g.skin);
		buttonToggleAdm.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				toggleAdmCapt();
			}
		});
		actionButtons.add(buttonToggleAdm);
	}
	
	public void focusCardDetails() {
		if (!focusCardActions) {
			startingPixels = g.resizeY(330);
			g.game.font.draw(g.game.batch, "Admiral Skill Bonus: " +  skill, xLine, startingPixels);
			startingPixels -= newLine;
			g.game.font.draw(g.game.batch, "Captain Skill: " +  captSkill, xLine, startingPixels);
		}
		super.focusCardDetails();		
	}
}
