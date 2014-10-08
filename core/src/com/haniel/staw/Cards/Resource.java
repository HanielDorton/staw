package com.haniel.staw.Cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Fleet;
import com.haniel.staw.GameScreen;

public class Resource extends Card{
	
	private List<Card> upgrades = new ArrayList<Card>();

	public Resource(Element element, GameScreen g, Fleet f) {
		super(element, g, f);
		if (Gdx.files.internal("Resources/" + name + ".png").exists()) {
			this.texture = new Texture(Gdx.files.internal("Resources/" + name + ".png"));
			this.textureLoaded = true;
			getUpgrades(element);
		}
	}
	
	private void getUpgrades(Element r) {
		for (int i = 0; i< r.getChildCount(); i++) {
			String text = r.getChild(i).getName();
			if (text.equals("Captain")) {
				upgrades.add(new CaptainCard(r.getChild(i), g, f));
			}
			if (text.equals("EliteTalent")) {
				upgrades.add(new Card(r.getChild(i), g, f));
			}
			if (text.equals("Crew")) {
				upgrades.add(new Card(r.getChild(i), g, f));
			}
			if (text.equals("Weapon")) {
				upgrades.add(new WeaponCard(r.getChild(i), g, f));
			}
			if (text.equals("Tech")) {
				upgrades.add(new Card(r.getChild(i), g, f));
			}
			
		}
	}
	
	public void displayResource(GameScreen g) {
		g.currentCards.add(this);
		for (Card upgrade : upgrades) {
			upgrade.displayCard(g);
		}
	}
	
	public void setupCardActions() {
		TextButton buttonUse = new TextButton("Use Card Action", g.skin);
		buttonUse.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				g.lastAction.setText("Action: " + name);
			}
		});
		actionButtons.add(buttonUse);
	}
	
	public void focusCardDetails() {
		// name cardType, faction, cardtext, source
		if (!focusCardActions) {
			startingCharacter = 0;

			startingPixels = g.resizeY(330);
			g.game.font.draw(g.game.batch, currenText1, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText2, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText3, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText4, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText5, xLine, startingPixels);
			startingPixels -= newTextLine;
			g.game.font.draw(g.game.batch, currenText6, xLine, startingPixels);
			startingPixels -= newLine;
			if (source != null) g.game.font.draw(g.game.batch, source, xLine, startingPixels);
		}
	}

}
