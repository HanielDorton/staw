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
				if (g.playSounds) {
					g.playSounds = false;
					g.addAction("Sound Muted");
				}
				else {
					g.playSounds = true;
					g.addAction("Sound Restored");
				}

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
		
		TextButton buttonResetFleets = new TextButton("Reset All", g.skin);
		buttonResetFleets.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				centerTable.clear();
				g.createFleetTable();
				g.gamePlay = new GamePlay(g);
				g.setupRoundsList();
				g.addAction("Game Reset");
				g.buttonNextShip = new TextButton("Start Game", g.skin);
				g.buttonNextShip.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						if (g.fleets.size() > 0) {
							if (g.playSounds) g.doubleBeep.play();
							g.gamePlay.next();
						} else {
							if (g.playSounds) g.error.play();
							g.addAction("Error: Unable to start game with no ships loaded");
						}
					}
				});
				g.resetBottomMenu();
			}
		});
		
		centerTable.add(buttonResetFleets).width(buttonWidth).height(buttonHeight);
		centerTable.row().padBottom(10).padTop(10);
		centerTable.add(buttonSounds).width(buttonWidth).height(buttonHeight);
		centerTable.row().padBottom(10);
		centerTable.add(buttonExit).width(buttonWidth).height(buttonHeight);
		centerTable.row().padBottom(10);
		centerTable.add(buttonExitMenu).width(buttonWidth).height(buttonHeight);
		
	}
}