package com.haniel.staw.Cards;

import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;

public class AdmiralCard extends Card{
	

	public AdmiralCard(Element element, GameScreen g, Fleet f, ShipCard ship) {
		super(element, g, f, ship);
		this.isAdmiral = true;
		for (int i = 0; i< element.getChildCount(); i++) {
			String text = element.getChild(i).getName();
			if (text.equals("Skill")) {
				try {
					String sk = "";
					sk += (element.getChildByName("Skill")).getText().charAt(1);
					this.skill = Integer.parseInt(sk);
				}
				catch (Exception e) {
					this.skill = 0;
				}
			}
		}
		this.textureLoaded = false;
		if (g.checkForFile(faction + "/" + name + ".png")) {
			this.texture = new Texture(g.downloadFile(faction + "/" + name + ".png"));
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
		else if (name.equals("Adm Borg Queen")) {
			captSkill = 7;
		}
		else if (name.equals("Adm Matthew Dougherty")) {
			captSkill = 4;
		}
	}
	
	public void toggleAdmCapt() throws IOException {
		textureLoaded = false;
		if (isAdmiral) {
			isAdmiral = false;
			if (g.checkForFile(faction + "/" + name + " - Captain.png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + name + " - Captain.png"));
				this.textureLoaded = true;
			}			
		}
		else {
			isAdmiral = true;
			if (g.checkForFile(faction + "/" + name + ".png")) {
				this.texture = new Texture(g.downloadFile(faction + "/" + name + ".png"));
				this.textureLoaded = true;
			}	
		}
	}
	public void setupAdmiralButton() {
		TextButton buttonToggleAdm = new TextButton("Toggle Adm/Capt", g.skin);
		buttonToggleAdm.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				try {
					toggleAdmCapt();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
