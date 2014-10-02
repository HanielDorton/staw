package com.haniel.staw;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SettingsMenu {
	private GameScreen g;
	private Table centerTable;
	
	private int buttonWidth, buttonHeight; 
	
	public SettingsMenu(GameScreen g, Table centerTable) {
		this.g = g;
		this.centerTable = centerTable;
		buttonWidth = g.resizeX(200);
		buttonHeight = g.resizeY(40);
		makeMenu();		
	}

	private void makeMenu() {
		
		TextButton buttonSounds = new TextButton("Toggle Sounds", g.skin);
		buttonSounds.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				if (g.backgroundMusic.isPlaying()) g.backgroundMusic.stop();
				else g.backgroundMusic.play();
				if (g.playSounds)g.playSounds = false;
				else g.playSounds = true;

			}
		});
		
		TextButton buttonExit = new TextButton("End Program", g.skin);
		buttonExit.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				g.backgroundMusic.stop();
				if (g.playSounds) g.quickbeep.play();
				g.game.setScreen(new ShutdownScreen(g.game, g.playSounds));
			}
		});
		
		TextButton buttonExitMenu = new TextButton("Exit menu", g.skin);
		buttonExitMenu.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				centerTable.clear();
			}
		});
		
		
		centerTable.add(buttonSounds).width(buttonWidth).height(buttonHeight);
		centerTable.row().padBottom(10).padTop(10);
		centerTable.add(buttonExit).width(buttonWidth).height(buttonHeight);
		centerTable.row().padBottom(10);
		centerTable.add(buttonExitMenu).width(buttonWidth).height(buttonHeight);
		
	}
}