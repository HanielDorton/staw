package com.haniel.staw;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LoadFileMenu {
	
	private int fleetButton;
	private GameScreen g;
	private Sound quickbeep = Assets.manager.get("quickbeep.mp3", Sound.class);
	private Table centerTable;
	
	private int buttonWidth, buttonHeight; 
	
	public LoadFileMenu(GameScreen g, Table centerTable, int fleetButton) {
		this.g = g;
		this.fleetButton = fleetButton;
		this.centerTable = centerTable;
		buttonWidth = g.resizeX(200);
		buttonHeight = g.resizeY(60);
		makeFileChooser(fleetButton);
		
	}

	private void makeFileChooser(final int fleetButton) {		
	
		centerTable.setBounds(g.resizeX(300), g.resizeY(100), g.resizeX(400), g.resizeY(500));
		
		final TextField currentDirectoryText = new TextField(g.currentDirectory.path(), g.skin);
		
		currentDirectoryText.addListener(new ChangeListener() {
	
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				quickbeep.play();
				
			}
			
		});
		
		final TextButton exitButton = new TextButton("Exit", g.skin);
		exitButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				quickbeep.play();
				centerTable.clear();
			}
		});
		
		TextButton backDirectory = new TextButton("Back", g.skin);
		backDirectory.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				quickbeep.play();
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
		centerTable.add(scrollPane).width(g.resizeX(200)).height(g.resizeY(300));
		centerTable.add(currentDirectoryText).width(buttonWidth).height(buttonHeight);
		centerTable.add(exitButton).width(buttonWidth).height(buttonHeight);
		centerTable.add(backDirectory).width(buttonWidth).height(buttonHeight);
		centerTable.add(openFile).width(buttonWidth).height(buttonHeight);
		
	}
	
	public void openFile(TextField currentDirectoryText, int fleetButton) {
		FileHandle newFile = Gdx.files.absolute(g.currentDirectory + g.directoryList.getSelected() + "/");
		if (newFile.isDirectory()) {
			quickbeep.play();
			g.currentDirectory = newFile;
			loadFiles(g.currentDirectory);
			currentDirectoryText.setText(newFile.path());
			return;
		} else {
			if (newFile.exists()) {
	    		g.loadFleet(newFile.toString(), fleetButton);
	    		return;
	    	}
		}
		newFile = Gdx.files.absolute(g.currentDirectory +"/" +  g.directoryList.getSelected() + "/");
		if (newFile.isDirectory()) {
			quickbeep.play();
			g.currentDirectory = newFile;
			loadFiles(g.currentDirectory);
			currentDirectoryText.setText(newFile.path());
			return;
		} else {
			if (newFile.exists()) {
	    		g.loadFleet(newFile.toString(), fleetButton);
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
	

}
