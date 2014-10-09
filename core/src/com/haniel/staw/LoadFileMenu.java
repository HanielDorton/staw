package com.haniel.staw;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader;

public class LoadFileMenu {
	
	private GameScreen g;
	private Table centerTable;
	
	private int buttonWidth, buttonHeight; 
	
	public LoadFileMenu(GameScreen g, Table centerTable, int fleetButton) {
		this.g = g;
		this.centerTable = centerTable;
		buttonWidth = g.resizeX(200);
		buttonHeight = g.resizeY(60);
		makeFileChooser(fleetButton);		
	}

	private void makeFileChooser(final int fleetButton) {		
			
		final TextField currentDirectoryText = new TextField(g.currentDirectory.path(), g.skin);
		
		final TextButton exitButton = new TextButton("Exit", g.skin);
		exitButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				centerTable.clear();
			}
		});
		
		TextButton backDirectory = new TextButton("Back", g.skin);
		backDirectory.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (g.playSounds) g.quickbeep.play();
				g.currentDirectory = g.currentDirectory.parent();
				loadFiles(g.currentDirectory);
				currentDirectoryText.setText(g.currentDirectory.path());
			}
		});
		
		TextButton openFile = new TextButton("Access", g.skin);
		openFile.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				openFile(currentDirectoryText, fleetButton);
			}
		});
		
		loadFiles(g.currentDirectory);

		ScrollPane scrollPane = new ScrollPane(g.directoryList, g.skin);
		
		centerTable.add(scrollPane).width(g.resizeX(300)).height(g.resizeY(250));
		
		Table newTable = new Table();	
		centerTable.add(newTable);
		
		newTable.add(currentDirectoryText).width(buttonWidth);
		newTable.row().pad(g.resizeY(10));
		newTable.add(exitButton).width(buttonWidth).height(buttonHeight);
		newTable.row().pad(g.resizeY(10));
		newTable.add(backDirectory).width(buttonWidth).height(buttonHeight);
		newTable.row().pad(g.resizeY(10));
		newTable.add(openFile).width(buttonWidth).height(buttonHeight);


	}
	
	public void openFile(TextField currentDirectoryText, int fleetButton) {
		FileHandle newFile = Gdx.files.absolute(g.currentDirectory + g.directoryList.getSelected() + "/");
		if (newFile.isDirectory()) {
			if (g.playSounds) g.quickbeep.play();
			g.currentDirectory = newFile;
			loadFiles(g.currentDirectory);
			currentDirectoryText.setText(newFile.path());
			return;
		} else {
			if (newFile.exists()) {
	    		loadFleet(newFile.toString(), fleetButton);
	    		return;
	    	}
		}
		newFile = Gdx.files.absolute(g.currentDirectory +"/" +  g.directoryList.getSelected() + "/");
		if (newFile.isDirectory()) {
			if (g.playSounds) g.quickbeep.play();
			g.currentDirectory = newFile;
			loadFiles(g.currentDirectory);
			currentDirectoryText.setText(newFile.path());
			return;
		} else {
			if (newFile.exists()) {
	    		loadFleet(newFile.toString(), fleetButton);
	    		return;
	    	}
		}
	}
	
	public void loadFiles(FileHandle file) {
		FileHandle[] files = file.list();
		ArrayList<String> stringFiles = new ArrayList<String>();
		for (FileHandle f : files) {
			stringFiles.add(f.name());
		}
		String[] filesArray = new String[ stringFiles.size() ];
		stringFiles.toArray(filesArray);
		g.directoryList.setItems(filesArray);
	}
	
	private void loadFleet(String text, final int fleetButton) {
		try {
			FileHandle handle = Gdx.files.absolute(text);
			XmlReader xml = new XmlReader();
			xml.parse(handle);
			final Fleet fleet = new Fleet(g, text);
			g.fleets.add(fleet);
			File f = new File(text);

			g.fleetButtons.remove(fleetButton);
			
			final TextButton button = new TextButton((f.getName()).replace(".xml", ""), g.skin);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					g.centerTable.clear();
					g.displayFleet(fleet);
					g.activeFleet = fleetButton;
					g.fleetTable.reset();
					g.redrawFleetTable();
					if (g.playSounds) g.doubleBeep.play();
				}
			});
			g.fleetButtons.add(fleetButton, button);
			g.fleetButtonsActive.remove(fleetButton);
			fleet.name = (f.getName()).replace(".xml", "");
			final TextButton button2 = new TextButton((f.getName()).replace(".xml", ""), g.skin2);
			button2.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					g.centerTable.clear();
					g.displayFleet(fleet);
					g.activeFleet = fleetButton;
					g.fleetTable.reset();
					g.redrawFleetTable();
					if (g.playSounds) g.doubleBeep.play();
				}
			});
			g.fleetButtonsActive.add(fleetButton, button2);
			g.activeFleet = fleetButton;
			g.displayFleet(fleet);
			g.fleetTable.reset();
			g.redrawFleetTable();
			if (g.playSounds) g.doubleBeep.play();
			centerTable.clear();
			g.addAction("Fleet " + f.getName().replace(".xml", "") + " Loaded");
		}
		catch (Exception e) {
			if (g.playSounds) g.error.play();
			g.addAction("Unable to Parse File");
		}
	}
	

}
